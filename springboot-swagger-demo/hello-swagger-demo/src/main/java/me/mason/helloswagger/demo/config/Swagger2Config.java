package me.mason.helloswagger.demo.config;

import cn.hutool.core.util.StrUtil;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * swagger 配置类，访问地址：http://ip:port/swagger-ui.html
 *
 * @author mason
 *
 * @date 2019/6/1
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
	@Autowired
	private SwaggerInfo swaggerInfo;

	/**
	 * 默认分组全部接口
	 * @return
	 */
	@Bean
	public Docket defaultApi() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				//是否启用
				.enable(swaggerInfo.getEnable());
		ApiSelectorBuilder builder = docket.select();
		//api过滤
		builder = builder.apis(Predicates.or(apisFilter()));
		//接口路径过滤
		if (StrUtil.isNotEmpty(swaggerInfo.getAntPath())) {
			builder = builder.paths(PathSelectors.ant(swaggerInfo.getAntPath()));
		}

//		//全局header参数
//		ParameterBuilder tokenPar = new ParameterBuilder();
//		List<Parameter> pars = new ArrayList<Parameter>();
//		tokenPar.name("Authorization").description("token令牌")
//				.modelRef(new ModelRef("string"))
//				.parameterType("header")
//				.required(true).build();
//		pars.add(tokenPar.build());
//		docket.globalOperationParameters(pars);

		//使用认证上下文
		docket.securitySchemes(securitySchemes())
		.securityContexts(securityContexts());
		return builder.build();
	}


	/**
	 * 过滤不显示的接口
	 * @return 返回需要过滤的条件数组
	 */
	private List<Predicate<RequestHandler>> apisFilter() {
		List<Predicate<RequestHandler>> apis = new ArrayList<>();
		String basePackageStr = swaggerInfo.getBasePackage();
		// 1.包过滤
		if (StrUtil.isNotEmpty(basePackageStr)) {
			//支持多个包
			String[] basePackages = basePackageStr.split(";");
			if (null != basePackages && basePackages.length > 0) {
				Predicate<RequestHandler> predicate = input -> {
					// 按basePackage过滤
					Class<?> declaringClass = input.declaringClass();
					String packageName = declaringClass.getPackage().getName();
					return Arrays.asList(basePackages).contains(packageName);
				};
				apis.add(predicate);
			}
		}
		return apis;
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(swaggerInfo.getTitle())
				.description(swaggerInfo.getDescription())
				.version(swaggerInfo.getVersion())
				.licenseUrl(swaggerInfo.getLicenseUrl())
				.contact(new Contact(swaggerInfo.getContactName()
						,swaggerInfo.getContactUrl()
						,swaggerInfo.getContactEmail()))
				.build();
	}

	private List<ApiKey> securitySchemes() {
		return Lists.newArrayList(
				new ApiKey("Authorization", "Authorization", "header"));
	}
	private List<SecurityContext> securityContexts() {
		return Lists.newArrayList(
				SecurityContext.builder()
						.securityReferences(defaultAuth())
						//正则式过滤,此处是所有非login开头的接口都需要认证
						.forPaths(PathSelectors.regex("^(?!login).*$"))
						.build()
		);
	}
	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "认证权限");
		return Lists.newArrayList(
				new SecurityReference("Authorization", new AuthorizationScope[]{authorizationScope}));
	}
}
