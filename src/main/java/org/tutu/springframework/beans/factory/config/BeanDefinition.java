package org.tutu.springframework.beans.factory.config;

import lombok.Data;
import org.tutu.springframework.beans.PropertyValues;

@Data
public class BeanDefinition {
    // bean的class信息
    private Class<?> beanClass;
    // bean的属性信息
    private PropertyValues propertyValues;

    /**
     * Bean 的实例化是放在BeanDefinition的构造方法中
     * @param beanClass
     */
    public BeanDefinition(Class<?> beanClass){
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }




}
