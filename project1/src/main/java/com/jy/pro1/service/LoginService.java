package com.jy.pro1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pro1.dao.LoginDAO;
import com.jy.pro1.dto.LoginDTO;

@Service
public class LoginService {
	
	@Autowired
	private LoginDAO loginDAO;

	public LoginDTO login(LoginDTO dto) {
		
		return loginDAO.login(dto);
	}

}
