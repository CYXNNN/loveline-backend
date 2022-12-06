package ch.egli.lovelinebackend.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Date;

@Getter
@Setter
@Entity
public class Appointment extends Persistent {

	@Column(nullable = false)
	private String title;
	@Column
	private String description;
	@Column
	private String location;

	@Column(nullable = false)
	private Date start;
	@Column(nullable = false)
	private Date end;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private AppointmentType category;

	@Column
	private String recurrence;

	@Column
	private boolean allDay;

}
