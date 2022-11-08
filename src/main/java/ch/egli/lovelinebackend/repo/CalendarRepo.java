package ch.egli.lovelinebackend.repo;

import java.util.Date;
import java.util.List;

import ch.egli.lovelinebackend.model.Appointment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CalendarRepo extends CrudRepository<Appointment, String> {

	@Query(nativeQuery = true,
			value = "SELECT * FROM appointment a WHERE a.start >= :after ORDER BY a.start ASC LIMIT :lim")
	List<Appointment> getFuture(@Param("after") Date after, @Param("lim") Integer lim);
}
