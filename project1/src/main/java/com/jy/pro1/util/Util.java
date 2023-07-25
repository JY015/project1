package com.jy.pro1.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

// Controller Service Repository
// Component = 객체

@Component
public class Util {
	// 문자열이 들어오면 숫자로 변경
	public int strToInt(String str) {
		// 숫자로 바꿀 수 있는 경우 숫자로, 숫자로 못 바꾼다면?
		int result = 0;
		
		try {
			result = Integer.parseInt(str);
		}catch (Exception e) {
//			String re=""; // 숫자인것만 모을 스트링
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<str.length(); i++) {
				if(Character.isDigit(str.charAt(i))) { // 숫자인지 물어봄
					sb.append(str.charAt(i)); //숫자만 모아서
				}
			}
			result = Integer.parseInt(sb.toString()); //숫자로 만들어서
		}
		return result;
	}
	
	
	public String exchange(String str) {
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		return str;
	}
	
	public String getIp() {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();

		// 상대방 ip 가져오기
		String ip = null;
		ip = req.getHeader("X-Forwarded-For");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("X-Real-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("X-RealIP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("REMOTE_ADDR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getRemoteAddr();
		}

		return ip;
	}
}
