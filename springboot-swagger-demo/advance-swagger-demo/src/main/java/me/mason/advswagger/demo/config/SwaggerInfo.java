package me.mason.advswagger.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


/**
 * swagger配置信息
 * @author mason
 * @date 2019/10/31
 */
@Component
@ConfigurationProperties(prefix = "swagger")
@PropertySource("classpath:/config/swagger.properties")
@Data
public class SwaggerInfo {
    private String basePackage;
    private String antPath;
    private String title = "HTTP API";
    private String description = "Swagger 自动生成接口文档";
    private String version ;
    private Boolean enable;
    private String contactName;
    private String contactEmail;
    private String contactUrl;
    private String license;
    private String licenseUrl;
}
