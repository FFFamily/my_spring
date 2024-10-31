package org.tutu.springframework.context.event;

import org.tutu.springframework.context.ApplicationContext;
import org.tutu.springframework.context.ApplicationEvent;

/**
 *
 */
public class ApplicationContextEvent extends ApplicationEvent {
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        // todo 这个EventObject中的getSource是做什么用的？
        return (ApplicationContext) getSource();
    }
}
