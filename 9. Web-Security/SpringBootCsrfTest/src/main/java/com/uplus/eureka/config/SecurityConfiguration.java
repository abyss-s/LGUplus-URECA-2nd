package com.uplus.eureka.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

//http://localhost:9000/eureka/index.jsp
@Component
public class SecurityConfiguration implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
//      2. CSRF 방어하기 첫(Get 방식으로)요청시  _csrf 로 입력됐는지 확인하고 없으면  응답 헤더에 CSRF 헤더 붙여주기
        CsrfToken csrfToken = (CsrfToken) req.getAttribute("_csrf");

        //      CSRF-TOKEN이 없는 경우 header에 CSRF-TOKEN 추가   
        if (csrfToken != null) {
            res.setHeader("X-CSRF-TOKEN", csrfToken.getToken()); 
        }
//        
//     1.5 XSS 방어하기  js 주입하지 못하도록 설정
//       res.setHeader("Content-Security-Policy", "default-src 'self'; script-src 'self';");

       return true;

    }
}
