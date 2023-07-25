package com.jy.pro1.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jy.pro1.dto.BoardDTO;
import com.jy.pro1.service.BoardService;
import com.jy.pro1.util.Util;

@Controller
public class BoardController {
	// user -> Controller -> service -> DAO -> mybatis
	
	@Autowired
	private Util util;

	// @Service("boardService") 여기쓴 이름과 동일하게 써야됨
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board")
	public String board(Model m) {
		// 서비스에서 값 가져오기
		m.addAttribute("list", boardService.boardList());

		return "board";
	}

	// 파라미터로 들어오는 값 잡기
	@GetMapping("/detail")
	// Model은 jsp에 값을 붙이기 위해 넣는다.
	public String detail(HttpServletRequest req, Model m) {
		// bno에 요청하는 값이 있다. 이 값을 db까지 보낸다.
		int bno = util.strToInt(req.getParameter("bno"));
	
//		DTO로 변경합니다.
		BoardDTO dto = new BoardDTO();
		dto.setBno(bno);
//		dto.setM_id(null); 글 상세보기에서는 mid가 없어도 된다.
		
		BoardDTO res = boardService.detail(dto);
		m.addAttribute("dto", res);
		
//		m.addAttribute("bno",dto.getBno());
		return "detail";
	}

	@GetMapping("/write")
	public String write(HttpServletRequest req) {
		HttpSession session = req.getSession();
		if(session.getAttribute("mname") != null) {
			return "write";
		} else {			
			return "redirect:/login";
		}
	}

	@PostMapping("/write")
	public String write(HttpServletRequest req, BoardDTO dto ) {

		HttpSession session = req.getSession();
		if(session.getAttribute("mid")!=null) {
			// 로그인 했음.
			dto = new BoardDTO();
			dto.setBtitle(req.getParameter("title"));
			dto.setBcontent(req.getParameter("content"));
			//세션에서 불러오기
			dto.setM_id((String)session.getAttribute("mid")); // 세션에서 가져옴
			dto.setM_name((String)session.getAttribute("mname")); // 세션에서 가져옴

			// Service -> DAO -> mybatis -> DB로 보내서 저장하기
			
			boardService.write(dto);
			
			return "redirect:board"; // 다시 컨트롤러 지나가기 GET방식으로 간다.
		} else {
			// 로그인 안했음 = 로그인 해
			return "redirect:/login"; 
		}
		// 사용자가 입력한 데이터 변수에 담기
//		System.out.println(req.getParameter("title"));
//		System.out.println(req.getParameter("content"));
	}
	
	@GetMapping("/delete")
//	public String delete(@RequestParam(value="bno", required=false, defaultValue="0") int bno) HttpServletRequest
//		System.out.println("bno : "+bno);
	public String delete(HttpServletRequest req) {
//		System.out.println(req.getParameter("bno"));
		int bno = Integer.parseInt(req.getParameter("bno"));
		BoardDTO dto = new BoardDTO();
		dto.setBno(bno);
		// 추후 로그인을 하면 사용자의 정보도 담아서 보낸다.
		
		boardService.delete(dto);
		
		return "redirect:board";
	}
	
//	public ModelAndView getUpdate(@RequestParam("bno") int bno) {
//		ModelAndView mv = new ModelAndView("update"); //jsp
//		int bno = Integer.parseInt(req.getParameter("bno"));	
//		BoardDTO dto = boardService.detail(bno);
//		
//		return mv;
//	}
	
	@GetMapping("/update")
	public String getUpdate(HttpServletRequest req, Model m) {
		
		HttpSession session = req.getSession();
		
		// dto를 하나 만들어서 거기에 담겠다. = bno, mid
		// db에 bno를 보내서 dto를 얻어온다.
		BoardDTO dto = new BoardDTO();
		dto.setBno(util.strToInt(req.getParameter("bno")));
		// 내 글만 수정할 수 있도록 세션에 있는 mid도 보낸다.
		dto.setM_id((String)session.getAttribute("mid"));

		
		BoardDTO res = boardService.detail(dto);

		m.addAttribute("dto", res);
		return "update";
	}
	
	@PostMapping("/update")
	/*
	 * public String postUpdate(@RequestParam Map<String, Object> map) {
	 * System.out.println("map: "+ map); return "redirect:board"; 
	 * }
	 */
	
	/*
	 * public String postUpdate(BoardDTO dto) { }
	 */
	
	public String postUpdate(HttpServletRequest req) {
		int bno = util.strToInt(req.getParameter("bno"));
		String btitle = req.getParameter("title");
		String bcontent = req.getParameter("content");
//		System.out.println(bno);
//		System.out.println("제목 : "+btitle);
//		System.out.println("내용 : "+bcontent);
		
		BoardDTO dto = new BoardDTO();
		dto.setBno(bno);
		dto.setBtitle(btitle);
		dto.setBcontent(bcontent);

		boardService.update(dto);

		return "redirect:detail?bno="+bno;
	}

}
