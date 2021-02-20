package es.atsistemas.heromaker.controllers.crud;

import es.atsistemas.heromaker.model.Hero;
import es.atsistemas.heromaker.services.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hero")
public class HeroController extends AbstractCrudController<Hero, Integer>{

    @Autowired
    protected HeroController(HeroService service){
        super(service);
    }

    @GetMapping("/name/{name}")
    public List<Hero> getByName(@PathVariable String name){
        return ((HeroService) service).findByNameContains(name);
    }

    @GetMapping("/powers")
    public Hero.Power[] getPowers(){
        return Hero.Power.values();
    }

}
