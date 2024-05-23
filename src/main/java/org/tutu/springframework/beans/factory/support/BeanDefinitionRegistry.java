package org.tutu.springframework.beans.factory.support;

import org.tutu.springframework.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {


    /**
     * 注册 BeanDefinition
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 查询是否注册过 BeanDefinition
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);
}
