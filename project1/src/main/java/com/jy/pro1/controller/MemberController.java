package com.jy.pro1.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jy.pro1.dto.MemberDTO;
import com.jy.pro1.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;

	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@PostMapping("/register")
	public String register(HttpServletRequest req) {

		MemberDTO dto = new MemberDTO();
		dto.setM_id(req.getParameter("userId"));
		dto.setM_pw(req.getParameter("userPw"));
		dto.setM_name(req.getParameter("userName"));
		dto.setM_addr(req.getParameter("userAddr"));
		
		memberService.register(dto);

		return "redirect:/index";
	}
}
