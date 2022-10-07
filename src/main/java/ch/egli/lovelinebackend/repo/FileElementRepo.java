package ch.egli.lovelinebackend.repo;

import ch.egli.lovelinebackend.model.Event;
import ch.egli.lovelinebackend.model.FileElement;
import org.springframework.data.repository.CrudRepository;

public interface FileElementRepo extends CrudRepository<FileElement, String> {
}
