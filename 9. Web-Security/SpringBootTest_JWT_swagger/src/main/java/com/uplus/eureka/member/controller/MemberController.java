package com.uplus.eureka.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uplus.eureka.EurekaException;
import com.uplus.eureka.member.model.dto.Member;
import com.uplus.eureka.member.model.service.MemberService;
import com.uplus.eureka.util.JWTUtil;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/member")
@Tag(name = "회원  Rest 컨트롤러", description = "회원 에 대한 목록과 상세보기, 등록, 수정, 탈퇴등 전반적인 사용자 정보 관리를 처리하는 클래스")
@AllArgsConstructor
@Slf4j
////////////////TODO 1. 스웨거에서 header에 저장한 token 보내도록 설정 
@SecurityRequirement(name = "Bearer Authentication")
@SecurityRequirement(name = "AccessToken") // AccessToken 필요
@SecurityRequirement(name = "RefreshToken") // RefreshToken 필요
public class MemberController {
	private static final String SUCCESS = "성공";
	private static final String FAIL = "사용 불가 토큰";
	private final String HEADER_AUTH = "Authorization";
	private final String Refresh_AUTH = "Refresh-Token";
	private final MemberService memberService;

	//////////////// TODO 2. 토큰 관리를 위해 JWTUtil 선언하기
	private final JWTUtil jwtUtil;

	@ExceptionHandler
	public ResponseEntity<String> handler(Exception e) {
		log.error("msg:{}", e.getMessage());

		HttpHeaders resheader = new HttpHeaders();
		// 에러메세지가 한글인 경우 깨지므로 한글 처리를 위한 응답 헤더 설정
		resheader.add("Content-Type", "application/json;charset-UTF-8");

		String msg = "처리 중 오류 발생";
		if (e instanceof EurekaException) {
			msg = e.getMessage();
		}
		return new ResponseEntity<String>(msg, resheader, HttpStatus.INTERNAL_SERVER_ERROR);
	}

////////////////TODO 3. 인증 처리를 위한 함수 정의하기
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody Member member) {
		log.debug("login member :{}", member);

		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String id = member.getId();
			Member loginUser = memberService.login(id, member.getPassword());

			if (loginUser != null) {
				String accessToken = jwtUtil.createAccessToken(id);
				String refreshToken = jwtUtil.createRefreshToken(id);
				log.debug("accessToken:{}", accessToken);
				log.debug("refreshToken:{}", refreshToken);

				// refreshToken 확인용으로 DB에 저장하기
				memberService.saveRefreshToken(id, refreshToken);

				// token을 헤더에 추가
				headers.add(HEADER_AUTH, accessToken);
				headers.add(Refresh_AUTH, refreshToken);
				resultMap.put("message", SUCCESS);
			}
		} catch (Exception e) {
			log.debug("로그인 에러 발생:{}", e);
			resultMap.put("message", "인증 처리 중 오류 발생");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, headers, status);
	}

	
