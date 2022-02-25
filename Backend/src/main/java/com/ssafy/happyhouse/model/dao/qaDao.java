package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.qaDto;


@Mapper
public interface qaDao {
	
	public List<qaDto> list() throws SQLException;
	public List<qaDto> search(Map<String, Object> map) throws SQLException;
	public List<qaDto> paging(int start) throws SQLException;
	public int ListCount() throws SQLException;
	public int registQA(qaDto qaDto) throws SQLException;
	public int updateQA(qaDto qaDto) throws SQLException;
	public int deleteQA(int no) throws SQLException;
	public qaDto detailQA(int no) throws SQLException;
	public int checkQA(qaDto qaDto) throws SQLException;
	
}
