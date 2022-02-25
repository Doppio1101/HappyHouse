package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.memberDao;
import com.ssafy.happyhouse.model.dto.memberDto;

@Service
public class memberServiceImpl implements memberService {
	
	@Autowired
	private memberDao memberDao;
	
	@Override
	public int idChk(String userid) throws SQLException {
		return memberDao.idChk(userid);
	}

	@Override
	public int insertMember(memberDto memberDto) throws SQLException {
		return memberDao.insertMember(memberDto);
	}

	@Override
	public int updateMember(memberDto memberDto) throws SQLException {
		return memberDao.updateMember(memberDto);
	}

	@Override
	public int deleteMember(String userid) throws SQLException {
		return memberDao.deleteMember(userid);
	}

	@Override
	public memberDto login(memberDto memberDto) throws SQLException {
		return memberDao.login(memberDto);
	}
	
	@Override
	public memberDto detailMember(String userid) throws SQLException {
		return memberDao.detailMember(userid);
	}

	@Override
	public int updatePw(memberDto memberDto) throws SQLException {
		return memberDao.updatePw(memberDto);
	}

}
