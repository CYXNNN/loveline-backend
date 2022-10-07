package ch.egli.lovelinebackend.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import ch.egli.lovelinebackend.model.Event;
import ch.egli.lovelinebackend.model.EventType;

public class EventCreationDto {
	public String title;
	public String description;
	public LocalDateTime date;
	public EventType type;

	public Event toEntity() {
		var entity = new Event();
		entity.setTitle(title);
		entity.setDescription(description);
		entity.setDate(date);
		entity.setType(type);

		entity.setId(UUID.randomUUID().toString());
		entity.setCreated(LocalDateTime.now());
		entity.setUpdated(LocalDateTime.now());

		return entity;
	}
}
