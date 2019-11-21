package me.mason.advswagger.demo.config;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import me.mason.advswagger.demo.annotation.ApiVersion;
import me.mason.advswagger.demo.common.ApiVersionConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
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
	 * 版本分组接口
	 * @return
	 */
	@Bean
	public Docket v100Api() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2)
				.groupName(ApiVersionConstant.VERVION_100)
				.apiInfo(apiInfo())
				//是否启用
				.enable(swaggerInfo.getEnable());
		ApiSelectorBuilder builder = docket.select();
		//
		// 指定需要过滤的版本号
		builder = builder.apis(Predicates.and(apisFilter(false,true
				,new String[]{ApiVersionConstant.VERVION_100})));
		//接口路径过滤
		if (StrUtil.isNotEmpty(swaggerInfo.getAntPath())) {
			builder = builder.paths(Predicates.and(pathFilter()));
		}
		return builder.build();
	}

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
		builder = builder.apis(Predicates.and(apisFilter(true,false,null)));
		//接口路径过滤
		if (StrUtil.isNotEmpty(swaggerInfo.getAntPath())) {
			builder = builder.paths(Predicates.and(pathFilter()));
		}
		//安全认证
		docket.securitySchemes(securitySchemes())
		.securityContexts(securityContexts());
		return builder.build();
	}


	/**
	 * 按参数过滤不显示的接口
	 * @param enableClassFilter 是否按类注解(RestController)过滤
	 * @param enableMethodFilter 是否按方法注解(ApiOperation)过滤
	 * @param groupsFilters 按指定组过滤，null或空数组则不过滤
	 * @return 返回需要过滤的条件数组
	 */
	private List<Predicate<RequestHandler>> apisFilter(boolean enableClassFilter,boolean enableMethodFilter, String[]groupsFilters) {
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

		// 2.过滤被RestController注解的类
		if(enableClassFilter){
			Predicate<RequestHandler> predicate = input -> {
				Class<?> declaringClass = input.declaringClass();
				return declaringClass.isAnnotationPresent(RestController.class);
			};
			apis.add(predicate);
		}

		// 3.过滤被ApiOperation注解的方法
		if(enableMethodFilter){
			apis.add(input -> input.isAnnotatedWith(ApiOperation.class));
		}

		// 4.过滤组
		if(groupsFilters !=null && groupsFilters.length >0){
			Predicate<RequestHandler> predicate = input -> {
				ApiVersion apiVersion = input.getHandlerMethod().getMethodAnnotation(ApiVersion.class);
				return apiVersion!=null && ArrayUtil.containsAny(apiVersion.group(),groupsFilters);
			};
			apis.add(predicate);
		}

		return apis;
	}

	/**
	 * 访问路径过滤
	 * @return
	 */
	private List<Predicate<String>> pathFilter(){
		List<Predicate<String>> paths = new ArrayList<>();
		Predicate<String> ant = PathSelectors.ant(swaggerInfo.getAntPath());
		paths.add(ant);

		return paths;
	}


	/**
	 * 文档基本信息
	 * @return
	 */
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


	/**安全认证**/
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
