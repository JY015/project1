package com.jy.pro1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.jy.pro1.util.Util;

@Controller
public class IndexController {
	@Autowired
	private Util util;
	
	// 첫화면 로딩 : index.jsp 호출
	@GetMapping(value={"/", "index"})
	public String index() {
		if(util.getIp().equals("172.30.1.6")) {
			System.out.println("나");
		}else if(util.getIp().equals("172.30.1.20")) {
			System.out.println("대원 접속");
		}else if(util.getIp().equals("172.30.1.76")) {
			System.out.println("대규 접속");
		}else if(util.getIp().equals("172.30.1.62")) {
			System.out.println("지선 접속");
		}else{
			System.out.println(util.getIp());
		}
		return "index"; // 데이터 붙임 없이 index.jsp 페이지만 보여준다.
	}
	
	@GetMapping("/menu")
	public String menu() {
		return "menu";
	}
	
	@GetMapping("/board2")
	public String board2() {
		return "board2";
	}
	
	@GetMapping("/mooni")
	public String mooni() {
		return "mooni";
	}
	
	@GetMapping("/notice")
	public String notice() {
		return "notice";
	}
}
