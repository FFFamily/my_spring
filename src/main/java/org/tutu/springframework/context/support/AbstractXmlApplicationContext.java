package org.tutu.springframework.context.support;

import org.tutu.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.tutu.springframework.beans.factory.xml.XmlBeanDefinitionReader;
// 上下文中对配置信息的加载
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        // xml 读取器
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations){
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    /**
     * 此方法是为了从入口上下文类，拿到配置信息的地址描述
     */
    protected abstract String[] getConfigLocations();
}
