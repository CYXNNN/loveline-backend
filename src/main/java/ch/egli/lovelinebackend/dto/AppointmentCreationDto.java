package ch.egli.lovelinebackend.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import ch.egli.lovelinebackend.model.Appointment;
import ch.egli.lovelinebackend.model.AppointmentType;

public class AppointmentCreationDto {
	public String title;
	public String description;
	public String location;
	public Date start;
	public Date end;
	public AppointmentType category;
	public String recurrence;
	public boolean allDay;

	public Appointment toEntity() {
		var entity = new Appointment();
		entity.setTitle(title);
		entity.setDescription(description);
		entity.setLocation(location);
		entity.setStart(start);
		entity.setEnd(end);
		entity.setCategory(category);
		entity.setAllDay(allDay);

		entity.setId(UUID.randomUUID().toString());
		entity.setCreated(LocalDateTime.now());
		entity.setUpdated(LocalDateTime.now());

		return entity;
	}
}
