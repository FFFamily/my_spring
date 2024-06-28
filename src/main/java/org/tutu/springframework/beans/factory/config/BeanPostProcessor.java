package org.tutu.springframework.beans.factory.config;

/**
 * 定义扩展的通用顶层通用方法
 */
public interface BeanPostProcessor {

    /**
     * 在 Bean 对象执行初始化方法之前，执行此方法
     *
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) ;

    /**
     * 在 Bean 对象执行初始化方法之后，执行此方法
     */
    Object postProcessAfterInitialization(Object bean, String beanName);
}
