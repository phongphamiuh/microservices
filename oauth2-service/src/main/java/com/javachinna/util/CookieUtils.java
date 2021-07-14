package com.javachinna.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


import java.util.Base64;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.SerializationUtils;

public class CookieUtils {
	private static final Logger log= LoggerFactory.getLogger(CookieUtils.class);

	public static Optional<Cookie> getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();

		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					return Optional.of(cookie);
				}
			}
		}

		return Optional.empty();
	}

	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
		System.out.println("Add cookie");
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		cookie.setHttpOnly(true);
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					cookie.setValue("");
					cookie.setPath("/");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
	}

	public static String serialize(Object object) {
		return Base64.getUrlEncoder().encodeToString(SerializationUtils.serialize(object));
	}

	public static String serialize1(Object object) {
		log.info("serialize1 error step 1");
		org.apache.commons.codec.binary.Base64 base64=new org.apache.commons.codec.binary.Base64();
		String encodedString = new String(base64.encode(SerializationUtils.serialize(object)));
		log.info("serialize1 error step 2");
		return encodedString;
	}

	public static <T> T deserialize1(Cookie cookie, Class<T> cls) {
		log.info("deserialize1 error step 1");
		org.apache.commons.codec.binary.Base64 base64=new org.apache.commons.codec.binary.Base64();
		log.info("deserialize1 error step 2");
		return cls.cast(SerializationUtils.deserialize(base64.decode(cookie.getValue())));
	}

	public static <T> T deserialize(Cookie cookie, Class<T> cls) {
		return cls.cast(SerializationUtils.deserialize(Base64.getUrlDecoder().decode(cookie.getValue())));
	}
}