package org.tutu.springframework.beans.factory;

public interface InitializingBean {
    /**
     * Bean 处理了属性填充后调用
     */
    void afterPropertiesSet() throws Exception;
}
