package ch.egli.lovelinebackend.util;

import ch.egli.lovelinebackend.security.LovedPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

	public static LovedPrincipal getCurrentUser() {
		return (LovedPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
