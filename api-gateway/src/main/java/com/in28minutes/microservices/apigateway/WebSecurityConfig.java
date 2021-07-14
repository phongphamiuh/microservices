//package com.in28minutes.microservices.apigateway;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.cors().and()
//			.csrf().disable()
//			.authorizeRequests()
//			.antMatchers(HttpMethod.GET,new String[] {"/api/category/alls"})
//			.permitAll();
//	}
//	
//
//}
