package me.mason.demo.proxy.dynamicproxy.jdk;

import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 日志动态代理类：JDK实现
 *
 * @author: mason
 * @since: 2019/12/19
 **/
public class JdkTimeProxyHandler implements InvocationHandler {
    private Object targetObject;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(" jdk dynamic timeCalculation");

        Object result = method.invoke(targetObject, args);

        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
        return result;
    }

    /**
     * 根据委托类动态产生代理类
     * @param targetObject 委托类对象
     * @return 代理类
     */
    public Object createPorxy(Object targetObject){
        this.targetObject = targetObject;
        System.out.println(Arrays.toString(targetObject.getClass().getInterfaces()));
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader()
        , targetObject.getClass().getInterfaces(),this);
    }
}
