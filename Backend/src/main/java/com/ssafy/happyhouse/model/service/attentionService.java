package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.attentionDto;

public interface attentionService {

	public int insertAttention(attentionDto attentionDto) throws SQLException;
	public List<attentionDto> detailAttention(String userid) throws SQLException;
	public int updateAttention(attentionDto attentionDto) throws SQLException;
	public int deleteAttention(String userid) throws SQLException;
}
