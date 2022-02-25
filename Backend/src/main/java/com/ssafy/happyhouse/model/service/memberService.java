package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;

import com.ssafy.happyhouse.model.dto.memberDto;

public interface memberService {

	public int idChk(String userid) throws SQLException;
	public int insertMember(memberDto memberDto) throws SQLException;
	public int updateMember(memberDto memberDto) throws SQLException;
	public int deleteMember(String userid) throws SQLException;
	public int updatePw(memberDto memberDto) throws SQLException;
	
	public memberDto login(memberDto memberDto) throws SQLException;
	public memberDto detailMember(String userid) throws SQLException;
	
}
