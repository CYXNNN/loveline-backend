package ch.egli.lovelinebackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User extends Persistent {

	@Column(unique = true)
	private String username;
	@JsonIgnore
	private String password;
	@Column(unique = true)
	private String mail;
	@Column(unique = true)
	private String phone;
	private Boolean enabled;

}
