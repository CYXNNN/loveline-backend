package ch.egli.lovelinebackend.service;

import java.util.ArrayList;
import java.util.List;

import ch.egli.lovelinebackend.model.Appointment;
import ch.egli.lovelinebackend.repo.CalendarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarService {

	@Autowired
	private CalendarRepo repo;

	public List<Appointment> get() {
		var result = new ArrayList<Appointment>();
		repo.findAll().forEach(result::add);

		return result;
	}

	public Appointment post(Appointment appointment) {
		return repo.save(appointment);
	}

}
