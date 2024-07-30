package org.tutu.springframework.context.support;

import org.tutu.springframework.beans.factory.config.BeanPostProcessor;
import org.tutu.springframework.context.ApplicationContext;
import org.tutu.springframework.context.ApplicationContextAware;

/**
 * 包装处理器
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {
    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 由于 ApplicationContext 的获取并不能直接在创建 Bean 时候就可以拿到
     * 所以需要在 refresh 操作时，把 ApplicationContext 写入到一个包装的 BeanPostProcessor 中去，
     * 再由 AbstractAutowireCapableBeanFactory.applyBeanPostProcessorsBeforeInitialization 方法调用
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        // 在 Bean 初始化之前将 applicationContext 放进去
        if (bean instanceof ApplicationContextAware){
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
