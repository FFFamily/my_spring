package org.tutu.springframework.beans.factory;

/**
 * TODO 这类是做什么用的
 * @param <T>
 */
public interface FactoryBean<T> {
    // 获取对象
    T getObject() throws Exception;
    // 获取对象类型
    Class<?> getObjectType();
    // 是否单例
    boolean isSingleton();

}
