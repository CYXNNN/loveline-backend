package ch.egli.lovelinebackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table
public class Event extends Persistent {

	@Column
	private String title;

	@Column
	private String description;

	@Column
	private LocalDateTime date;

	@Column
	@Enumerated(EnumType.STRING)
	private EventType type;

	@Column
	private String thumbnailPath;

	@OneToMany(mappedBy = "event")
	List<FileElement> elements;

	@JsonIgnore
	@Transient
	public void addFile(FileElement file) {
		if (elements == null) {
			elements = new ArrayList<>();
		}

		elements.add(file);
	}
}
