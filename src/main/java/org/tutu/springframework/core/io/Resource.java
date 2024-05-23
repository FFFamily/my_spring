package org.tutu.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 资源加载接口的定义
 *
 */
public interface Resource {
    /**
     * 获取对应资源的输入流
     */
    InputStream getInputStream() throws IOException;
}
