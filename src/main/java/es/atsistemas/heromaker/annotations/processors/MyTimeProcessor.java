package es.atsistemas.heromaker.annotations.processors;

import es.atsistemas.heromaker.annotations.callbacks.MyTimeFieldCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component
public class MyTimeProcessor implements BeanPostProcessor {

    @Autowired
    private ConfigurableListableBeanFactory configurableBeanFactory;

    protected void scanDataAccessAnnotation(Object bean, String beanName) {
        this.configureFieldInjection(bean);
    }


    private void configureFieldInjection(Object bean) {
        Class<?> managedBeanClass = bean.getClass();
        ReflectionUtils.FieldCallback fieldCallback =
                new MyTimeFieldCallback(configurableBeanFactory, bean);
        ReflectionUtils.doWithFields(managedBeanClass, fieldCallback);
    }
}
