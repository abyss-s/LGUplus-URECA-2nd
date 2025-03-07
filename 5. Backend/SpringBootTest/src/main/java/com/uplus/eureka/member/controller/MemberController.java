package com.uplus.eureka.member.controller;

import com.uplus.eureka.book.model.dto.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.uplus.eureka.member.model.dto.Member;
import com.uplus.eureka.member.model.service.MemberService;

import jakarta.servlet.http.HttpSession;

//TODO 1. RestController로 등록하기
@RestController
//TODO 2. RequestMapping을 이용하여 요청명 등록하기

@RequestMapping("/member")
//TODO 3. CrossOrigin을 이용하여 CORS 승인하기

@CrossOrigin(origins = {"*"})
public class MemberController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	//TODO 4. 회원 정보를 처리하기 위해 MemberService를 선언한다. 
	private MemberService memberService;
	private static final String SUCCESS = "SUCCESS";

	//TODO 5. MemberService를 전달 받는 생성자를 선언한다. 
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	//TODO 7. 회원 정보를 조회하여 응답하는 함수 작성
	@GetMapping("/{id}")
	public ResponseEntity<Member> search(@PathVariable("id") String id) {
		//logger.debug("regist- book:{}", member);
		Member member = memberService.search(id);
		return new ResponseEntity<Member>(member, HttpStatus.OK);
	}
	
	//TODO 8. 회원 정보를 등록하는 함수 작성
	@PostMapping
	public ResponseEntity<String> regist(@RequestBody Member member) {
		logger.debug("member: {}", member);
		memberService.regist(member);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
	}
	
	//TODO 10. 회원 정보를 수정하는 함수 작성
	@PutMapping
	public ResponseEntity<String> update(@RequestBody Member member) {
		logger.debug("member: {}", member);
		memberService.update(member);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	//TODO 12. 회원 정보를 삭제하는 함수 작성
	@DeleteMapping("/{id}")
	public ResponseEntity<Member> remove(@PathVariable("id") String id) {
		logger.debug("remove- book:{}", id);
		Member member = memberService.search(id);
		return new ResponseEntity<Member>(member, HttpStatus.OK);
	}

}






