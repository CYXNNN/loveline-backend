package ch.egli.lovelinebackend.dto;

import jakarta.validation.constraints.NotBlank;

public class TokenRefreshRequest {
	@NotBlank
	public String refreshToken;
}
