package org.tutu.springframework.beans.factory.support;

import org.tutu.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 利用JDK实现携带参数实例化
 */
public class SimpleInstantiationStrategy  implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> ctor, Object[] args) {
        Class<?> clazz = beanDefinition.getBeanClass();
        try {
            if (ctor != null){
                return clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            }else {
                return clazz.getDeclaredConstructor().newInstance();
            }
        }catch (Exception e){
            throw new RuntimeException("Failed to instantiate [" + clazz.getName() + "]", e);
        }
    }
}
