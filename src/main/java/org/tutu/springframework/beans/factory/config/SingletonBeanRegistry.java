package org.tutu.springframework.beans.factory.config;

/**
 * 单例注册接口
 */
public interface SingletonBeanRegistry {
    /**
     * 获取对应Bean的单例对象
     * @param name
     * @return
     */
    Object getSingleton(String name);

    /**
     * 注册
     */
    void registerSingleton(String beanName, Object singletonObject);
}
