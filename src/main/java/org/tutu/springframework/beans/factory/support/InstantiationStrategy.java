package org.tutu.springframework.beans.factory.support;

import org.tutu.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 实例化策略接口
 * 之所以要这么做，是考虑到bean可能有多种构造参数
 */
public interface InstantiationStrategy {
    /**
     * 将对应的 beanDefinition 进行实例化
     * @param beanDefinition Bean信息
     * @param beanName Bean 名称
     * @param ctor 构造方法
     * @param args 对应构造方法的入参
     * @return
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> ctor,Object[] args);
}