////////////////TODO 4. 로그 아웃을 위한 함수 정의하기
	@Operation(summary = "로그아웃", description = "아이디 해당하는 회원을 로그 아웃.")
	@GetMapping("/logout/{userId}")
	public ResponseEntity<?> logout(@PathVariable("userId") String id, @RequestHeader(HEADER_AUTH) String accessToken) {
		Map<String, Object> result = new HashMap<>();
		HttpStatus status = HttpStatus.OK;
		try {
			if (isAuth(id, accessToken)) {
				memberService.deleRefreshToken(id);
				result.put("message", SUCCESS);
			} else {
				log.error("사용 불가 토큰!!!!");
				result.put("message", "사용 불가 토큰");
				status = HttpStatus.UNAUTHORIZED;
			}
		} catch (Exception e) {
			log.error("로그 아웃 실패:{}", e);
			result.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(result, status);
	}

////////////////TODO 8. accessToken이 만료된 경우 다시 발급 받기 위한 함수 정의하기	
	@Operation(summary = "토큰 재 발급", description = "accessToken이 만료된 경우 다시 발급 받기 위한 함수 정의하기")
	@PostMapping("/refresh")
	public ResponseEntity<?> refresh(@RequestHeader(Refresh_AUTH) String refreshToken, @RequestBody Member member) {
		
		log.debug("refresh..............................refreshToken:{}", refreshToken);
		String id = member.getId();
		Map<String, Object> result = new HashMap<>();
		HttpStatus status = HttpStatus.OK;
		HttpHeaders headers = new HttpHeaders();
		try {
			String myRefresh = memberService.getRefreshToken(id);
			log.debug("refresh..............................myRefresh:{}", myRefresh);
			if(myRefresh.equals(refreshToken) && jwtUtil.checkToken(refreshToken)) {
				String accessToken = jwtUtil.createAccessToken(member.getId());
				log.debug("re id:{}  accessToken:{}", member.getId(),  accessToken);
				headers.add(HEADER_AUTH, accessToken);
				result.put("message", SUCCESS);
			}else {
				log.error("유효하지 않은 토큰");
				result.put("message", "유효하지 않은 토큰");
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}catch (Exception e) {
			log.error("refresh 통큰 생성 실패:{}", e);
			result.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(result, headers, status);
	}
	
	public boolean isAuth(String userId, String authorHeader) {
		log.debug("search - Authorization:{}", authorHeader);
		String token = authorHeader.replace("Bearer ", "");
		if (!jwtUtil.checkToken(token))
			return false;
		String tokenId = jwtUtil.getUserId(authorHeader.replace("Bearer ", ""));
		if (userId.equals(tokenId)) {
			return true;
		} else {
			return false;
		}
	}

	@Operation(	summary = "회원  상세 정보", 
			description = "아이디에 대한 사용자의 상세 정보를 반환해 줍니다.",
			responses ={@ApiResponse(responseCode = "200", description = "책목록 OK!!")})
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object> > search(@PathVariable("id") @Parameter(description = "인증할 회원의 아이디.", required = true) String id 
			, @RequestHeader(HEADER_AUTH) String accessToken){
		log.debug("search - id:{}",id);
		HttpStatus status = HttpStatus.OK;
		Map<String, Object> resultMap = new HashMap<>();
		////////////////TODO 5. 토큰으로 체크하고 안전하면 회원정보를 제공하는 코드 작성
		if(isAuth(id, accessToken)) {  
			Member member = memberService.search(id);
			resultMap.put("member", member);
			resultMap.put("message", SUCCESS);
		}else {
			log.error("사용 불가 토큰!!!!");
			resultMap.put("message", "사용 불가 토큰");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@Operation(	summary = "회원 정보 등록", 
			description = "회원 정보를 등록합니다.",
			responses ={@ApiResponse(responseCode = "200", description = "회원 등록 OK!!")})
	@PostMapping
	public ResponseEntity<Map<String, Object>>  regist(@RequestBody Member member){
		log.debug("regist - member:{}",member);
		memberService.regist(member);
		HttpStatus status = HttpStatus.CREATED;
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("message", SUCCESS);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@Operation(summary = "회원 정보 수정", description = "회원 아이디에 대한 상세 정보를 수정합니다.", responses = {
			@ApiResponse(responseCode = "200", description = "회원 정보 수정 OK!!") })
	@PutMapping
	public ResponseEntity<Map<String, Object>> update(@RequestBody Member member,
			@RequestHeader("Authorization") String authorHeader) {
		log.debug("update - member:{}", member);
		log.debug("update - Authorization:{}", authorHeader);
		HttpStatus status = HttpStatus.OK;
		Map<String, Object> resultMap = new HashMap<>();
////////////////TODO 6. 토큰으로 체크하고 안전하면 회원정보를 수정하는 코드 작성
		if (isAuth(member.getId(), authorHeader)) {
			log.info("토큰 ID와 요청 id가 같음 사용 가능한 토큰!!!");
			resultMap.put("message", SUCCESS);
			memberService.update(member);
		} else {
			log.error(FAIL);
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@Operation(summary = "회원 탈퇴", description = "회원 아이디를 탈퇴 처리합니다.", responses = {
			@ApiResponse(responseCode = "200", description = "탈퇴 OK") })
	@DeleteMapping("/{id}")
	public ResponseEntity<String> remove(@PathVariable("id") String id,
			@RequestHeader("Authorization") String authorHeader) {
		log.debug("update - id:{}", id);

		Map<String, Object> resultMap = new HashMap<>();
		String msg = SUCCESS;
		HttpStatus status = HttpStatus.OK;
////////////////TODO 7. 토큰으로 체크하고 안전하면 회원을 탈퇴시키는 코드 작성		
		if (isAuth(id, authorHeader)) {
			log.info("토큰 ID와 요청 id가 같음 사용 가능한 토큰!!!");
			resultMap.put("message", msg);
			memberService.remove(id);
		} else {
			log.error("사용 불가 토큰!!!");
			resultMap.put("message", "사용 불가 토큰");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<String>(SUCCESS, status);
	}
}
