package com.jy.pro1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pro1.dao.MemberDAO;
import com.jy.pro1.dto.MemberDTO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO memberDao;

	public void register(MemberDTO dto) {
		memberDao.regiseter(dto);
		
	}

}
