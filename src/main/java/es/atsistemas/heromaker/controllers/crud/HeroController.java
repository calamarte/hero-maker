package es.atsistemas.heromaker.controllers.crud;

import es.atsistemas.heromaker.model.Hero;
import es.atsistemas.heromaker.services.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hero")
public class HeroController extends AbstractCrudController<Hero, Integer>{

    @Autowired
    protected HeroController(HeroService service){
        super(service);
    }

}
