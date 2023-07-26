package com.jy.pro1.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jy.pro1.dto.MemberDTO;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSession sqlSession;

	public void regiseter(MemberDTO dto) {
		sqlSession.insert("member.register", dto);
	}

}
