package es.atsistemas.heromaker.respositories;


import es.atsistemas.heromaker.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
