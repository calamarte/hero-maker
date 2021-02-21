package es.atsistemas.heromaker.annotations.callbacks;

import es.atsistemas.heromaker.annotations.MyTime;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

public class MyTimeFieldCallback implements ReflectionUtils.FieldCallback {

    private ConfigurableListableBeanFactory configurableBeanFactory;
    private Object bean;

    public MyTimeFieldCallback(ConfigurableListableBeanFactory bf, Object bean) {
        this.configurableBeanFactory = bf;
        this.bean = bean;
    }

    @Override
    public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
            if (!field.isAnnotationPresent(MyTime.class)) {
                return;
            }

            // TODO implementación del Tag

        }

}
