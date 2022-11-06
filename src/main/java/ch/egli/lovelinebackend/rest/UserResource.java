package ch.egli.lovelinebackend.rest;

import javax.validation.Valid;

import ch.egli.lovelinebackend.dto.LoginDto;
import ch.egli.lovelinebackend.dto.LoginResponseDto;
import ch.egli.lovelinebackend.dto.TokenRefreshRequest;
import ch.egli.lovelinebackend.dto.TokenRefreshResponse;
import ch.egli.lovelinebackend.exception.TokenRefreshException;
import ch.egli.lovelinebackend.model.RefreshToken;
import ch.egli.lovelinebackend.security.JwtUtil;
import ch.egli.lovelinebackend.security.LovedPrincipal;
import ch.egli.lovelinebackend.service.JwtUserDetailsService;
import ch.egli.lovelinebackend.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@CrossOrigin
@RequestMapping("/api/v1/user")
public class UserResource {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private RefreshTokenService refreshTokenService;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(path = "/authenticate",
			method = RequestMethod.POST,
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<LoginResponseDto> createAuthenticationToken(@RequestBody LoginDto authenticationRequest)
			throws Exception {

		authenticate(authenticationRequest.username, authenticationRequest.password);

		final var userDetails = (LovedPrincipal) userDetailsService
				.loadUserByUsername(authenticationRequest.username);

		var token = jwtTokenUtil.generateToken(userDetails);
		token.user = userDetails.getUser();

		RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
		token.refreshToken = refreshToken.getToken();

		return ResponseEntity.ok(token);

	}

	@RequestMapping(path = "/refreshtoken",
			method = RequestMethod.POST,
			consumes = "application/json",
			produces = "application/json")
	public HttpEntity<TokenRefreshResponse> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
		String requestRefreshToken = request.refreshToken;

		return refreshTokenService.findByToken(requestRefreshToken)
				.map(refreshTokenService::verifyExpiration)
				.map(RefreshToken::getUser)
				.map(user -> {
					final var userDetails = (LovedPrincipal) userDetailsService
							.loadUserByUsername(user.getUsername());
					var token = jwtTokenUtil.generateToken(userDetails);
					return new HttpEntity(new TokenRefreshResponse(
							token.token,
							requestRefreshToken,
							token.expiresAt));
				})
				.orElseThrow(() -> new TokenRefreshException(
						requestRefreshToken,
						"Refresh token is not in database!"));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
