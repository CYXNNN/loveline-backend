package ch.egli.lovelinebackend.repo;

import ch.egli.lovelinebackend.model.Appointment;
import org.springframework.data.repository.CrudRepository;

public interface CalendarRepo extends CrudRepository<Appointment, String> {
}
