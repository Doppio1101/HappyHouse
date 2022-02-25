package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.attentionDto;

@Mapper
public interface attentionDao {
	public int insertAttention(attentionDto attentionDto) throws SQLException;
	public List<attentionDto> detailAttention(String userid) throws SQLException;
	public int updateAttention(attentionDto attentionDto) throws SQLException;
	public int deleteAttention(String userid) throws SQLException;
}
