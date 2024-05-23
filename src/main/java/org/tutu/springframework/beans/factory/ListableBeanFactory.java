package org.tutu.springframework.beans.factory;


import java.util.Map;

public interface ListableBeanFactory extends BeanFactory {
    /**
     * 按照类型返回 Bean 实例
     */
    <T> Map<String, T> getBeansOfType(Class<T> type);

    /**
     * Return the names of all beans defined in this registry.
     *
     * 返回注册表中所有的Bean名称
     */
    String[] getBeanDefinitionNames();
}
