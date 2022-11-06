package ch.egli.lovelinebackend.repo;

import java.util.Optional;

import ch.egli.lovelinebackend.model.RefreshToken;
import ch.egli.lovelinebackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Long> {
	Optional<RefreshToken> findByToken(String token);

	@Modifying
	int deleteByUser(User user);
}
