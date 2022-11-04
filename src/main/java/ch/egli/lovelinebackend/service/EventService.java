package ch.egli.lovelinebackend.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import ch.egli.lovelinebackend.model.Event;
import ch.egli.lovelinebackend.model.FileElement;
import ch.egli.lovelinebackend.repo.EventRepo;
import ch.egli.lovelinebackend.repo.FileElementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EventService {

	@Autowired
	private EventRepo repo;

	@Autowired
	private FileElementRepo fileRepo;

	@Autowired
	private FileService fileService;

	public Event post(Event event) {
		return repo.save(event);
	}

	public List<Event> get() {
		return repo.findAllByOrderByDateDesc();
	}

	public List<FileElement> upload(MultipartFile[] files, String eventId) {
		var event = repo.findById(eventId).orElseThrow(NullPointerException::new);

		var fileElements = Arrays.stream(files).map(file -> {

			var fileEntity = fileToEntity(file, eventId);

			fileEntity.setEvent(event);
			fileRepo.save(fileEntity);

			event.addFile(fileEntity);
			repo.save(event);

			try {
				fileService.save(file, fileEntity.getPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return fileEntity;
		}).collect(Collectors.toList());

		var first = event.getElements().get(0);
		var thumb = fileService.saveThumbnail(first.getPath() + "/" + first.getFilename());
		event.setThumbnailPath(thumb);

		return fileElements;
	}

	private FileElement fileToEntity(MultipartFile file, String eventId) {
		var entity = new FileElement();

		entity.setId(UUID.randomUUID().toString());
		entity.setCreated(LocalDateTime.now());
		entity.setUpdated(LocalDateTime.now());

		entity.setFilename(file.getOriginalFilename());
		entity.setMime(file.getContentType());
		entity.setPath(eventId);

		return entity;
	}
}
