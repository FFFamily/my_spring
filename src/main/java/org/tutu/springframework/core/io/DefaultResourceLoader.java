package org.tutu.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 默认资源加载方式实现
 */
public class DefaultResourceLoader implements ResourceLoader{

    /**
     * 默认资源获取方式
     * 将三种资源获取的方式进行统一，根据不同的类型选择不同的方式
     * @param location 资源地址
     * @return 资源类
     */
    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "Location must not be null");
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        }
        else {
            try {
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                return new FileSystemResource(location);
            }
        }
    }
}
