package ch.egli.lovelinebackend.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
