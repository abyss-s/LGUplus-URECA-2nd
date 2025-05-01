package com.uplus.eureka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class SecurityCSRFConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	log.debug("SecurityCSRFConfig................securityFilterChain");
    	  http
          .csrf(csrf -> csrf
                  .requireCsrfProtectionMatcher(request -> !request.getMethod().equals("GET"))
          )
          .authorizeHttpRequests(auth -> auth
             .anyRequest().permitAll()
        	 //.anyRequest().authenticated()
          );
        return http.build();
    }
}
