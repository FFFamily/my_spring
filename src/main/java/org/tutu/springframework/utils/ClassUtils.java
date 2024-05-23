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
}
