package com.uplus.eureka.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.lang.Arrays;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
//import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

//Swagger-UI 확인
//http://localhost:8080/eureka/swagger-ui/index.html

//@OpenAPIDefinition(
//	    info = @Info(
//	        title = "Eureka SpringTest API 명세서",
//	        description = "<h3>SpringTest API Reference for Developers</h3>Swagger를 이용한 SpringTest API<br><img src=\"/assets/img/ssafy_logo.png\" width=\"150\">",
//	        version = "v1",
//	        contact = @Contact(
//	            name = "kdgfox",
//	            email = "kdgfox@gmail.com",
//	            url = "http://eureka.uplus.com"
//	        )
//	    )
//	)
@SecuritySchemes  ({
    @SecurityScheme(
        name = "AccessToken",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
    ),
    @SecurityScheme(
        name = "RefreshToken",
        type = SecuritySchemeType.APIKEY, // HTTP가 아닌 API Key 방식 사용
        in = SecuritySchemeIn.HEADER,
        paramName = "Refresh-Token"
    )
})
@Configuration
public class SwaggerConfiguration {
	private Logger logger = LoggerFactory.getLogger(getClass());
	public SwaggerConfiguration() {
		logger.debug("SwaggerConfiguration.................");
	}
	@Bean
	public OpenAPI openEurekaAPI() {
		logger.debug("openEurekaAPI.............");
		Info info = new Info().title("ureka SpringTest API 명세서").description(
				"<h3>SpringTest API Reference for Developers</h3>Swagger를 이용한 SpringTest API<br><img src=\"/eureka/img/eureka_logo.png\" width=\"50\">")
				.version("v1")
				.contact(new io.swagger.v3.oas.models.info.Contact().name("kdgfox")
				.email("kdgfox@gmail.com").url("http://eureka.uplus.com"));

		return new OpenAPI().components(new Components()).info(info);
	}
	

    
	@Bean
	public GroupedOpenApi memberApi() {
		return GroupedOpenApi.builder().group("eureka-member").pathsToMatch("/member/**").build();
	}

	@Bean
	public GroupedOpenApi bookApi() {
		return GroupedOpenApi.builder().group("eureka-book").pathsToMatch("/book/**").build();
	}
	@Bean
	public GroupedOpenApi testApi() {
		return GroupedOpenApi.builder().group("eureka-test").pathsToMatch("/test/**").build();
	}
	 
}