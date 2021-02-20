package es.atsistemas.heromaker.listeners;

import es.atsistemas.heromaker.model.Hero;
import es.atsistemas.heromaker.model.User;
import es.atsistemas.heromaker.services.HeroService;
import es.atsistemas.heromaker.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class StartUpListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(StartUpListener.class);

    @Autowired
    private HeroService heroService;

    @Autowired
    private UserService userService;

    @Value("#{'${users.start.list:admin-admin}'.split(',')}")
    private List<String> users;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<User> users = this.users.stream()
                .filter(Objects::nonNull)
                .map((u) -> {
                    String[] split = u.split("-");
                    return split.length == 2 ? new User(split[0], split[1]) : null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        users = (List<User>) userService.saveAll(users);
        logger.info("Creados los usuarios: " + users);

        List<Hero> heroList = Arrays.asList(
                new Hero("Flash", Hero.Power.SUPER_VELOCITY),
                new Hero("Luke Cage", Hero.Power.SUPER_STRENGTH),
                new Hero("Batman", Hero.Power.SUPER_RICH),
                new Hero("KICK-ASS", Hero.Power.NO_POWER),
                new Hero("Jesus", Hero.Power.ABSOLUTE)
        );

        heroList = (List<Hero>) heroService.saveAll(heroList);
        logger.info("Insertados los siguientes super heroes: " + heroList);
    }
}
