package org.tutu.springframework.beans.factory.support;

import lombok.Getter;
import org.tutu.springframework.beans.factory.BeanFactory;
import org.tutu.springframework.beans.factory.FactoryBean;
import org.tutu.springframework.beans.factory.config.BeanDefinition;
import org.tutu.springframework.beans.factory.config.BeanPostProcessor;
import org.tutu.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.tutu.springframework.utils.ClassUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * AbstractBeanFactory 集成了
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {
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
        Object sharedInstance = getSingleton(beanName);
        // 判断对象是否被放置在缓存
        if (sharedInstance != null){
            return (T) getObjectForBeanInstance(sharedInstance,beanName);
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        Object bean = createBean(beanName, beanDefinition, args);
        return (T) getObjectForBeanInstance(bean, beanName);
    }

    private Object getObjectForBeanInstance(Object beanInstance,String beanName){
        // TODO 为什么这里要加这一层
        if (!(beanInstance instanceof FactoryBean)){
            return beanInstance;
        }
        Object object = getCachedObjectForFactoryBean(beanName);
        // 先从缓存中获取
        if (object == null) {
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            object = getObjectFromFactoryBean(factoryBean, beanName);
        }
        return object;
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
