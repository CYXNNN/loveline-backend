package ch.egli.lovelinebackend.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import ch.egli.lovelinebackend.Config;
import ch.egli.lovelinebackend.repo.FileElementRepo;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	private Path base = Paths.get(Config.UPLOAD_DIRECTORY);

	@Autowired
	private FileElementRepo repo;

	FileService() {
		if (!Files.exists(base)) {
			init();
		}
	}

	public Resource get(String path) {
		try {
			var first = Files.walk(toFilepath(path))
					.filter(Files::isRegularFile)
					.findFirst()
					.orElseThrow(() -> new NullPointerException("Dokument konnte nicht gefunden werden."));

			Resource resource = new UrlResource(first.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (IOException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

	public void save(MultipartFile file, String path) throws IOException {
		Objects.requireNonNull(file.getOriginalFilename());

		var filepath = toFilepath(path);

		Files.createDirectories(filepath);
		Files.copy(file.getInputStream(), filepath.resolve(file.getOriginalFilename()));
	}

	public void delete(String path) throws IOException {
		Files.delete(toFilepath(path));
	}

	public void update(MultipartFile file, String path) {
		// TODO
	}

	public String saveThumbnail(String path) {

		var newPath = "thumbnail." + path;

		var filepath = toFilepath(path);

		try {
			var file = filepath.toFile();
			Thumbnails.of(file)
					.size(200, 200)
					.outputFormat("jpg")
					.toFiles(Rename.PREFIX_DOT_THUMBNAIL);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return newPath;
	}

	private Path toFilepath(String path) {
		return Path.of(base + "\\" + path);
	}

	private void init() {
		try {
			Files.createDirectories(base);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize folder for upload!");
		}
	}
}
