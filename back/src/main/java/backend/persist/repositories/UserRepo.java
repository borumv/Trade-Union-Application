package backend.persist.repositories;

import backend.persist.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User,Long> {
     Optional<User> findByEmail(String email);
}
