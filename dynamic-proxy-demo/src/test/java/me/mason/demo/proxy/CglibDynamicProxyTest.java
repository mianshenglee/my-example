package me.mason.demo.proxy;

import me.mason.demo.proxy.dynamicproxy.cglib.CglibLogProxyInterceptor;
import me.mason.demo.proxy.dynamicproxy.cglib.CglibService;
import me.mason.demo.proxy.dynamicproxy.cglib.CglibTimeProxyInterceptor;
import me.mason.demo.proxy.staticproxy.IService;
import me.mason.demo.proxy.staticproxy.ServiceImpl;
import org.junit.jupiter.api.Test;

/**
 * cglib动态代理测试类
 *
 * @author: mason
 * @since: 2019/12/19
 **/
class CglibDynamicProxyTest {

    @Test
    void testLogProxy() {
        CglibService proxy = CglibLogProxyInterceptor.createProxy(CglibService.class);
        proxy.doAction1();
        System.out.println("############");
        proxy.doAction2();
    }

    @Test
    void testTimeProxy() {
        CglibService proxy = CglibTimeProxyInterceptor.createProxy(CglibService.class);
        proxy.doAction1();
        System.out.println("############");
        proxy.doAction2();
    }
}