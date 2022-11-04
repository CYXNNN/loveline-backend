package ch.egli.lovelinebackend.model;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User extends Persistent {

	private String username;
	private String password;

}
