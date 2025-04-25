package com.uplus.eureka.util;

import java.net.http.HttpHeaders;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.uplus.eureka.UnAuthorizedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JWTUtil {
	////TODO 1. application.properties에 설정한 salt 정보 가져오기 
	@Value("${jwt.salt}")
	private String SALT;

	
	////TODO 2. application.properties에 설정한 access-token의 expiretime 정보 가져오기 
	@Value("${jwt.access-token.expiretime}")
	private long accessTokenExpireTime;

	////TODO 3. application.properties에 설정한 refresh-token의 expiretime 정보 가져오기 
	@Value("${jwt.refresh-token.expiretime}")
	private long refreshTokenExpireTime;

	
	
	////TODO 4. Token 발급하는 함수 작성하 
	//		JWT 구성 : Header + Payload(Claim) + Signature
	private String generateToken(Map<String, Object> claims, String subject, long expireTime) {

		//Header 설정.
		Map<String, String> headers = new HashMap<>();
		headers.put("typ", "JWT");
		log.debug("generateToken- expireTime:{}", expireTime);
		return Jwts.builder().header().add(headers).and().claims(claims).subject(subject)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + expireTime))
				.signWith(getSigningKey()).compact();
	}


	////TODO 5. Signature 설정에 들어갈 key 생성. 
	private SecretKey getSigningKey() {
		byte[] keyBytes = SALT.getBytes(StandardCharsets.UTF_8);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	////TODO 6. id 정보를 이용한 AccessToken을 생성하는 함수 작성하기  
	public String createAccessToken(String userId) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("userId", userId);
		claims.put("tokenType", "ACCESS");
		return generateToken(claims, "access-token", accessTokenExpireTime);
	}

	////TODO 7. id 정보를 이용한 RefreshToken을 생성하는 함수 작성하기 
	public String createRefreshToken(String userId) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("userId", userId);
		claims.put("tokenType", "REFRESH");
		return generateToken(claims, "refresh-token", refreshTokenExpireTime);
	}

	
	////TODO 8. 전달 받은 토큰이 제대로 생성된 것인지 확인 하고 문제가 있다면 UnauthorizedException 발생시키는  함수 작성하기
	public boolean checkToken(String token) {
		try {
//			Json Web Signature? 서버에서 인증을 근거로 인증 정보를 서버의 private key 서명 한것을 토큰화 한것
//			setSigningKey : JWS 서명 검증을 위한  secret key 세팅
//			parseClaimsJws : 파싱하여 원본 jws 만들기
			Jws<Claims> claims = Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token);
//			Claims 는 Map 구현체 형태
			log.debug("claims: {}", claims);

			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
	}

	////TODO 9. Token에서 id를 추출하는 함수 작성하기  
	public String getUserId(String authorization ) {
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(authorization);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new UnAuthorizedException();
		}
		Map<String, Object> value = claims.getPayload();
		log.info("value : {}", value);
		return (String) value.get("userId");
	}
	
	////TODO 10. Access Token 만료시 Refresh Token을 이용해 새로운 Access Token 발급하는 메서드 작성하기 
    public String generateAccessTokenFromRefreshToken(String refreshToken) {
        if (!checkToken(refreshToken)) {
            throw new UnAuthorizedException(); // Refresh Token이 유효하지 않으면 예외 발생
        }
        String userId = getUserId(refreshToken);
        return createAccessToken(userId); // 새로운 Access Token 생성
    }
}
