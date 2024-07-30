package org.tutu.springframework.beans.factory.config;

import org.tutu.springframework.beans.factory.HierarchicalBeanFactory;
/**
 * 大多数 bean 工厂都要实现的配置接口。除了 BeanFactory接口中的 bean 工厂客户端方法外，还提供配置 bean 工厂的工具。
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry{
    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例对象
     */
    void destroySingletons();
}
