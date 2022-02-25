package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.attentionDao;
import com.ssafy.happyhouse.model.dto.attentionDto;

@Service
public class attentionServiceImpl implements attentionService {
	
	@Autowired
	private attentionDao attentionDao;

	@Override
	public int insertAttention(attentionDto attentionDto) throws SQLException {
		return attentionDao.insertAttention(attentionDto);
	}

	@Override
	public List<attentionDto> detailAttention(String userid) throws SQLException {
		return attentionDao.detailAttention(userid);
	}

	@Override
	public int updateAttention(attentionDto attentionDto) throws SQLException {
		return attentionDao.updateAttention(attentionDto);
	}

	@Override
	public int deleteAttention(String userid) throws SQLException {
		return attentionDao.deleteAttention(userid);
	}
}
