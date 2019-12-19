package me.mason.demo.proxy;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射测试类
 *
 * @author: mason
 * @since: 2019/12/19
 **/
class ReflectionTest {

    @Test
    void testReflection() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //加载类
        Class<?> refClass = Class.forName("me.mason.demo.proxy.refrection.ReflectionService");
        //生成类对象
        Object refClassObject = refClass.getConstructor().newInstance();
        //调用类对象方法
        Method method = refClass.getDeclaredMethod("doSomething");
        method.invoke(refClassObject);
    }
}