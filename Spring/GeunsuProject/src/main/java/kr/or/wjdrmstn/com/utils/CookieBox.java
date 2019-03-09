package kr.or.wjdrmstn.com.utils;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieBox {

	private Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
	
	private static String charType="UTF-8";
	
	/**
	 * 쿠키들을 request 객체에서 가지고와서 cookieMap객체에 주입
	 * @param request
	 */
	public CookieBox(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				//생성된 쿠키명과 쿠키값을 cookieMap에 주입
				cookieMap.put(cookie.getName(), cookie);
			}
		}
	}
	
	/**
	 * cookie 값을 배열로 받아서 cookieMap 객체에 주입
	 * @param cookies
	 */
	public CookieBox(Cookie[] cookies) {
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}			
		}	
	}
	
	/**
	 * cookieMap 객체에서 name에 해당하는 값을 Cookie 값으로 반환
	 * @param name
	 * @return Cookie
	 */
	public Cookie getCookie(String name) {
		return cookieMap.get(name);
	}
	
	/**
	 * 쿠키에 들어있는 값을 URLDecoder.decode() 메소드로 값을 반환
	 * @param name
	 * @return String
	 * @throws IOException
	 */
	public String getValue(String name) throws IOException {
		Cookie cookie = cookieMap.get(name);
		if(cookie == null) {
			return null;
		}
		return URLDecoder.decode(cookie.getValue(), charType);
	}
	
	/**
	 * 등록된 쿠키명이 있는지 없는지 확인
	 * @param name
	 * @return boolean
	 */
	public boolean exists(String name) {
		return cookieMap.containsKey(name);
	}
	
	/**
	 * 쿠키 생성시 기본 설정및 커스텀 설정을 
	 * 통하여 쿠키 생성
	 * 오버로딩을 통한 무한한 확장
	 * @param name
	 * @param value
	 * @return Cookie
	 * @throws IOException
	 */
	public static Cookie createCookie(String name, String value) throws IOException {
		Cookie cookie = new Cookie(name, URLEncoder.encode(value, charType));  
		cookie.setPath("/");
		return cookie;
	}
	public static Cookie createCookie(String name, String value,String path) throws IOException {
		Cookie cookie = new Cookie(name, URLEncoder.encode(value, charType));
		cookie.setPath(path);
		return cookie;
	}
	public static Cookie createCookie(String name, String value,String path, int maxAge) throws IOException {
		Cookie cookie = new Cookie(name, URLEncoder.encode(value, charType));
		cookie.setPath(path);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
	public static Cookie createCookie(String name, String value,String path, int maxAge, String domain) throws IOException {
		Cookie cookie = new Cookie(name, URLEncoder.encode(value, charType));  //이름과 값
		cookie.setPath(path);  //경로
		cookie.setMaxAge(maxAge);  //생존시간
		cookie.setDomain(domain);  //도메인
		return cookie;
	}
}
