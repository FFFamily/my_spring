package org.tutu.springframework.context.support;

/**
 * ClassPathXmlApplicationContext，是具体对外给用户提供的应用上下文方法。
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext{
    private String[] configLocations;

    public ClassPathXmlApplicationContext() {
    }

    /**
     * 从 XML 中加载 BeanDefinition，并刷新上下文
     */
    public ClassPathXmlApplicationContext(String configLocations)  {
        this(new String[]{configLocations});
    }

    /**
     * 从 XML 中加载 BeanDefinition，并刷新上下文
     */
    public ClassPathXmlApplicationContext(String[] configLocations)  {
        this.configLocations = configLocations;
        // 调用的 AbstractApplicationContext 中的 refresh，刷新上下文
        // 这里拿到 config 的路径后就可以进行上下文的构建了
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }
}
