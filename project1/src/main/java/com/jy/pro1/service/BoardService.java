package com.jy.pro1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pro1.dao.BoardDAO;
import com.jy.pro1.dto.BoardDTO;
import com.jy.pro1.util.Util;

@Service
public class BoardService {
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Autowired
	private Util util;
	
	// 보드 리스트 불러오는 메소드
	public List<BoardDTO> boardList(){
		return boardDAO.boardList();
	}

	public BoardDTO detail(BoardDTO dto2) {
		// 좋아요 수 +1하기 기능 넣기
		boardDAO.views(dto2);
		
		
		BoardDTO dto = boardDAO.detail(dto2);
//		System.out.println(dto);
//		System.out.println(dto.getBno());
//		System.out.println(dto.getBip());
		
		if(dto !=null ) { // 내 글이 아닐때 null 들어온다. 즉, null이 아닐때라고 검사
			// 여기서 ip 뽑아오기
			// ip 중간 **
			// 172.**.1.19
			if(dto.getBip() !=null && dto.getBip().indexOf(".") != -1){
				String[] strArr = dto.getBip().split("[.]");
				strArr[1] = "**";
				dto.setBip(String.join(".", strArr));
			}
			return dto;
			
		}else {
			return null;
		}
		
	}

	public void write(BoardDTO dto) {
		// btitle을 꺼내준다. 
//		String btitle = dto.getBtitle();
//		btitle = util.exchange(dto.getBtitle());
//		dto.setBtitle(dto.getBtitle().replaceAll("<", "&lt;"));
//		dto.setBtitle(dto.getBtitle().replaceAll("<", "&gt;"));
		
		//btitle = util.exchange(dto.getBtitle());
		// 값을 변경한다. 
		//replace() < -> &lt;
		// replace() > -> &rt;
		
		dto.setBtitle(util.exchange(dto.getBtitle()));
		
		dto.setBip(util.getIp()); // 얻어온 ip도 저장해서 db로 보낸다.

		boardDAO.write(dto); // 실행만 시키고 결과를 받지 않는다.
		// select를 제외한 나머지는 영향받은 행의 수(int)를 받아오기도 한다.
	}

	public void delete(BoardDTO dto) {
		boardDAO.delete(dto);
	}

	public void update(BoardDTO dto) {
		boardDAO.update(dto);
	}


}
