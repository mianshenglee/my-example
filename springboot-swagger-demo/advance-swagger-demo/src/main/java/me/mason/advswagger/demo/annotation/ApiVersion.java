package me.mason.advswagger.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口版本管理注解
 *
 * @author mason
 * @since 2019/11/15
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ApiVersion {
    /**
     * 接口版本号(对应swagger中的group)
     * @return String[]
     */
    String[] group();
}
