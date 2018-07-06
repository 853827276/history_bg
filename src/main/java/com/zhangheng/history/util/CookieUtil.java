package com.zhangheng.history.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.Assert;


public class CookieUtil {

	/**
	 * 设置cookie值，10分钟过期
	 * @param response
	 * @param key
	 * @param value
	 */
	public static void setValue(HttpServletResponse response, String key, String value) {
		setValue(response, key, value, 60 * 10);
	}
	
	/**
	 * 设置cookie值，如果已经存在对应cookie的话则对现有cookie进行修改
	 * @param request
	 * @param response
	 * @param key
	 * @param value
	 * @param maxage 如果为null则不影响已存在cookie的生命周期
	 */
	public static void setValue(HttpServletRequest request, HttpServletResponse response, String key, String value, Integer maxage) {
		Assert.notNull(key);
		
		if (StringUtil.isNotEmpty(value)) {
			try {
				value = URLEncoder.encode(value, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		Cookie c = new Cookie(key, value);
		if(c == null) {
			c = new Cookie(key, value);
		} else {
			c.setValue(value);
		}
		c.setPath("/");
		if(maxage != null) {
			c.setMaxAge(maxage);
		}
	    response.addCookie(c);
	}
	
	public static void setValue(HttpServletResponse response, String key, String value, Integer maxage) {
		Assert.notNull(key);
		
		Cookie c = new Cookie(key, value);
		c.setPath("/");
		if(maxage != null) {
			c.setMaxAge(maxage);
		}
	    response.addCookie(c);
	}
	
	public static Cookie getCookie(HttpServletRequest request, String key) {
		Assert.notNull(key);
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (key.equals(cookie.getName())) {
					return cookie;
				}
			}
		}
		return null;
	}
	
	public static String getValue(HttpServletRequest request, String key) {
		Assert.notNull(key);
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (key.equals(cookie.getName())) {
					if (StringUtil.isNotEmpty(cookie.getValue())) {
						try {
							return URLDecoder.decode(cookie.getValue(), "utf-8");
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						return cookie.getValue();
					}
		
				}
			}
		}
		return null;
	}
	
	public static void clearValue(HttpServletResponse response, String key) {
		setValue(response, key, null, 0);
	}
}
