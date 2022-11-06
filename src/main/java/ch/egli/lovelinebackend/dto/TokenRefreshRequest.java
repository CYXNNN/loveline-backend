package ch.egli.lovelinebackend.dto;

import javax.validation.constraints.NotBlank;

public class TokenRefreshRequest {
	@NotBlank
	public String refreshToken;
}
