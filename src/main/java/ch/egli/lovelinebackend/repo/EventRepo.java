package ch.egli.lovelinebackend.repo;

import java.util.List;

import ch.egli.lovelinebackend.model.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EventRepo extends CrudRepository<Event, String> {
	List<Event> findAllByOrderByDateDesc();

	@Query(nativeQuery = true, value = "SELECT * FROM event ORDER BY rand() LIMIT 1")
	Event getRandom();
}
