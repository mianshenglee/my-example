package me.mason.demo.proxy;

import me.mason.demo.proxy.dynamicproxy.jdk.JdkLogProxyHandler;
import me.mason.demo.proxy.dynamicproxy.jdk.JdkTimeProxyHandler;
import me.mason.demo.proxy.staticproxy.IService;
import me.mason.demo.proxy.staticproxy.ServiceImpl;
import org.junit.jupiter.api.Test;

/**
 * jdk动态代理测试类
 *
 * @author: mason
 * @since: 2019/12/19
 **/
class JdkDynamicProxyTest {

    @Test
    void testLogProxy() {
        JdkLogProxyHandler logProxyHandler = new JdkLogProxyHandler();
        IService proxy = (IService)logProxyHandler.createPorxy(new ServiceImpl());
        proxy.doAction1();
        System.out.println("############");
        proxy.doAction2();
    }

    @Test
    void testTimeProxy() {
        JdkTimeProxyHandler timeProxyHandler = new JdkTimeProxyHandler();
        timeProxyHandler.createPorxy(new ServiceImpl());
        IService proxy = (IService)timeProxyHandler.createPorxy(new ServiceImpl());
        proxy.doAction1();
        System.out.println("############");
        proxy.doAction2();
    }
}