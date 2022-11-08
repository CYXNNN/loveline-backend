package ch.egli.lovelinebackend.rest;

import java.util.List;

import javax.validation.Valid;

import ch.egli.lovelinebackend.dto.AppointmentCreationDto;
import ch.egli.lovelinebackend.model.Appointment;
import ch.egli.lovelinebackend.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@CrossOrigin
@RequestMapping("/api/v1/calendar")
public class CalendarResource {

	@Autowired
	private CalendarService service;

	@GetMapping("/future/{limit}")
	public HttpEntity<List<Appointment>> getFuture(@PathVariable("limit") Integer limit) {
		return new HttpEntity<>(service.getFuture(limit));
	}

	@GetMapping
	public HttpEntity<List<Appointment>> get() {
		return new HttpEntity<>(service.get());
	}

	@RequestMapping(path = "",
			method = RequestMethod.POST,
			consumes = "application/json",
			produces = "application/json")
	public HttpEntity<Appointment> post(@RequestBody @Valid AppointmentCreationDto dto) {

		var entity = service.post(dto.toEntity());

		return new HttpEntity<>(entity);
	}
}
