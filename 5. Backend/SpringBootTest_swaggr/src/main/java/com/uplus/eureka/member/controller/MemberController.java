package com.uplus.eureka.member.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uplus.eureka.EurekaException;
import com.uplus.eureka.member.model.dto.Member;
import com.uplus.eureka.member.model.service.MemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/member")
@Tag(name = "회원  Rest 컨트롤러", description = "회원 에 대한 목록과 상세보기, 등록, 수정, 탈퇴등 전반적인 사용자 정보 관리를 처리하는 클래스")
public class MemberController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private static final String SUCCESS="SUCCESS";
	
	
	private MemberService memberService;
	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	@ExceptionHandler
	public ResponseEntity<String> handler(Exception e){
		logger.error("msg:{}", e.getMessage());
		
		HttpHeaders resheader = new HttpHeaders();
		//에러메세지가 한글인 경우 깨지므로 한글 처리를 위한 응답 헤더 설정
		resheader.add("Content-Type","application/json;charset-UTF-8");
		
		String msg ="처리 중 오류 발생";
		if (e instanceof EurekaException) {
			msg = e.getMessage();
		}		
		return new ResponseEntity<String>(msg, resheader, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Operation(	summary = "회원  상세 정보", 
			description = "아이디에 대한 사용자의 상세 정보를 반환해 줍니다.",
			responses ={
					@ApiResponse(responseCode = "200", description = "책목록 OK!!"),
					@ApiResponse(responseCode = "404", description = "요청 페이지 에러!!"),
					@ApiResponse(responseCode = "500", description = "서버에러!!")
			}
		  )
	@GetMapping("/{id}")
	public ResponseEntity<Member> search(@PathVariable("id") String id){
		logger.debug("search - id:{}",id);
		Member member = memberService.search(id);
		return new ResponseEntity<Member>(member, HttpStatus.OK);
	}
	
	@Operation(	summary = "회원 정보 등록", 
			description = "회원 정",
			responses ={
					@ApiResponse(responseCode = "200", description = "책목록 OK!!"),
					@ApiResponse(responseCode = "404", description = "요청 페이지 에러!!"),
					@ApiResponse(responseCode = "500", description = "서버에러!!")
			}
		  )
	@PostMapping
	public ResponseEntity<String>  regist(@RequestBody Member member){
		logger.debug("regist - member:{}",member);
		memberService.regist(member);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@Operation(	summary = "책목록", 
			description = "책에 대한 <big>전체 목록</big>을 반환해 줍니다.",
			responses ={
					@ApiResponse(responseCode = "200", description = "책목록 OK!!"),
					@ApiResponse(responseCode = "404", description = "요청 페이지 에러!!"),
					@ApiResponse(responseCode = "500", description = "서버에러!!")
			}
		  )
	@PutMapping
	public ResponseEntity<String> update(@RequestBody Member member){
		logger.debug("update - member:{}",member);
		memberService.update(member);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@Operation(	summary = "책목록", 
			description = "책에 대한 <big>전체 목록</big>을 반환해 줍니다.",
			responses ={
					@ApiResponse(responseCode = "200", description = "책목록 OK!!"),
					@ApiResponse(responseCode = "404", description = "요청 페이지 에러!!"),
					@ApiResponse(responseCode = "500", description = "서버에러!!")
			}
		  )
	@DeleteMapping("/{id}")
	public ResponseEntity<String> remove(@PathVariable("id") String id){
		logger.debug("update - id:{}",id);
		memberService.remove(id);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
}






