package org.tutu.springframework.beans.factory;

/**
 * 感知到所属的 ClassLoader
 */
public interface BeanClassLoaderAware extends Aware{
    void setBeanClassLoader(ClassLoader classLoader);
}
