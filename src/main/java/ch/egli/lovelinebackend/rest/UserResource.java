package ch.egli.lovelinebackend.rest;

import ch.egli.lovelinebackend.dto.LoginDto;
import ch.egli.lovelinebackend.dto.LoginResponseDto;
import ch.egli.lovelinebackend.security.JwtUtil;
import ch.egli.lovelinebackend.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@CrossOrigin(origins = { "http://localhost:4200/", "http://192.168.0.142:4200" }, maxAge = 3600) // FIXME
@RequestMapping("/api/v1/user")
public class UserResource {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<LoginResponseDto> createAuthenticationToken(@RequestBody LoginDto authenticationRequest)
			throws Exception {

		authenticate(authenticationRequest.username, authenticationRequest.password);

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.username);

		return ResponseEntity.ok(jwtTokenUtil.generateToken(userDetails));
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
