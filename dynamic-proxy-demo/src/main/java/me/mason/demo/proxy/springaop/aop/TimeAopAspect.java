package me.mason.demo.proxy.springaop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * 计时AOP
 *
 * @author: mason
 * @since: 2019/12/19
 **/
@Aspect
@Component
public class TimeAopAspect {

    /**
     * 切点：对controller包中所有方法进行织入
     */
    @Pointcut("execution(* me.mason.demo.proxy.springaop.controller.*.*(..))")
    private void allControllerMethodPointCut() {
    }


    /**
     * 环绕通知
     * @param proceedingJoinPoint 连接点
     * @return
     * @throws Throwable
     */
    @Around("allControllerMethodPointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(" spring aop around timeCalculation");

        try {
            //若方法有返回值，需要返回调用结果
            return proceedingJoinPoint.proceed();
        } finally {
            stopWatch.stop();
            System.out.println(stopWatch.prettyPrint());
        }
    }

}
