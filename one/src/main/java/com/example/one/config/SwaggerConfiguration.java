package com.example.one.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebMvc
@EnableSwagger2
public class SwaggerConfiguration extends WebMvcConfigurerAdapter {
	
//	private String tokenHeader ="Authorization";
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

	@Bean
    public Docket api() {
		//header accessToken 验证 配置
	    ParameterBuilder tokenPar = new ParameterBuilder();
//    	List<Parameter> pars = new ArrayList<Parameter>();
//    	tokenPar.name(CommonConst.ACCESS_TOCKEN).description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
//    	pars.add(tokenPar.build());
    	
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
//                .apis(RequestHandlerSelectors.any())
//                .apis(RequestHandlerSelectors.basePackage("com.test.system.controller"))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)) //只扫描有API 注解的
                .paths(PathSelectors.any())
                .build();
//                .globalOperationParameters(pars);
    }

}

