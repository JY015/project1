package com.jy.pro1.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jy.pro1.dto.BoardDTO;
import com.jy.pro1.dto.PageDTO;
import com.jy.pro1.service.BoardService;
import com.jy.pro1.util.Util;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class BoardController {
	// user -> Controller -> service -> DAO -> mybatis
	
	@Autowired
	private Util util;

	// @Service("boardService") 여기쓴 이름과 동일하게 써야됨
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board")
	public String board(@RequestParam(value="pageNo", required=false, defaultValue="1") int pageNo, Model model) {
		// 서비스에서 값 가져오기
		// 페이지네이션인포 -> 값 넣고 -> DB -> jsp
		//PaginationInfo에 필수 정보를 넣어 준다.
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pageNo); //현재 페이지 번호
		paginationInfo.setRecordCountPerPage(10); //한 페이지에 게시되는 게시물 건수
		paginationInfo.setPageSize(10); //페이징 리스트의 사이즈
		//전체 글 수 가져오는 명령문장
		int totalCount = boardService.totalCount();
		paginationInfo.setTotalRecordCount(totalCount);  //전체 게시물 건 수
		
		int firstRecordIndex = paginationInfo.getFirstRecordIndex();//시작위치
		int recordCountPerPage = paginationInfo.getRecordCountPerPage();//페이지당 몇 개?
		
		//System.out.println(firstRecordIndex);
		//System.out.println(recordCountPerPage);
		//System.out.println(pageNo);
		//System.out.println(totalCount);
		
		PageDTO page = new PageDTO();
		page.setFirstRecordIndex(firstRecordIndex);
		page.setRecordCountPerPage(recordCountPerPage);
		
		//보드서비스 수정합니다.
		List<BoardDTO> list = boardService.boardList(page); 
		
		model.addAttribute("list", list);
		//페이징 관련 정보가 있는 PaginationInfo 객체를 모델에 반드시 넣어준다.
		model.addAttribute("paginationInfo", paginationInfo);
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
//		System.out.println(res.getCommentcount());
		if(res.getCommentcount() > 0) {
			//db에 물어봐서 jsp로 보냄
			List<Map<String, Object>> commentsList = boardService.commentslist(bno);
			m.addAttribute("commentsList",commentsList);
		}
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
			dto.setUuid(UUID.randomUUID().toString());
//			System.out.println("======================");
//			System.out.println(dto.getUuid());
//			System.out.println(dto.getUuid().length());
//			System.out.println("======================");
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

		HttpSession session = req.getSession();
		System.out.println("mid : " + session.getAttribute("mid"));

		int bno = Integer.parseInt(req.getParameter("bno"));
		if (session.getAttribute("mid") != null ) {
			BoardDTO dto = new BoardDTO();
			dto.setBno(bno);
			dto.setM_id((String) session.getAttribute("mid"));
			// 추후 로그인을 하면 사용자의 정보도 담아서 보낸다.

			boardService.delete(dto);

			return "redirect:board"; // 다시 컨트롤러 지나가기 GET방식으로 간다.
		} else {
			// 로그인 안했음 = 로그인 해
			return "redirect:/login";
		}
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
		// 로그인 하지 않으면 로그인 화면으로 던지기
		if (session.getAttribute("mid") != null) {

			// dto를 하나 만들어서 거기에 담겠다. = bno, mid
			// db에 bno를 보내서 dto를 얻어온다.
			BoardDTO dto = new BoardDTO();
			dto.setBno(util.strToInt(req.getParameter("bno")));
			// 내 글만 수정할 수 있도록 세션에 있는 mid도 보낸다.
			dto.setM_id((String) session.getAttribute("mid"));

			BoardDTO res = boardService.detail(dto);
			if(res != null) { // 내 글 수정
					m.addAttribute("dto", res);
					return "update";
			}else { // 다른 사람 글이면 null
					return "warning";
			}

		} else {
			return "redirect:/login";
		}
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
