package com.javachinna.service;

import java.util.*;

import com.javachinna.exception.BadRequestException;
import com.javachinna.model.VerificationToken;
import com.javachinna.repo.VerificationTokenRepository;
import com.javachinna.security.jwt.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.javachinna.dto.LocalUser;
import com.javachinna.dto.SignUpRequest;
import com.javachinna.dto.SocialProvider;
import com.javachinna.exception.OAuth2AuthenticationProcessingException;
import com.javachinna.exception.UserAlreadyExistAuthenticationException;
import com.javachinna.model.Role;
import com.javachinna.model.User;
import com.javachinna.repo.RoleRepository;
import com.javachinna.repo.UserRepository;
import com.javachinna.security.oauth2.user.OAuth2UserInfo;
import com.javachinna.security.oauth2.user.OAuth2UserInfoFactory;
import com.javachinna.util.GeneralUtils;

/**
 * @author Chinna
 * @since 26/3/18
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private VerificationTokenRepository verificationTokenRepository;

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	@Transactional(value = "transactionManager")
	public User registerNewUser(final SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException {

		if (signUpRequest.getUserID() != null && userRepository.existsById(signUpRequest.getUserID())) {
			throw new UserAlreadyExistAuthenticationException("User with User id " + signUpRequest.getUserID() + " already exist");
		} else if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new UserAlreadyExistAuthenticationException("User with email id " + signUpRequest.getEmail() + " already exist");
		}
		User user = buildUser(signUpRequest,Role.ROLE_USER);
		Date now = Calendar.getInstance().getTime();

		user.setCreatedDate(now);
		user.setModifiedDate(now);
		user = userRepository.save(user);
		userRepository.flush();
		return user;
	}

	@Override
	@Transactional(value = "transactionManager")
	public User registerNewModerator(final SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException {

		if (signUpRequest.getUserID() != null && userRepository.existsById(signUpRequest.getUserID())) {
			throw new UserAlreadyExistAuthenticationException("User with User id " + signUpRequest.getUserID() + " already exist");
		} else if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new UserAlreadyExistAuthenticationException("User with email id " + signUpRequest.getEmail() + " already exist");
		}
		User user = buildUser(signUpRequest,Role.ROLE_MODERATOR);
		Date now = Calendar.getInstance().getTime();
		user.setCreatedDate(now);
		user.setModifiedDate(now);
		user = userRepository.save(user);
		userRepository.flush();
		return user;
	}

	private User buildUser(final SignUpRequest formDTO,String role) {
		User user = new User();
		user.setDisplayName(formDTO.getDisplayName());
		user.setEmail(formDTO.getEmail());
		user.setPassword(passwordEncoder.encode(formDTO.getPassword()));
		final HashSet<Role> roles = new HashSet<Role>();
	//	roles.add(roleRepository.findByName(Role.ROLE_USER));
		roles.add(roleRepository.findByName(role));
		user.setRoles(roles);
		user.setProvider(SocialProvider.LOCAL.toString());
		user.setEnabled(false);
		user.setProviderUserId(formDTO.getProviderUserId());
		return user;
	}

	public String generateVerificationToken(User user) {
		String token= UUID.randomUUID().toString();
		VerificationToken verificationToken=new VerificationToken();
		verificationToken.setToken(token);
		verificationToken.setUser(user);
		verificationTokenRepository.save(verificationToken);
		return token;
	}

	@Override
	public User findUserByEmail(final String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	@Transactional
	public LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes,
											 OidcIdToken idToken, OidcUserInfo userInfo) {

		OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId, attributes);

		logger.info(oAuth2UserInfo.getEmail());
		logger.info(oAuth2UserInfo.getName());
		logger.info(oAuth2UserInfo.getId());
		logger.info(oAuth2UserInfo.getImageUrl());
		OAuth2AuthenticationToken token;


		if (StringUtils.isEmpty(oAuth2UserInfo.getName())) {
			throw new OAuth2AuthenticationProcessingException("Name not found from OAuth2 provider");
		} else if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
			throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
		}

		SignUpRequest userDetails = toUserRegistrationObject(registrationId, oAuth2UserInfo);
		User user = findUserByEmail(oAuth2UserInfo.getEmail());
		if (user != null) {
			if (!user.getProvider().equals(registrationId) && !user.getProvider().equals(SocialProvider.LOCAL.getProviderType())) {
				throw new OAuth2AuthenticationProcessingException(
						"Looks like you're signed up with " + user.getProvider() + " account. Please use your " + user.getProvider() + " account to login.");
			}
			user = updateExistingUser(user, oAuth2UserInfo);
		} else {
			user = registerNewUser(userDetails);
		}

		return LocalUser.create(user, attributes, idToken, userInfo);
	}

	private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
		existingUser.setDisplayName(oAuth2UserInfo.getName());
		return userRepository.save(existingUser);
	}

	private SignUpRequest toUserRegistrationObject(String registrationId, OAuth2UserInfo oAuth2UserInfo) {
		return SignUpRequest.getBuilder().addProviderUserID(oAuth2UserInfo.getId()).addDisplayName(oAuth2UserInfo.getName()).addEmail(oAuth2UserInfo.getEmail())
				.addSocialProvider(GeneralUtils.toSocialProvider(registrationId)).addPassword("changeit").build();
	}

	@Override
	public Optional<User> findUserById(Long id) {
		return userRepository.findById(id);
	}

	/*verifyAccount*/
	@Override
	public void verifyAccount(String token) {
		Optional<VerificationToken> verificationToken =
				verificationTokenRepository.findByToken(token);
		verificationToken.orElseThrow(()->new BadRequestException("Invalid token"));
		fechUserAndEnable(verificationToken.get());
	}

	@Transactional
	private void fechUserAndEnable(VerificationToken verificationToken) {
		String username = verificationToken.getUser().getDisplayName();
		User user=userRepository.findByDisplayName(username).orElseThrow(
				()->new BadRequestException("User not find with name -"+username));
		user.setEnabled(true);
		userRepository.save(user);
	}
	/*verifyAccount*/


}
