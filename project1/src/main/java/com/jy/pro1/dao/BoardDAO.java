package com.jy.pro1.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jy.pro1.dto.BoardDTO;
import com.jy.pro1.dto.PageDTO;

// Inject 사용해보기

@Repository
public class BoardDAO {
	
	@Autowired
	private SqlSession sqlSession;

	public List<BoardDTO> boardList(PageDTO page){
		return sqlSession.selectList("board.boardList", page);
	}

	public BoardDTO detail(BoardDTO dto2) {
		// 앞에는 네임스페이스.아이디, 값
		return (BoardDTO) sqlSession.selectOne("board.detail", dto2);
	}

	public void write(BoardDTO dto) {
		sqlSession.insert("board.write",dto);
	}

	public void delete(BoardDTO dto) {
		sqlSession.update("board.delete", dto);
	}
	
	public void update(BoardDTO dto) {
		sqlSession.update("board.update",dto);		
	}

	public void views(BoardDTO dto2) {
		sqlSession.update("board.views", dto2);		
	}

	public int totalCount() {
		return sqlSession.selectOne("board.totalCount");
	}

	public List<Map<String, Object>> commentslist(int bno) {
		return sqlSession.selectList("board.commentslist",bno);
	}

}
