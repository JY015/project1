package com.jy.pro1.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jy.pro1.dto.LoginDTO;
import com.jy.pro1.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public String login(HttpServletRequest req) {
//		System.out.println(req.getParameter("id"));
//		System.out.println(req.getParameter("pw"));

		LoginDTO dto = new LoginDTO();
		dto.setM_id(req.getParameter("id"));
		dto.setM_pw(req.getParameter("pw"));

		// id, pw를 보냇을 때 무엇이 오면 좋을까?
		// 이름 + count(*)
//		LoginDTO result = loginService.login(dto);
		dto = loginService.login(dto);

//		System.out.println(result.getM_name());
//		System.out.println(result.getCount());

		if (dto.getCount() == 1) {
			// 세션을 만들어서 로그인을 지정시간동안 유지
			HttpSession session = req.getSession();
			session.setAttribute("mname", dto.getM_name());
			session.setAttribute("mid", req.getParameter("id"));
			// 세션 : 서버, 쿠키 : 클라이언트
//			System.out.println();
//			System.out.println();
//			System.out.println();
//			System.out.println();
//			System.out.println();
//			System.out.println();
//			System.out.println();
//			System.out.println();
//			System.out.println();
//			System.out.println();
//			System.out.println();
//			System.out.println();
//			System.out.println();
//			String str1="..............................................................................................................................................";
//			String str2="!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!";
//			String str3="@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@";
//			String str4="########################################################################";
//			String str5="$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$";
//			String str6="%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%";
//			String str7="^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^";
//			String str8="&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&";
//			String str9="************************************************************************";
//			String str10="((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((";
//			String str11="\")))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))";
//			String str12="..............................................................................................................................................";
//			dto.setBcontent(dto.getBcontent().replaceAll(")))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))",")))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))\n"));
//			dto.setBcontent(dto.getBcontent().replaceAll("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++","+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n"));
//			dto.setBcontent(dto.getBcontent().replaceAll("===================================================================","===================================================================\n"));
//			dto.setBcontent(dto.getBcontent().replaceAll(";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;",";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;\n"));
//			dto.setBcontent(dto.getBcontent().replaceAll("'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''","'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''\n"));
//			dto.setBcontent(dto.getBcontent().replaceAll("////////////////////////////////////////////////////////////////////////////////////////////////////////////////////","////////////////////////////////////////////////////////////////////////////////////////////////////////////////////\n"));
////			dto.setBcontent(dto.getBcontent().replaceAll("?????????????????????????????????????????????????????????????????????????????????","?????????????????????????????????????????????????????????????????????????????????\n"));
//			dto.setBcontent(dto.getBcontent().replaceAll("```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````","```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````\n"));
//			dto.setBcontent(dto.getBcontent().replaceAll("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~","~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"));
//			dto.setBcontent(dto.getBcontent().replaceAll("_______________________________________________________________________________","_______________________________________________________________________________\n"));
//			

			return "redirect:index"; // 정상적으로 로그인 했다면 인덱스로 가기
		} else {
			return "login"; // 암호 아이디가 일치하지 않은 사람은 다시 로그인
		}
	}

	@GetMapping("/logout")
	public String Logout(HttpSession session) {

//		if(session.getAttribute("mname")!=null) {
////			session.invalidate(); // 세션 삭제하기
//			session.removeAttribute("mname");
//		}
//
//		if(session.getAttribute("mid")!=null) {
////			session.invalidate(); // 세션 삭제하기
//			session.removeAttribute("mid");
//		}

		session.setMaxInactiveInterval(0); // 세션 유지시간을 0으로 = 종료시키기
		session.invalidate(); // 세션 초기화 = 종료

		return "redirect:index";
	}

	@GetMapping("/myInfo")
	public String myInfo(HttpServletRequest req) {
		
		return "myInfo";

	}
}
