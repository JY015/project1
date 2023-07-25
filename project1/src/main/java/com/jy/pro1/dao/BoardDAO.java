package com.jy.pro1.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jy.pro1.dto.BoardDTO;

// Inject 사용해보기

@Repository
public class BoardDAO {
	
	@Autowired
	private SqlSession sqlSession;

	public List<BoardDTO> boardList(){
		return sqlSession.selectList("board.boardList");
	}

	public BoardDTO detail(BoardDTO dto2) {
		// 앞에는 네임스페이스.아이디, 값
		return (BoardDTO) sqlSession.selectOne("board.detail", dto2);
	}

	public void write(BoardDTO dto) {
		sqlSession.insert("board.write",dto);
	}

	public void delete(BoardDTO dto) {
		sqlSession.delete("board.delete", dto);
	}
	
	public void update(BoardDTO dto) {
		sqlSession.update("board.update",dto);
		
	}

}
