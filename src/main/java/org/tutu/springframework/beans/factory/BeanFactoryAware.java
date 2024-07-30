package org.tutu.springframework.beans.factory;

/**
 * 容器感知类
 * 实现此接口，能感知到所属的 BeanFactory
 */
public interface BeanFactoryAware extends Aware{
    void setBeanFactory(BeanFactory beanFactory);
}
