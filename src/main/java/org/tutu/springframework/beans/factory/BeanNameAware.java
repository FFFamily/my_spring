package org.tutu.springframework.beans.factory;

public interface BeanNameAware extends Aware{
    void setBeanName(String name);
}
