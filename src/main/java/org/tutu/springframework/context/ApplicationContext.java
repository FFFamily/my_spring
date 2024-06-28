package org.tutu.springframework.context;

import org.tutu.springframework.beans.factory.ListableBeanFactory;

/**
 * 上下文接口
 * 继承于 ListableBeanFactory，也就继承了关于 BeanFactory 方法，比如一些 getBean 的方法
 */
public interface ApplicationContext  extends ListableBeanFactory {
}
