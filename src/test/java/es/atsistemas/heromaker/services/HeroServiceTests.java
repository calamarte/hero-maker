package es.atsistemas.heromaker.services;

import es.atsistemas.heromaker.model.Hero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;

@SpringBootTest
public class HeroServiceTests {

    @Autowired
    private HeroService heroService;

    @Test
    void crud(){

        Hero hulk = new Hero("Hulk", Hero.Power.SUPER_STRENGTH);
        Hero savedHulk = heroService.save(hulk);

        //Save
        Assertions.assertNotNull(savedHulk.getId());
        Assertions.assertTrue(equalValues(hulk, savedHulk));

        //Update
        savedHulk.setName("Hulka");
        savedHulk = heroService.save(savedHulk);

        Assertions.assertNotEquals(savedHulk.getName(), "Hulk");

        //FindAll
        List<Hero> heros = (List<Hero>) heroService.findAll();

        Assertions.assertTrue(heros.contains(savedHulk));

        //FindById
        Assertions.assertEquals(savedHulk, heroService.findById(savedHulk.getId()));

        //FindByName
        heros = heroService.findByNameContains("uLK");
        Assertions.assertTrue(heros.contains(savedHulk));

        //Delete
        heroService.delete(savedHulk.getId());
        heros = (List<Hero>) heroService.findAll();

        Assertions.assertFalse(heros.contains(savedHulk));

    }

    private static boolean equalValues(Hero hero1, Hero hero2){
        return Objects.equals(hero1.getName(), hero2.getName())
                && Objects.equals(hero1.getPower(), hero2.getPower());
    }



}
