package backend.persist.repositories;

import backend.persist.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

     /**
      * Retrieves an Optional of User object by the specified email.
      *
      * @param email the email of the user
      * @return an Optional of User object
      */
     Optional<User> findByEmail(String email);
}
