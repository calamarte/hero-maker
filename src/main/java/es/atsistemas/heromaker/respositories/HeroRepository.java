package es.atsistemas.heromaker.respositories;

import es.atsistemas.heromaker.model.Hero;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HeroRepository extends CrudRepository<Hero, Integer> {

    Optional<Hero> findByName(String name);

}
