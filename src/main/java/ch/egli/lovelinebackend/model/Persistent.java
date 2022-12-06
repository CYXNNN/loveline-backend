package ch.egli.lovelinebackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

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
