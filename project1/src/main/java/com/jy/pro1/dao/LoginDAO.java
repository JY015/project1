package com.jy.pro1.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jy.pro1.dto.LoginDTO;
import com.jy.pro1.dto.MemberDTO;

@Repository
public class LoginDAO {

	@Autowired
	private SqlSession sqlSession;
	

	public LoginDTO login(LoginDTO dto) {
		// 뒤에 들어가는 변수는 DB에 들어감
		return (LoginDTO) sqlSession.selectOne("login.login",dto);
	}

	public int register(MemberDTO memberDTO) {
		return sqlSession.insert("login.register", memberDTO);
	}
	
	public int idcheck(String m_id) {
		return sqlSession.selectOne("login.idCheck", m_id);
	}

	public List<MemberDTO> members() {
		return sqlSession.selectList("login.members");
	}

	public int checkID(String id) {
		return sqlSession.selectOne("login.checkID", id);
	}

	public List<Map<String, Object>> boardList2(int i) {
		return sqlSession.selectList("login.boardList2",i);
	}

	public int totalCount() {
		return sqlSession.selectOne("login.totalCount");
	}

	
}
