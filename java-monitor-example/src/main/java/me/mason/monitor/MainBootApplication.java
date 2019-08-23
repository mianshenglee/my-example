package me.mason.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 程序入口
 *
 * @author mason
 * @since 2019/8/16
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MainBootApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MainBootApplication.class);
        app.run(args);
    }
}
