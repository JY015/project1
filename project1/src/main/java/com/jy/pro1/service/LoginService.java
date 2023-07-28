package com.jy.pro1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pro1.dao.LoginDAO;
import com.jy.pro1.dto.LoginDTO;
import com.jy.pro1.dto.MemberDTO;

@Service
public class LoginService {
	
	@Autowired
	private LoginDAO loginDAO;

	public LoginDTO login(LoginDTO dto) {
		return loginDAO.login(dto);
	}

	public int register(MemberDTO memberDTO) {
		return loginDAO.register(memberDTO);
	}

	public List<MemberDTO> members() {

		return loginDAO.members();
	}

	/*
	 * public int idCheck(String m_id) { return loginDAO.idcheck(m_id); }
	 */


}
