package es.atsistemas.heromaker.exceptions;

import es.atsistemas.heromaker.model.AbstractEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class<? extends AbstractEntity> clazz, Object id){
        super(String.format("No se encuentra %s con la id: %s", clazz.getSimpleName(), id));
    }
}
