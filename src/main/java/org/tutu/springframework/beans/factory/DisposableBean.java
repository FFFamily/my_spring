package org.tutu.springframework.beans.factory;

public interface DisposableBean {
    void destroy() throws Exception;

}
