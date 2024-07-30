package org.tutu.springframework.beans.factory.support;

import lombok.Getter;
import org.tutu.springframework.beans.factory.BeanFactory;
import org.tutu.springframework.beans.factory.config.BeanDefinition;
import org.tutu.springframework.beans.factory.config.BeanPostProcessor;
import org.tutu.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.tutu.springframework.utils.ClassUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * AbstractBeanFactory 集成了
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {
    @Getter
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();
    /**
     * ClassLoader to resolve bean class names with, if necessary
     */
    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();
    @Override
    public Object getBean(String beanName) {
        return doGetBean(beanName,null);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) {
        return (T) getBean(name);
    }

    @Override
    public Object getBean(String beanName, Object... args) {
        return doGetBean(beanName,args);
    }

    protected <T> T doGetBean(final String beanName, final Object[] args){
        Object bean = getSingleton(beanName);
        // 判断对象是否被放置在缓存
        if (bean != null){
            return (T) bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return (T) createBean(beanName,beanDefinition,args);
    }

    /**
     * 获取 Bean 的 Classloader
     */
    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }


    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    /** 以下为模板方法 */
    protected abstract BeanDefinition getBeanDefinition(String beanName);
//    protected abstract Object createBean(String beanName,BeanDefinition beanDefinition);
    protected abstract Object createBean(String beanName,BeanDefinition beanDefinition,Object[] args);
}
