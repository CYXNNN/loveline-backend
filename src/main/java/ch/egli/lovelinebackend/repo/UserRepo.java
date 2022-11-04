package ch.egli.lovelinebackend.repo;

import ch.egli.lovelinebackend.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, String> {
	User findByUsername(String username);
}
