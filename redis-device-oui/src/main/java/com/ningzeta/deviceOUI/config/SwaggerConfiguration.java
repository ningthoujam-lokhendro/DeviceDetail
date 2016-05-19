package com.ningzeta.deviceOUI.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Documentation for the ReST webservice.
 *
 * @author Ningthoujam Lokhendro
 * @since 5/18/2016
 */

@Configuration
@EnableSwagger2
@PropertySource("classpath:application.properties")
public class SwaggerConfiguration {

	@Autowired
	private Environment environment;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ningzeta.deviceOUI.controller"))
				.paths(PathSelectors.regex("/api/.*"))
				.build()
				.pathMapping("/")
				.apiInfo(apiInfo())
				;
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title(environment.getProperty("app.title"))
				.description(environment.getProperty("app.description"))
				.contact(environment.getProperty("app.contact"))
				.version(environment.getProperty("app.version"))
				.license(environment.getProperty("app.copyright"))
				.build();
	}

	@Bean
	UiConfiguration uiConfig() {
		return new UiConfiguration("");
	}

}
