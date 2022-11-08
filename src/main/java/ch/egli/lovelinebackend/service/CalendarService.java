package ch.egli.lovelinebackend.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ch.egli.lovelinebackend.model.Appointment;
import ch.egli.lovelinebackend.repo.CalendarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarService {

	@Autowired
	private CalendarRepo repo;

	public List<Appointment> getFuture(Integer limit) {
		var date = new Date();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		return repo.getFuture(new Date(), limit);
	}

	public List<Appointment> get() {
		var result = new ArrayList<Appointment>();
		repo.findAll().forEach(result::add);

		return result;
	}

	public Appointment post(Appointment appointment) {
		return repo.save(appointment);
	}

}
