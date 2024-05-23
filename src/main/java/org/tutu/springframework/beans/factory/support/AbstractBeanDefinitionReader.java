package org.tutu.springframework.beans.factory.support;

import org.tutu.springframework.core.io.DefaultResourceLoader;
import org.tutu.springframework.core.io.Resource;
import org.tutu.springframework.core.io.ResourceLoader;

/**
 * Bean资源加载抽象类实现
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{
    private  BeanDefinitionRegistry registry;
    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(){

    }
    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }
    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }


}
