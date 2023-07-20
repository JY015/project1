package com.jy.pro1;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {
	// user -> Controller -> service -> DAO -> mybatis
	
	@Autowired
	private Util util;

	@Resource(name = "boardService")
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
	// Model은 jsp에 값을 붙이기 위해 넣는다.
	public String detail(HttpServletRequest req, Model m) {
		// bno에 요청하는 값이 있다. 이 값을 db까지 보낸다.
		int bno = util.strToInt(req.getParameter("bno"));
		BoardDTO dto = boardService.detail(bno);
		m.addAttribute("dto", dto);
//		m.addAttribute("bno",dto.getBno());
		return "detail";
	}

	@GetMapping("/write")
	public String write() {
		return "write";
	}

	@PostMapping("/write")
	public String write(HttpServletRequest req) {

		// 사용자가 입력한 데이터 변수에 담기
//		System.out.println(req.getParameter("title"));
//		System.out.println(req.getParameter("content"));
		BoardDTO dto = new BoardDTO();
		dto.setBtitle(req.getParameter("title"));
		dto.setBcontent(req.getParameter("content"));
		dto.setBwrite("홍길동");
		boardService.write(dto);

		// Service -> DAO -> mybatis -> DB로 보내서 저장하기

		return "redirect:board"; // 다시 컨트롤러 지나가기 GET방식으로 간다.
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
		int bno = Integer.parseInt(req.getParameter("bno"));
		BoardDTO dto = boardService.detail(bno);
		m.addAttribute("dto",dto);
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
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}

}
