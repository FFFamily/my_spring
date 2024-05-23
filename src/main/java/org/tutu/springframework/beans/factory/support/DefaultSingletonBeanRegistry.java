package org.tutu.springframework.beans.factory.support;

import org.tutu.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * 默认的单例bean注册主键
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    /**
     * 一级缓存
     */
    private Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }
}
