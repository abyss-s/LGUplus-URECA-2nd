package com.uplus.eureka.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.uplus.eureka.UnAuthorizedException;
import com.uplus.eureka.util.JWTUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JWTInterceptor implements HandlerInterceptor {

	private final String HEADER_AUTH = "Authorization";
	private final String Refresh_AUTH = "Refresh-Token";
	private JWTUtil jwtUtil;
	///////////////////TODO 1. refresh-token을 위한 상수 선언하기 

	public JWTInterceptor(JWTUtil jwtUtil) {
		super();
		this.jwtUtil = jwtUtil;
	}

	// Refresh-Token
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			return true;
		}
		String accessToken = request.getHeader(HEADER_AUTH);
		String token = request.getHeader("token");
		String url = request.getServletPath();
		String method = request.getMethod();
		
		///////////////////TODO 3. 쿠키에서  refreshToken 토큰 추출하기 
		log.debug("JWTInterceptor 진입");
		log.debug("request url:{} method:{}", url, method);
		log.debug("accessToken:{}", accessToken);
//		log.debug("refreshToken (from cookie):{}", refreshToken);
		
		if (url.equals("/member/login") && method.equalsIgnoreCase("POST")) {
			return true;
		}
		
		if (accessToken != null) {
			accessToken = accessToken.replace("Bearer ", "");
			log.debug("HEADER_AUTH:{}", accessToken);
			if (jwtUtil.checkToken(accessToken)) {
				log.info("Access Token 사용 가능 : {}", accessToken);
				return true;
			}
		}

		log.info("Access Token 사용 불가능, Refresh Token 검사 시작");

//		if (refreshToken != null) {
//			log.info("Refresh Token 사용 가능, Access Token 갱신 필요");
//			return true;
//		}
		log.info("Access Token과 Refresh Token 모두 사용 불가능");
		throw new UnAuthorizedException();
	}

	///////////////////TODO 2. 쿠키에서 특정 이름의 토큰을 추출하는 함수를 작성하기 

}
