package me.mason.demo.proxy.staticproxy;

import org.springframework.util.StopWatch;

/**
 * 服务代理类：代理类
 *
 * @author: mason
 * @since: 2019/12/19
 **/
public class ServiceProxy implements IService {
    private ServiceImpl serviceImpl;

    public ServiceProxy(ServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void doAction1() {
        System.out.println(" proxy log begin ");
        serviceImpl.doAction1();
        System.out.println(" proxy log end ");
    }

    @Override
    public void doAction2() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("timeCalculation");

        serviceImpl.doAction2();

        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
}
