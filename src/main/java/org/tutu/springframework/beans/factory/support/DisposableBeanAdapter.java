package org.tutu.springframework.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import org.tutu.springframework.beans.factory.DisposableBean;
import org.tutu.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * 销毁方法适配器
 * 适配器的原因是因为对象bean的销毁方式有很多，这里只是展示了 DisposableBean 和  destroyMethodName 的方式
 * 那么在 执行销毁操作时并不关心用哪个方法，只需要一个统一的接口
 */
public class DisposableBeanAdapter implements DisposableBean {
    private final Object bean;
    private final String beanName;
    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }


    @Override
    public void destroy() throws Exception {
        if (bean instanceof DisposableBean){
            ((DisposableBean) bean).destroy();
        }
        // 配置信息 destroy-method {判断是为了避免二次执行销毁}
        if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            destroyMethod.invoke(bean);
        }
    }
}
