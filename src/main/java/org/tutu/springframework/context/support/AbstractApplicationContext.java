package org.tutu.springframework.context.support;

import org.tutu.springframework.beans.factory.ConfigurableListableBeanFactory;
import org.tutu.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.tutu.springframework.beans.factory.config.BeanPostProcessor;
import org.tutu.springframework.context.ConfigurableApplicationContext;
import org.tutu.springframework.core.io.DefaultResourceLoader;

import java.util.Map;
import java.util.logging.Logger;

/**
 * 应用上下文抽象类实现：注意，是应用。也就是说该类主要是操作上下文，而非真的是上下文
 * AbstractApplicationContext 继承 DefaultResourceLoader 是为了处理 spring.xml 配置资源的加载。
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {
    @Override
    public void refresh() {
        // 1. 创建 BeanFactory，并加载 BeanDefinition
        refreshBeanFactory();
        // 2. 获取 BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        // 3. 在 Bean 实例化之前，执行 BeanFactoryPostProcessor (Invoke factory processors registered as beans in the context.)
        invokeBeanFactoryPostProcessors(beanFactory);
        // 4. BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);
        // 5. 提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        if (beanFactoryPostProcessorMap == null){
            System.out.println("没有配置对外扩展执行流程");
        }else {
            for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
                beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
            }
        }
    }

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        if (beanPostProcessorMap == null){
            System.out.println("没有配置针对Bean的前置和后置处理器");
        }else {
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
                beanFactory.addBeanPostProcessor(beanPostProcessor);
            }
        }
    }

    protected abstract void refreshBeanFactory();
    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType)  {
        return getBeanFactory().getBean(name, requiredType);
    }


    /**
     * TODO 这个是用来做什么呢？
     */
    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    /**
     * TODO BeanFactory 中没有 destroySingletons 方法
     */
    @Override
    public void close() {
        getBeanFactory().destroySingletons();
    }
}
