package org.tutu.springframework.beans.factory.support;

import org.tutu.springframework.beans.factory.DisposableBean;
import org.tutu.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 默认的单例bean注册主键
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    /**
     * 一级缓存
     */
    private final Map<String, Object> singletonObjects = new HashMap<>();
    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }
    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }
    /**
     * 添加单例Bean
     * @param beanName Bean名称
     * @param singletonObject Bean对象
     */
    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

    /**
     * TODO 明明是 ConfigurableBeanFactory 定义的 destroySingletons 方法，为什么这个类也能做到类似继承实现呢？
     * TODO 本应该是 DefaultListableBeanFactory 类去实现，结果他抛给了父类去实现
     * TODO 这么做的原因是什么？隔离分层服务的设计方式？
     */
    public void destroySingletons() {
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] disposableBeanNames = keySet.toArray();
        for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new RuntimeException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }


}
