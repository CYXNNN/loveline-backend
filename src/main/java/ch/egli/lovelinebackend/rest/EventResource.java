package ch.egli.lovelinebackend.rest;

import java.util.List;

import javax.validation.Valid;

import ch.egli.lovelinebackend.dto.EventCreationDto;
import ch.egli.lovelinebackend.model.Event;
import ch.egli.lovelinebackend.model.FileElement;
import ch.egli.lovelinebackend.service.EventService;
import ch.egli.lovelinebackend.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@CrossOrigin(origins = { "*" }, maxAge = 3600) // FIXME
@RequestMapping("/api/v1/event")
public class EventResource {

	@Autowired
	private EventService service;

	@Autowired
	private FileService fileService;

	@RequestMapping(path = "",
			method = RequestMethod.POST,
			consumes = "application/json",
			produces = "application/json")
	public HttpEntity<Event> post(@RequestBody @Valid EventCreationDto dto) {

		var entity = service.post(dto.toEntity());

		return new HttpEntity<>(entity);
	}

	@RequestMapping(path = "/{eventId}",
			method = RequestMethod.POST,
			consumes = "multipart/form-data",
			produces = "application/json")
	public HttpEntity<List<FileElement>> upload(@RequestParam MultipartFile[] files, @PathVariable String eventId) {
		return new HttpEntity<>(service.upload(files, eventId));
	}

	@GetMapping("/{path}/{filename}")
	public HttpEntity<Resource> getFile(@PathVariable String path, @PathVariable String filename) {

		var file = fileService.get(path + "/" + filename);

		return new HttpEntity<>(file);
	}

	@GetMapping
	public HttpEntity<List<Event>> get() {
		return new HttpEntity<>(service.get());
	}
}
