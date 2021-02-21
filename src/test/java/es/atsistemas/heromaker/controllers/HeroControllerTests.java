package es.atsistemas.heromaker.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.atsistemas.heromaker.services.HeroService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

//@TODO Aplicar test real
public class HeroControllerTests {

    private final String user = "admin";
    private final String password = "admin";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private HeroService heroService;


    @Test
    public void crud(){

    }






}
