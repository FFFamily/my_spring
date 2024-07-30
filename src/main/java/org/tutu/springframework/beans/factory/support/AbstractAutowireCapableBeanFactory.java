package org.tutu.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.Setter;
import org.tutu.springframework.beans.PropertyValue;
import org.tutu.springframework.beans.PropertyValues;
import org.tutu.springframework.beans.factory.DisposableBean;
import org.tutu.springframework.beans.factory.InitializingBean;
import org.tutu.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.tutu.springframework.beans.factory.config.BeanDefinition;
import org.tutu.springframework.beans.factory.config.BeanPostProcessor;
import org.tutu.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 实例化 bean
 * 实现了 AutowireCapableBeanFactory，意味着这个类具备实例化Bean之前和之后的操作方法
 * 继承了 AbstractBeanFactory，意味着本身也是BeanFactory，具备一系列操作Bean的方法
 */
@Setter
@Getter
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    /**
     * 根据Bean配置 beanDefinition 创建 Bean
     * @param beanName Bean名称
     * @param beanDefinition Bean类配置
     * @return Bean
     */
//    @Override
//    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
//        Object bean = null;
//        try {
//            bean = beanDefinition.getBeanClass().getConstructor().newInstance();
//        }catch (Exception e){
//            throw new RuntimeException("实例化Bean出错啦");
//        }
//        addSingleton(beanName,bean);
//        return bean;
//    }

    /**
     * 根据Bean配置 beanDefinition以及Bean 构造方法参数 创建 Bean
     * @param beanName Bean名称
     * @param beanDefinition Bean类配置
     * @param args Bean 构造方法参数
     * @return Bean
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Object bean;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);
            // 属性填充
            applyPropertyValues(beanName,bean,beanDefinition);
            // 执行 Bean 的初始化方法和 BeanPostProcessor 的前置和后置处理方法
            bean = initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new RuntimeException("Instantiation of bean failed", e);
        }
        // 注册实现了 DisposableBean 接口的 Bean 对象
        // 在创建 Bean 对象的实例的时候，需要把销毁方法保存起来，方便后续执行销毁动作进行调用
        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);
        // 加入缓存
        addSingleton(beanName, bean);
        return bean;
    }



    /**
     * 初始化Bean
     * @param beanName bean 名称
     * @param bean bean
     * @param beanDefinition bean 配置
     */
    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {
        // 1. 执行 BeanPostProcessor Before 处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);
        // 待完成内容：invokeInitMethods(beanName, wrappedBean, beanDefinition);
        invokeInitMethods(beanName, wrappedBean, beanDefinition);
        // 2. 执行 BeanPostProcessor After 处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        return wrappedBean;
    }

    /**
     * 执行Bean的前置初始化方法
     */
    private void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {
        // 1. 实现接口 InitializingBean
        // TODO 为什么这里的bean可能是 InitializingBean 的子类呢？，这个Bean 到底是什么？
        if(bean instanceof InitializingBean ){
            ((InitializingBean) bean).afterPropertiesSet();
        }
        // 2. 配置信息 init-method {判断是为了避免二次执行销毁}
        String initMethodName = beanDefinition.getInitMethodName();
        if (StrUtil.isNotEmpty(initMethodName)){
            Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
            initMethod.invoke(bean);
        }
    }
    /**
     * 判断是否需要注册销毁方法对象
     * @param beanName
     * @param bean
     * @param beanDefinition
     */
    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
    }



    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor<?> constructor = null;
        Constructor<?>[] declaredConstructors = beanDefinition.getBeanClass().getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            if (args != null && declaredConstructor.getParameterTypes().length == args.length){
                constructor = declaredConstructor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition,beanName,constructor,args);
    }

    /**
     * Bean 属性填充
     */
    protected void applyPropertyValues(String beanName,Object bean,BeanDefinition beanDefinition){
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                if (value instanceof BeanReference){
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                // 属性填充
                BeanUtil.setProperty(bean,name,value);
            }
        }catch (Exception e){
            throw new RuntimeException("Error setting property values：" + beanName);
        }

    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName){
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName)  {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }

}
