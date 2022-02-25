package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.qaDao;
import com.ssafy.happyhouse.model.dto.qaDto;

@Service
public class qaServiceImpl implements qaService {

	@Autowired
	private qaDao dao;
	
	@Override
	public List<qaDto> list() throws SQLException {
		return dao.list();
	}

	@Override
	public List<qaDto> list(String key, String val) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("key", key);
		map.put("val", val);
		return dao.search(map);
	}

	@Override
	public List<qaDto> list(int start) throws SQLException {
		return dao.paging(start);
	}

	@Override
	public int ListCount() throws SQLException {
		return dao.ListCount();
	}

	@Override
	public int registQA(qaDto qaDto) throws SQLException {
		return dao.registQA(qaDto);
	}

	@Override
	public int updateQA(qaDto qaDto) throws SQLException {
		return dao.updateQA(qaDto);
	}

	@Override
	public int deleteQA(int no) throws SQLException {
		return dao.deleteQA(no);
	}

	@Override
	public qaDto detailQA(int no) throws SQLException {
		return dao.detailQA(no);
	}

	@Override
	public int checkQA(int no, String userid) throws SQLException {
		qaDto dto = new qaDto();
		dto.setNo(no);
		dto.setUserid(userid);
		return dao.checkQA(dto);
	}
	
}
