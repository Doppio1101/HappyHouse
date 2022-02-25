package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.qaDto;


public interface qaService {
	
	public List<qaDto> list() throws SQLException;
	public List<qaDto> list(String key, String val) throws SQLException;
	public List<qaDto> list(int start) throws SQLException;
	public int ListCount() throws SQLException;
	public int registQA(qaDto qaDto) throws SQLException;
	public int updateQA(qaDto qaDto) throws SQLException;
	public int deleteQA(int no) throws SQLException;
	public qaDto detailQA(int no) throws SQLException;
	public int checkQA(int no, String userid) throws SQLException;

	
}
