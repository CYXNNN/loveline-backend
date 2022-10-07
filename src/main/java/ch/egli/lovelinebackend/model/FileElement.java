package ch.egli.lovelinebackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "file_element")
public class FileElement extends Persistent {

	@Column
	private String mime;

	@Column
	private String filename;

	@Column
	private String path;

	@Column
	private String thumbnailPath;

	@ManyToOne
	@JoinColumn(name="event_id")
	@JsonIgnore
	private Event event;

}
