package me.mason.demo.proxy.dynamicproxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 日志动态代理类：JDK实现
 *
 * @author: mason
 * @since: 2019/12/19
 **/
public class JdkLogProxyHandler implements InvocationHandler {
    private Object targetObject;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(" jdk dynamic proxy log begin ");
        Object result = method.invoke(targetObject, args);
        System.out.println(" jdk dynamic proxy log end ");
        return result;
    }

    /**
     * 根据委托类动态产生代理类
     * @param targetObject 委托类对象
     * @return 代理类
     */
    public Object createPorxy(Object targetObject){
        this.targetObject = targetObject;
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader()
        ,targetObject.getClass().getInterfaces(),this);
    }
}
