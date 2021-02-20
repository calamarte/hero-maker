package es.atsistemas.heromaker.exceptions;

import es.atsistemas.heromaker.model.AbstractEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ConstraintViolationException extends RuntimeException {

    public ConstraintViolationException(AbstractEntity entity){
        super("Se ha violado una restricción en la persistencia de " + entity);
    }


    public ConstraintViolationException(Iterable<AbstractEntity> entities){
        super(ConstraintViolationException.formatMessage((List<AbstractEntity>) entities));
    }

    private static String formatMessage(List<AbstractEntity> entities){
        StringBuilder sb = new StringBuilder();
        sb.append("Se ha violado una restricción en la persistencia de: ");

        String stringEntities = entities.stream()
                .map(AbstractEntity::toString)
                .collect(Collectors.joining("\n"));

        sb.append(stringEntities);
        return sb.toString();
    }
}
