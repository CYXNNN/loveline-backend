package ch.egli.lovelinebackend.dto;

import java.util.Date;

public class TokenRefreshResponse {
	public String token;
	public String refreshToken;
	public Date expiresAt;

	public TokenRefreshResponse(String accessToken, String refreshToken, Date expiresAt) {
		this.token = accessToken;
		this.refreshToken = refreshToken;
		this.expiresAt = expiresAt;
	}
}
