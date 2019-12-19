package me.mason.demo.proxy.staticproxy;

/**
 * 服务实现类：委托类
 *
 * @author: mason
 * @since: 2019/12/19
 **/
public class ServiceImpl implements IService {
    @Override
    public void doAction1() {
        System.out.println(" do action1 ");
    }

    @Override
    public void doAction2() {
        System.out.println(" do action2 ");
    }
}
