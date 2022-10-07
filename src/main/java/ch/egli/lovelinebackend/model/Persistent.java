package ch.egli.lovelinebackend.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public class Persistent {
	@Id
	@Column(length = 100)
	private String id;

	@Column
	private LocalDateTime created;

	@Column
	private LocalDateTime updated;
}
