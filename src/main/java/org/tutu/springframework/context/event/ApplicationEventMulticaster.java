package org.tutu.springframework.context.event;

import org.tutu.springframework.context.ApplicationEvent;
import org.tutu.springframework.context.ApplicationListener;

/**
 * 事件广播器

 */
public interface ApplicationEventMulticaster {
    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    void multicastEvent(ApplicationEvent event);
}
