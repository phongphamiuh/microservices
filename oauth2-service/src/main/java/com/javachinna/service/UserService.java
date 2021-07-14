package com.javachinna.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;

import com.javachinna.dto.LocalUser;
import com.javachinna.dto.SignUpRequest;
import com.javachinna.exception.UserAlreadyExistAuthenticationException;
import com.javachinna.model.User;

/**
 * @author Chinna
 * @since 26/3/18
 */
public interface UserService {

	User registerNewUser(SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException;

	User registerNewModerator(SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException;

	String generateVerificationToken(User user);

	User findUserByEmail(String email);

	Optional<User> findUserById(Long id);

	LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes,
									  OidcIdToken idToken, OidcUserInfo userInfo );

	void verifyAccount(String token);



}
