package org.tutu.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import org.tutu.springframework.beans.PropertyValue;
import org.tutu.springframework.beans.factory.config.BeanDefinition;
import org.tutu.springframework.beans.factory.config.BeanReference;
import org.tutu.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import org.tutu.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.tutu.springframework.core.io.Resource;
import org.tutu.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * xml处理bean注册
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) {
        try {
            try (InputStream inputStream = resource.getInputStream()) {
                doLoadBeanDefinitions(inputStream);
            }
        } catch ( Exception e) {
            throw new RuntimeException("IOException parsing XML document from " + resource, e);
        }
    }



    @Override
    public void loadBeanDefinitions(Resource... resources) {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(String... locations) {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }

    private void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        // 读取xml文件
        Document document = XmlUtil.readXML(inputStream);
        // 获取文档组件
        Element root = document.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
        // 遍历解析
        for (int i = 0; i < childNodes.getLength(); i++) {
            if (!(childNodes.item(i) instanceof Element bean)){
                // 如果子节点不是组件，直接跳过
                continue;
            }
            if (!("bean".equals(childNodes.item(i).getNodeName()))){
                // 只解析 bean 节点
                continue;
            }
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");
            // 获取 Class，方便获取类中的名称
            Class<?> clazz = Class.forName(className);
            // 优先级 id > name
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)) {
                // 都为空取类名
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }
            // 定义Bean
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            // 属性填充
            for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                if (!(bean.getChildNodes().item(j) instanceof Element property)) continue;
                if (!"property".equals(bean.getChildNodes().item(j).getNodeName())) continue;
                // 属性名
                String attrName = property.getAttribute("name");
                // 属性值
                String attrValue = property.getAttribute("value");
                // 对象引用
                String attrRef = property.getAttribute("ref");
                // 获取属性值：引入对象、值对象
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                // 创建属性信息
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if (getRegistry().containsBeanDefinition(beanName)){
                throw new RuntimeException("Duplicate beanName[" + beanName + "] is not allowed");
            }
            // 注册 BeanDefinition
            getRegistry().registerBeanDefinition(beanName,beanDefinition);
        }
    }
}
