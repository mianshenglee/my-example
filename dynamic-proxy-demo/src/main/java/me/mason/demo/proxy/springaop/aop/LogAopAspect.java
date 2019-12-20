package me.mason.demo.proxy.springaop.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 日志AOP
 *
 * @author: mason
 * @since: 2019/12/19
 **/
@Slf4j
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
    public void before() {
        log.info(" spring aop before log begin ");
    }

    @AfterReturning("allServiceMethodPointCut()")
    public void after() {
        log.info(" spring aop before log end ");
    }

    /**
     * 环绕通知
     *
     * @param proceedingJoinPoint 连接点
     * @return
     * @throws Throwable
     */
    @Around("allServiceMethodPointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info(" spring aop around log begin ");
        try {
            //若方法有返回值，需要返回调用结果
            return proceedingJoinPoint.proceed();
        } finally {
            log.info(" spring aop around log end ");
        }
    }

}
