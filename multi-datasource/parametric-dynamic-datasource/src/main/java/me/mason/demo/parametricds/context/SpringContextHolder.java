package me.mason.demo.parametricds.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring上下文
 *
 * @author: mason
 * @since: 2020/1/9
 **/
@Component
public class SpringContextHolder implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }

    /**
     * 返回上下文
     * @return
     */
    public static ApplicationContext getContext(){
        return SpringContextHolder.applicationContext;
    }
}
