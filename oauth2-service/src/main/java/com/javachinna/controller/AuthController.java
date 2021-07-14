package com.javachinna.controller;

import javax.validation.Valid;

import com.javachinna.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.javachinna.dto.ApiResponse;
import com.javachinna.dto.JwtAuthenticationResponse;
import com.javachinna.dto.LocalUser;
import com.javachinna.dto.LoginRequest;
import com.javachinna.dto.SignUpRequest;
import com.javachinna.exception.UserAlreadyExistAuthenticationException;
import com.javachinna.security.jwt.TokenProvider;
import com.javachinna.service.UserService;
import com.javachinna.util.GeneralUtils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	TokenProvider tokenProvider;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = tokenProvider.createToken(authentication);
		LocalUser localUser = (LocalUser) authentication.getPrincipal();
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, GeneralUtils.buildUserInfo(localUser)));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		try {
			User user=userService.registerNewUser(signUpRequest);
			String token=userService.generateVerificationToken(user);
			ResponseEntity<String> responseEntity =
					new RestTemplate().getForEntity("http://localhost:8081/send/login/"+user.getEmail()+"/"+token	,String.class);

		} catch (UserAlreadyExistAuthenticationException e) {
			log.error("Exception Ocurred", e);
			return new ResponseEntity<>(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok().body(new ApiResponse(true, "User registered successfully"));
	}

	@PostMapping("/signup/moderator")
	public ResponseEntity<?> registerModerator(@Valid @RequestBody SignUpRequest signUpRequest) {
		try {
			User user=userService.registerNewModerator(signUpRequest);
			String token=userService.generateVerificationToken(user);
			ResponseEntity<String> responseEntity =
					new RestTemplate().getForEntity("http://localhost:8081/send/login/"+user.getEmail()+"/"+token,String.class);
		} catch (UserAlreadyExistAuthenticationException e) {
			log.error("Exception Ocurred", e);
			return new ResponseEntity<>(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok().body(new ApiResponse(true, "User registered successfully"));
	}

	@GetMapping("accountVerification/{token}")
	public ResponseEntity<String> veryfiAccount(@PathVariable String token){
		userService.verifyAccount(token);
		return new ResponseEntity<>("Account active successfully",HttpStatus.OK);
	}

	@GetMapping("/check/token/{token}")
	public ResponseEntity<Boolean> checkToken(@PathVariable("token")String token){
		return ResponseEntity.ok().body(tokenProvider.validateToken(token));
	}


}