package es.atsistemas.heromaker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:compiled.properties"})
public class PropertiesConfiguration {
}
