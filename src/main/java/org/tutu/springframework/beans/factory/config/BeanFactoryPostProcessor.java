package org.tutu.springframework.beans.factory.config;

import org.tutu.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * 对外暴露的可扩展的组件
 */
public interface BeanFactoryPostProcessor {
    /**
     * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory);
}
