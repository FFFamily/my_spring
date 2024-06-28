package org.tutu.springframework.context;

/**
 * 需要在上下文的实现中完成刷新容器的操作过程
 */
public interface ConfigurableApplicationContext extends ApplicationContext {
    /**
     * 刷新上下文
     */
    void refresh();
}
