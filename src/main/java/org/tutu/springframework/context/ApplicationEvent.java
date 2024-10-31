package org.tutu.springframework.context;

import java.util.EventObject;

/**
 * 定义和发布事件
 * 后续所有事件的类都需要继承这个类
 * ApplicationEvent 是定义事件的抽象类，所有的事件包括关闭、刷新，以及用户自己实现的事件，都需要继承这个类
 */
public abstract class ApplicationEvent extends EventObject {
    public ApplicationEvent(Object source) {
        super(source);
    }
}
