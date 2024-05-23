package org.tutu.springframework.beans.factory;

import org.tutu.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.tutu.springframework.beans.factory.config.BeanDefinition;
import org.tutu.springframework.beans.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    BeanDefinition getBeanDefinition(String beanName) ;

    void preInstantiateSingletons()  ;

}
