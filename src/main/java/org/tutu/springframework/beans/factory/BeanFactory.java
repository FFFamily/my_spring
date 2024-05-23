package org.tutu.springframework.beans.factory;

import org.tutu.springframework.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * Bean工厂
 */
public interface BeanFactory {
    /**
     * 获取Bean
     * @param beanName Bean名称
     * @return
     */
    Object getBean(String beanName);

    /**
     * 获取含有入参信息的Bean
     * @param beanName Bean名称
     * @param args 入参
     * @return
     */
    Object getBean(String beanName,Object... args);

    /**
     * 获取指定类型的Bean
     * @param name
     * @param requiredType
     * @return
     * @param <T>
     */
     <T> T getBean(String name, Class<T> requiredType);
}
