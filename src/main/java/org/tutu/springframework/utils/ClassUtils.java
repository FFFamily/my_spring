package org.tutu.springframework.utils;

public class ClassUtils {

    /**
     * TODO 没明白获取当前线程的一个类加载器是什么意思
     */
    public static ClassLoader getDefaultClassLoader(){
        ClassLoader classLoader = null;
        try{
            classLoader = Thread.currentThread().getContextClassLoader();
        }catch (Throwable ex){

        }
        if (classLoader == null){
            classLoader = ClassUtils.class.getClassLoader();
        }
        return classLoader;
    }

    /**
     * Check whether the specified class is a CGLIB-generated class.
     * @param clazz the class to check
     */
    public static boolean isCglibProxyClass(Class<?> clazz) {
        return (clazz != null && isCglibProxyClassName(clazz.getName()));
    }

    /**
     * Check whether the specified class name is a CGLIB-generated class.
     * @param className the class name to check
     */
    public static boolean isCglibProxyClassName(String className) {
        return (className != null && className.contains("$$"));
    }
}
