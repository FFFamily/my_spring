package org.tutu.springframework.beans.factory.support;

import org.tutu.springframework.beans.factory.BeanFactory;
import org.tutu.springframework.beans.factory.config.BeanDefinition;
import org.tutu.springframework.beans.factory.config.BeanPostProcessor;
import org.tutu.springframework.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * AbstractBeanFactory 集成了
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();
    @Override
    public Object getBean(String beanName) {
        Object bean = getSingleton(beanName);
        // 判断对象是否被放置在缓存
        if (bean != null){
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName,beanDefinition);

    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) {
        return null;
    }

    @Override
    public Object getBean(String beanName, Object... args) {
        return null;
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    /** 以下为模板方法 */
    protected abstract BeanDefinition getBeanDefinition(String beanName);
    protected abstract Object createBean(String beanName,BeanDefinition beanDefinition);
    protected abstract Object createBean(String beanName,BeanDefinition beanDefinition,Object[] args);
}
