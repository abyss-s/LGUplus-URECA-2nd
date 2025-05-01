package com.uplus.eureka.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.uplus.eureka.interceptor.JWTInterceptor;

//import lombok.extern.slf4j.Slf4j;

//@Slf4j
@Configuration
@EnableAspectJAutoProxy
@MapperScan(basePackages = { "com.uplus.**.dao" })
public class WebMvcConfiguration implements WebMvcConfigurer {
	private JWTInterceptor jwtInterceptor;

	public WebMvcConfiguration(JWTInterceptor jwtInterceptor) {
		super();
		this.jwtInterceptor = jwtInterceptor;
	}
	/*
	 * CORS 
	 * 브라우저에서 보낸 pre-flight에 허용할 정보들을 설정한다. 
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")        	//모든 요청에 대해
//		.allowedOrigins("*")				//허용해줄 Origin 설정 
		.allowedOrigins("http://localhost:3000", "http://localhost:3001")
//		허용해줄 method 설정   pre-flight에서 options로 요청하지만 head로 오는 경우도 있어서 모두 허용한다. 
		.allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),
				HttpMethod.DELETE.name(), HttpMethod.HEAD.name(), HttpMethod.OPTIONS.name(),
				HttpMethod.PATCH.name())
//		CORS에 의해 Authorization, Refresh-Token 헤더에 접근 불가하므로 아래 헤더에
		.allowedHeaders("Authorization", "Refresh-Token", "Content-Type")
//		 응답 헤더 브라우저에서 읽기 허용   ==> 허용하지 안하으면 프론트에서 header를 읽으면 null이된다. 
		.exposedHeaders("Authorization", "Refresh-Token")  
        .allowCredentials(true)  	// 인증 정보 포함 (쿠키 전송 허용)
		.maxAge(1800); 				// 30분(1800초) 동안 preflight 결과를 캐시에 저장 => 보안 고려
									// 1시간(3600초) 일반적으로 설정 시간
	}
	
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor)
//				 Interceptor 적용 대상을 위한 Path 설정 
		         .addPathPatterns("/**")             
//		         Interceptor에 제외될 path를 설정 (List를 통해서 경로 설정 가능)
	             .excludePathPatterns(	"/js/**", 
	            		 				"/css/**", 
	            		 				"/img/**",
	            		 				"/error",
	            		 				"/book/**",
	            		 				"/favicon.ico",
	            		 				"/swagger-ui/**"
	            		 				,"/v3/api-docs/**"
	            		 				,"/member/login"
	            		 
	            		 				);    
	 }
	
	/**
	 * resource 요청이 들어왔을때 spring container를 거치지 않고 바로 해당 경로에서 서비스 되도록 설정 
	 */
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/assets/img/");
		registry.addResourceHandler("/*.html**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/swagger-ui.html**").addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        
    }
	
}
