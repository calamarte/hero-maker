package es.atsistemas.heromaker.services;

import es.atsistemas.heromaker.model.Hero;
import es.atsistemas.heromaker.respositories.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HeroService extends AbstractService<Hero, Integer> {

    @Autowired
    protected HeroService(HeroRepository repository) {
        super(repository, Hero.class);
    }

    public Optional<Hero> findByName(String name){
        return ((HeroRepository) repository).findByName(name);
    }
}
