package com.javachinna.security.oauth2;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javachinna.exception.BadRequestException;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;

import com.javachinna.util.CookieUtils;
import com.nimbusds.oauth2.sdk.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Component
public class HttpCookieOAuth2AuthorizationRequestRepository implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {
	public static final String OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME = "oauth2_auth_request";
	public static final String REDIRECT_URI_PARAM_COOKIE_NAME = "redirect_uri";
	private static final int cookieExpireSeconds = 180;
	private static final Log log= LogFactory.getLog(HttpCookieOAuth2AuthorizationRequestRepository.class);

	@Override
	public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {
		System.out.println("loadAuthorizationRequest--------------------------------------------------------------------------------------------------------");
		OAuth2AuthorizationRequest auth=null;
		try{
			 auth=CookieUtils.getCookie(request, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME)
					.map(cookie -> {
						//case 1
//						OAuth2AuthorizationRequest auth2AuthorizationRequest=
//								CookieUtils.deserialize1(cookie, OAuth2AuthorizationRequest.class);
						log.info("deserialize before");
						//case 2
						OAuth2AuthorizationRequest auth2AuthorizationRequest=
								CookieUtils.deserialize1(cookie,OAuth2AuthorizationRequest.class);
						System.out.println(auth2AuthorizationRequest.getClientId());
						System.out.println(auth2AuthorizationRequest.getRedirectUri());
						System.out.println(auth2AuthorizationRequest.getAuthorizationUri());
						log.info("deserialize after");
						return auth2AuthorizationRequest;
					})
					.orElse(null);
		}catch (OAuth2AuthenticationException e){
			log.info("message"+e);
		}
		log.info("return loadAuthorizationRequest");
		if(auth==null){
			new BadRequestException("Bad request");
		}
		return auth;
	}

	@Override
	public void saveAuthorizationRequest(OAuth2AuthorizationRequest authorizationRequest,
										 HttpServletRequest request, HttpServletResponse response) {
		System.out.println("saveAuthorizationRequest--------------------------------------------------------------------------------------------------------");
		authorizationRequest.getAttributes().forEach((k,v)->{
			log.info("Key : "+k +" value :"+v);
		});


		if (authorizationRequest == null) {
			System.out.println("authorizationRequest=null----------");
			CookieUtils.deleteCookie(request, response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME);
			CookieUtils.deleteCookie(request, response, REDIRECT_URI_PARAM_COOKIE_NAME);
			return;
		}
		try{

			CookieUtils.addCookie(response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME,
					CookieUtils.serialize1(authorizationRequest), cookieExpireSeconds);

			Optional<Cookie> cookie = CookieUtils.getCookie(request, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME);

		}catch (OAuth2AuthenticationException ex){
			log.info("Exception :");
			log.info(ex.getMessage());
		}


		//log.info("Token is : "+cookie.get().getValue());

		String redirectUriAfterLogin = request.getParameter(REDIRECT_URI_PARAM_COOKIE_NAME);

		if (StringUtils.isNotBlank(redirectUriAfterLogin)) {
			System.out.println("redirectUriAfterLogin is(if String Untils.isNotBlank) :"+redirectUriAfterLogin);
			CookieUtils.addCookie(response, REDIRECT_URI_PARAM_COOKIE_NAME, redirectUriAfterLogin, cookieExpireSeconds);
		}
	}

	@Override
	public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request) {
		System.out.println("removeAuthorizationRequest--------------------------------------------------------------------------------------------------------");
		return this.loadAuthorizationRequest(request);
//		return null;
	}

	public void removeAuthorizationRequestCookies(HttpServletRequest request, HttpServletResponse response) {
		CookieUtils.deleteCookie(request, response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME);
		CookieUtils.deleteCookie(request, response, REDIRECT_URI_PARAM_COOKIE_NAME);
	}
}