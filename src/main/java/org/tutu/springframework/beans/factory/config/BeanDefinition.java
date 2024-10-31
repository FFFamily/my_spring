package org.tutu.springframework.beans.factory.config;

import lombok.Data;
import org.tutu.springframework.beans.PropertyValues;

@Data
public class BeanDefinition {
    String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;

    String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;
    // bean的class信息
    private Class<?> beanClass;
    // bean的属性信息
    private PropertyValues propertyValues;
    // 初始化方法
    private String initMethodName;
    // 销毁方法
    private String destroyMethodName;
    // TODO 判断是否是单例这样的方式是不是不行，那我岂不是增加一个类型的时候又要改很多其他地方的代码
    private String scope = SCOPE_SINGLETON;
    // 是否是单例
    private boolean singleton = true;
    // 是否是原型
    private boolean prototype = false;
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


    //在xml注册Bean定义时，通过scope字段来判断是单例还是原型
    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }



}
