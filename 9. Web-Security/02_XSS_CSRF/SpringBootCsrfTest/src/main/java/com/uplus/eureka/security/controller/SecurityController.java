package com.uplus.eureka.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SecurityController {
	
	@PostMapping("/csrf")
	public String csrf(@RequestParam("query") String query) {
		log.debug("SecurityController......9000: query={}", query);
		return "CsrfDefenseForm";
	}
}
