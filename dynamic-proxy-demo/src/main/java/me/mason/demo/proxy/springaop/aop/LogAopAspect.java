package me.mason.demo.proxy.springaop.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 日志AOP
 *
 * @author: mason
 * @since: 2019/12/19
 **/
@Aspect
@Component
public class LogAopAspect {

    /**
     * 切点：对service包中所有方法进行织入
     */
    @Pointcut("execution(* me.mason.demo.proxy.springaop.service.*.*(..))")
    private void allServiceMethodPointCut() {
    }

    @Before("allServiceMethodPointCut()")
    public void before(){
        System.out.println(" spring aop log begin ");
    }

    @AfterReturning("allServiceMethodPointCut()")
    public void after(){
        System.out.println(" spring aop log end ");
    }

}
