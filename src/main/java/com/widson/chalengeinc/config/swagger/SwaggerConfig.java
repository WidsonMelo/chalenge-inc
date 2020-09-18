package com.widson.chalengeinc.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig{
	
	  @Bean
	  public Docket api() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .select()
	        .apis(RequestHandlerSelectors.basePackage("com.widson.chalengeinc.controllers"))
	        .paths(PathSelectors.any())
	        .build()
	        .apiInfo(metaData());
	  }

	  private ApiInfo metaData() {
	    return new ApiInfoBuilder()
	        .title("Movie Evaluation Application")
	        .description("\"Chalenge-inc is a company responsible for creating media applications and is studying the development of a new application so that its users can exchange information about the films they have watched and those they still want to watch. This project consumes an Api to describe films.\"")
	        .version("0.1.0")
	        .license("Apache License Version 2.0")
	        .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
	        .build();
	  }
}