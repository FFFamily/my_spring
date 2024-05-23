package org.tutu.springframework.core.io;

import cn.hutool.core.lang.Assert;
import org.tutu.springframework.utils.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 类路径资源处理
 */
public class ClassPathResource implements Resource{
    // 类路径
    private String path;
    // 类加载器
    private ClassLoader classLoader;

    public ClassPathResource(String path){
        this(path, null);
    }
    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path, "Path must not be null");
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }

    @Override
    public InputStream getInputStream() throws IOException {
        // 通过 ClassLoader 读取ClassPath 下的文件信息
        InputStream is = classLoader.getResourceAsStream(path);
        if (is == null) {
            throw new FileNotFoundException(this.path + " cannot be opened because it does not exist");
        }
        return is;
    }
}
