package org.tutu.springframework.beans.factory.support;

import org.tutu.springframework.core.io.Resource;
import org.tutu.springframework.core.io.ResourceLoader;

/**
 * 这里需要注意 getRegistry()、getResourceLoader()，都是用于提供给后面三个方法的工具，加载和注册，
 * 这两个方法的实现会包装到抽象类中，以免污染具体的接口实现方法。
 * #
 */
public interface BeanDefinitionReader {
    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    /**
     * 通过资源加载Bean信息
     */
    void loadBeanDefinitions(Resource resource);

    /**
     * 通过多个资源加载Bean信息
     */
    void loadBeanDefinitions(Resource... resources);

    /**
     * 通过某个位置加载Bean信息
     */
    void loadBeanDefinitions(String location);

    /**
     * 通过多个位置加载Bean信息
     */
    void loadBeanDefinitions(String... locations);


}
