package com.in28minutes.microservices.apigateway;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter {

	private Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, 
			GatewayFilterChain chain) {
	//	HttpHeaders authHeader=exchange.getRequest().getHeaders();
	//	String auth=authHeader.getFirst(HttpHeaders.AUTHORIZATION);
		
//		if(StringUtils.isEmpty(auth)) {
//			logger.error("Invalid auth header");
//			return null;
//		}else if(auth.split("Bearer ").length!=2){
//			logger.error("Invalid auth header");
//			return null;
//		}
//		
//		String token=auth.split("Bearer ")[1];
//		HashMap<String, String> uriVariables = new HashMap<>();
//        uriVariables.put("token",token);
//	    ResponseEntity<Boolean> checkToken=new RestTemplate()
//	                .getForEntity("http://localhost:8080/api/auth/check/token/{token}", Boolean.class,uriVariables);
//	    
//	    if(!checkToken.getBody()) {
//	    	logger.error("Invalid auth header");
//			return null;
//	    }
	    
		logger.info("Path of the request received -> {}",
				exchange.getRequest().getPath());

		return chain.filter(exchange);
	}

}
