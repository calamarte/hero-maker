package es.atsistemas.heromaker.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Hero extends AbstractEntity<Integer>{

    //Posibles poderes
    public enum Power{
        SUPER_VELOCITY,
        SUPER_STRENGTH,
        SUPER_ELASTIC,
        SUPER_RICH,
        INVISIBILITY,
        TELEPORT,
        ABSOLUTE,
        NO_POWER
    }

    private String name;

    @Enumerated(EnumType.STRING)
    private Power power = Power.NO_POWER;

    public Hero(){}

    public Hero(String name, Power power){
        this.name = name;
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Power getPower() {
        return power;
    }

    public void setPower(Power power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", power=" + power +
                '}';
    }
}
