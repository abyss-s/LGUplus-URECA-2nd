package com.uplus.eureka.member.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uplus.eureka.member.model.dto.Member;
import com.uplus.eureka.member.model.service.MemberService;

import jakarta.servlet.http.HttpSession;

//TODO 1. RestController로 등록하기
//TODO 2. RequestMapping을 이용하여 요청명 등록하기
//TODO 3. CrossOrigin을 이용하여 CORS 승인하기
public class MemberController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	//TODO 4. 회원 정보를 처리하기 위해 MemberService를 선언한다. 
	private MemberService memberService;
	
	//TODO 5. MemberService를 전달 받는 생성자를 선언한다. 
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	//TODO 7. 회원 정보를 조회하여 응답하는 함수 작성
	
	//TODO 8. 회원 정보를 등록하는 함수 작성
	
	//TODO 10. 회원 정보를 수정하는 함수 작성
	
	//TODO 12. 회원 정보를 삭제하는 함수 작성
	
	
	

}






