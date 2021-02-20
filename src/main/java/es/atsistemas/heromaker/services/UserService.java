package es.atsistemas.heromaker.services;

import es.atsistemas.heromaker.model.User;
import es.atsistemas.heromaker.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends AbstractService<User, Integer> {

    @Autowired
    protected UserService(UserRepository repository) {
        super(repository, User.class);
    }

    public Optional<User> findByUsername(String username){
        return ((UserRepository)repository).findByUsername(username);
    }

}
