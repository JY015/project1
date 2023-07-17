package com.jy.pro1;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	// user -> Controller -> service -> DAO -> mybatis

	@Resource(name="boardService") 
	// @Service("boardService") 여기쓴 이름과 동일하게 써야됨
	 private BoardService boardService;
	
	@GetMapping("/board")
	public String board(Model m) {
		// 서비스에서 값 가져오기
		m.addAttribute("list", boardService.boardList());
		
		return "board";
	}
	
	// 파라미터로 들어오는 값 잡기
	@GetMapping("/detail")
	//Model은 jsp에 값을 붙이기 위해 넣는다.
	public String detail(HttpServletRequest req, Model m) {
		// bno에 요청하는 값이 있다. 이 값을 db까지 보낸다.
		String bno = req.getParameter("bno");
		BoardDTO dto = boardService.detail(bno);
		m.addAttribute("dto",dto);
//		m.addAttribute("bno",dto.getBno());
		return "detail";
	}
}

