package org.tutu.springframework.test.bean;

import lombok.Getter;
import org.tutu.springframework.beans.factory.BeanClassLoaderAware;
import org.tutu.springframework.beans.factory.BeanFactory;
import org.tutu.springframework.beans.factory.BeanFactoryAware;
import org.tutu.springframework.beans.factory.BeanNameAware;
import org.tutu.springframework.context.ApplicationContext;
import org.tutu.springframework.context.ApplicationContextAware;
@Getter
public class AwareService implements BeanNameAware, BeanClassLoaderAware, ApplicationContextAware, BeanFactoryAware {
    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;
    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)  {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Bean Name is：" + name);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("ClassLoader：" + classLoader);
    }
}
