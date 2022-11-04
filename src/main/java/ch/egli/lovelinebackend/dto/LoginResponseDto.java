package ch.egli.lovelinebackend.dto;

import java.util.Date;

public class LoginResponseDto {

	public Date expiresAt;
	public String token;

	public LoginResponseDto(Date expiresAt, String token) {
		this.expiresAt = expiresAt;
		this.token = token;
	}

}
