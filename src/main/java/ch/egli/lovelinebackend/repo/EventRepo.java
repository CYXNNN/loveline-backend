package ch.egli.lovelinebackend.repo;

import java.util.List;

import ch.egli.lovelinebackend.model.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepo extends CrudRepository<Event, String> {
	List<Event> findAllByOrderByDateDesc();
}
