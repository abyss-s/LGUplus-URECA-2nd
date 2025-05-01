package com.uplus.eureka.config;

import java.util.Arrays;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableAspectJAutoProxy
@AllArgsConstructor
@MapperScan(basePackages = { "com.uplus.**.dao" })
public class WebMvcConfiguration implements WebMvcConfigurer {
	private final SecurityConfiguration securityConfiguration;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*")
//		.allowedOrigins("http://localhost:8080", "http://localhost:8081")
		.allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),
				HttpMethod.DELETE.name(), HttpMethod.HEAD.name(), HttpMethod.OPTIONS.name(),
				HttpMethod.PATCH.name())
		.maxAge(1800); // 1800초 동안 preflight 결과를 캐시에 저장
	}

//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/upload/file/**").addResourceLocations("file:///" + uploadFilePath + "/")
//				.setCachePeriod(3600).resourceChain(true).addResolver(new PathResourceResolver());
//	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		log.debug("WebMvcConfiguration .....addViewControllers");
//		registry.addViewController("/").setViewName("index");
		//중간 패스는 무관, 마지막 패스가 form으로 끝나는 경로로 요청이 들어오면 
		//url과 같은 이름의  view로 이동하기 
		registry.addViewController("/*Form");
	}
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(securityConfiguration)
                .addPathPatterns("/**"); // 전체 경로 적용
    }
	
}
