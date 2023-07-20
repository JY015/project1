package com.jy.pro1;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("boardService")
public class BoardService {
	
	@Autowired
	private Util util;
	
	@Inject
	@Named("boardDAO")
	private BoardDAO boardDAO;
	
	// 보드 리스트 불러오는 메소드
	public List<Map<String, Object>> boardList(){
		return boardDAO.boardList();
	}

	public BoardDTO detail(int bno) {
		BoardDTO dto = boardDAO.detail(bno);
		// 여기서 ip 뽑아오기
		// ip 중간 **
		// 172.**.1.19
		if(dto.getBip() !=null && dto.getBip().indexOf(".") != -1){
		String[] strArr = dto.getBip().split("[.]");
		strArr[1] = "**";
//		System.out.println(Arrays.toString(strArr));
		dto.setBip(Arrays.toString(strArr).replaceAll("\\[","")
				.replaceAll("\\]",""));
//		System.out.println(dto.getBip());
		}
		return dto;
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
