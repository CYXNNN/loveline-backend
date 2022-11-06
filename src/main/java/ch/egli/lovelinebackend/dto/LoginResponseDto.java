package ch.egli.lovelinebackend.dto;

import java.util.Date;

import ch.egli.lovelinebackend.model.User;

public class LoginResponseDto {

	public Date expiresAt;
	public String token;
	public String refreshToken;
	public User user;

	public LoginResponseDto(Date expiresAt, String token) {
		this.expiresAt = expiresAt;
		this.token = token;
	}
}
