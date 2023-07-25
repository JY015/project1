package com.jy.pro1.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jy.pro1.dto.LoginDTO;

@Repository
public class LoginDAO {

	@Autowired
	private SqlSession sqlSession;

	public LoginDTO login(LoginDTO dto) {
		return (LoginDTO) sqlSession.selectOne("login.login",dto);
	}
	
}
