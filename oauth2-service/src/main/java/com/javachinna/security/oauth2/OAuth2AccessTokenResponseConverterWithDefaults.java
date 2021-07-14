package com.javachinna.security.oauth2;

import java.text.ParseException;
import java.util.*;

import io.jsonwebtoken.JwtParser;
import org.apache.commons.codec.binary.Base64;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.javachinna.security.jwt.TokenAuthenticationFilter;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.proc.JWTProcessor;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * @author Joe Grandja
 */
public class OAuth2AccessTokenResponseConverterWithDefaults implements Converter<Map<String, String>, OAuth2AccessTokenResponse> {
	private static final Logger log=LoggerFactory.getLogger(OAuth2AccessTokenResponseConverterWithDefaults.class);
	private static final Set<String> TOKEN_RESPONSE_PARAMETER_NAMES = Stream
				.of(OAuth2ParameterNames.ACCESS_TOKEN,
						OAuth2ParameterNames.TOKEN_TYPE,
						OAuth2ParameterNames.EXPIRES_IN,
						OAuth2ParameterNames.REFRESH_TOKEN,
						OAuth2ParameterNames.SCOPE)
					.collect(Collectors.toSet());

	private OAuth2AccessToken.TokenType defaultAccessTokenType = OAuth2AccessToken.TokenType.BEARER;
	private static final Logger logger = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

	@Override
	public OAuth2AccessTokenResponse convert(Map<String, String> tokenResponseParameters) {

		System.out.println("convert--------------------------------------------------------------------------------------------------");
		String accessToken = tokenResponseParameters.get(OAuth2ParameterNames.ACCESS_TOKEN);
		//String idToken=tokenResponseParameters.get("id_token");
		//logger.info("id_token is :"+idToken);
		logger.info("Convert accessToken :"+accessToken);
		OAuth2AccessToken.TokenType accessTokenType = this.defaultAccessTokenType;
		logger.info("Convert accessTokenType :"+accessToken);

		if (OAuth2AccessToken.TokenType.BEARER.getValue()
				.equalsIgnoreCase(tokenResponseParameters.get(OAuth2ParameterNames.TOKEN_TYPE))) {
			accessTokenType = OAuth2AccessToken.TokenType.BEARER;
			logger.info("Convert accessTokenType in if statement:"+accessToken);
		}
		// EXPIRES_IN
		long expiresIn = 0;
		if (tokenResponseParameters.containsKey(OAuth2ParameterNames.EXPIRES_IN)) {
			try {
				expiresIn = Long.valueOf(tokenResponseParameters.get(OAuth2ParameterNames.EXPIRES_IN));
			} catch (NumberFormatException ex) {
			}
		}

		Set<String> scopes = Collections.emptySet();
		if (tokenResponseParameters.containsKey(OAuth2ParameterNames.SCOPE)) {

			String scope = tokenResponseParameters.get(OAuth2ParameterNames.SCOPE);
			logger.info("Convert Scope:"+scope);

			scopes = Arrays.stream(StringUtils.delimitedListToStringArray(scope, " ")).collect(Collectors.toSet());
			logger.info("Convert scopes:"+scope);
		}


		TOKEN_RESPONSE_PARAMETER_NAMES.stream().forEach(k->{
			log.info("Key is :"+k);
		});
		// additionalParameters
		Map<String, Object> additionalParameters = new LinkedHashMap<>();
		tokenResponseParameters.entrySet().stream().forEach(e->{
			logger.info("Key entrySet:"+e.getKey()+" value :"+e.getValue());
		});
		String refreshToken = tokenResponseParameters.get(OAuth2ParameterNames.REFRESH_TOKEN);

		for (Map.Entry<String, String> e : tokenResponseParameters.entrySet()) {
			if (!TOKEN_RESPONSE_PARAMETER_NAMES.contains(e.getKey())) {
				String token = e.getValue();

				try {
					JWT jwt=JWTParser.parse(token);

				} catch (ParseException parseException) {
					parseException.printStackTrace();
				}

				logger.info("Key :" + e.getKey() + " value :" + e.getValue());
				additionalParameters.put(e.getKey(), e.getValue());
			}
		}
		OAuth2AccessTokenResponse oAuth2AccessTokenResponse=OAuth2AccessTokenResponse
				.withToken(accessToken)
				.tokenType(accessTokenType)
				.expiresIn(expiresIn)
				.scopes(scopes)
				.refreshToken(refreshToken)
			//	.additionalParameters(additionalParameters)
				.build();
		//System.out.println("oAuth2AccessTokenResponse getAccessToken"+oAuth2AccessTokenResponse.getAccessToken().toString());
	//	System.out.println("oAuth2AccessTokenResponse getRefreshToken"+oAuth2AccessTokenResponse.getRefreshToken().toString());
		return OAuth2AccessTokenResponse
				.withToken(accessToken)
				.tokenType(accessTokenType)
				.expiresIn(expiresIn)
				.scopes(scopes)
				.additionalParameters(additionalParameters)
				.build();
	}

	public final void setDefaultAccessTokenType(OAuth2AccessToken.TokenType defaultAccessTokenType) {
		Assert.notNull(defaultAccessTokenType, "defaultAccessTokenType cannot be null");
		this.defaultAccessTokenType = defaultAccessTokenType;
	}

}