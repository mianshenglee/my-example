package me.mason.demo.proxy.dynamicproxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 日志动态代理：cglib实现
 *
 * @author: mason
 * @since: 2019/12/19
 **/
public class CglibLogProxyInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println(" cglib dynamic proxy log begin ");
        Object result = methodProxy.invokeSuper(object, args);
        System.out.println(" cglib dynamic proxy log begin ");
        return result;
    }

    /**
     * 动态创建代理
     *
     * @param cls 委托类
     * @return
     */
    public static <T> T createProxy(Class<T> cls) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(cls);
        enhancer.setCallback(new CglibLogProxyInterceptor());
        return (T) enhancer.create();
    }
}
