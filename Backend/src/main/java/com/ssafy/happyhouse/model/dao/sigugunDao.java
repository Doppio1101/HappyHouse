package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.dongDto;
import com.ssafy.happyhouse.model.dto.gugunDto;
import com.ssafy.happyhouse.model.dto.sidoDto;

@Mapper
public interface sigugunDao {
	
	public List<sidoDto> getSidoList();//처음 선택 박스 생성
	public List<gugunDto> getGugunList(String sidoCode);//시도가 변경되었을 때 구군 리스트를 반환
	public List<dongDto> getDongList(String gugunCode);//구군이 변경되었을 때 동 리스트를 반환
	public int updateList();
	public int checkItem(String code);
//	public int updateData(gugunDto dto);
	public int updateData(dongDto dto);
	
}
