package ch.egli.lovelinebackend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.Setter;

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
