package com.uplus.eureka.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.uplus.eureka.UnAuthorizedException;
import com.uplus.eureka.util.JWTUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JWTInterceptor implements HandlerInterceptor {

	private final String HEADER_AUTH = "Authorization";
	private final String Refresh_AUTH = "Refresh-Token";

	private JWTUtil jwtUtil;

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
		String refreshToken = request.getHeader(Refresh_AUTH); // Refresh Token도 가져오기
		String url = request.getServletPath();
		String method = request.getMethod();
		log.debug("JWTInterceptor");
		log.debug("request url:{} method:{}", url, method);
		log.debug("token:{}", token);
		log.debug("accessToken:{}", accessToken);
		log.debug("Refresh_AUTH:{}", refreshToken);
//		if (url.equals("/auth")) {
//			return true;
//		}
		if (url.equals("/member") && method.equalsIgnoreCase("POST")) {
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

		if (refreshToken != null) {
			log.info("Refresh Token 사용 가능, Access Token 갱신 필요");
//			String newAccessToken= jwtUtil.generateAccessTokenFromRefreshToken(refreshToken);
//			log.info("재 발행한 access Token:{}",newAccessToken);
//			response.addHeader(HEADER_AUTH, newAccessToken);
			return true;
		}
		log.info("Access Token과 Refresh Token 모두 사용 불가능");
		throw new UnAuthorizedException();
	}
}
