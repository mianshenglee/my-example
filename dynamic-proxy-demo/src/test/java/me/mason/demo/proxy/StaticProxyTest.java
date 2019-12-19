package me.mason.demo.proxy;

import me.mason.demo.proxy.staticproxy.ServiceImpl;
import me.mason.demo.proxy.staticproxy.ServiceProxy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 静态代理测试类
 *
 * @author: mason
 * @since: 2019/12/19
 **/
class StaticProxyTest {

    @Test
    void testLogProxy() {
        ServiceProxy serviceProxy = new ServiceProxy(new ServiceImpl());
        serviceProxy.doAction1();
    }

    @Test
    void testTimeProxy() {
        ServiceProxy serviceProxy = new ServiceProxy(new ServiceImpl());
        serviceProxy.doAction2();
    }
}