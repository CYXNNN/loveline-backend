package ch.egli.lovelinebackend.dto;

import java.util.Date;

public class LoginResponseDto {

	public Date expiresAt;
	public String token;
	public String name;

	public LoginResponseDto(Date expiresAt, String token, String name) {
		this.expiresAt = expiresAt;
		this.token = token;
		this.name = name;
	}

}
