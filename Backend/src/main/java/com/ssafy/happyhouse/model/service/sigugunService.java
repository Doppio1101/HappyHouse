package com.ssafy.happyhouse.model.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.ssafy.happyhouse.model.dto.dongDto;
import com.ssafy.happyhouse.model.dto.gugunDto;
import com.ssafy.happyhouse.model.dto.houseInfoDto;
import com.ssafy.happyhouse.model.dto.sidoDto;

public interface sigugunService {
	
	public List<sidoDto> getSidoList();//처음 선택 박스 생성
	public List<gugunDto> getGugunList(String sidoCode);//시도가 변경되었을 때 구군 리스트를 반환
	public List<dongDto> getDongList(String gugunCode);//구군이 변경되었을 때 동 리스트를 반환
	public List<houseInfoDto> getAptList(String pageNo) throws Exception;//최신화 할 메소드(최신화 할 때만 사용)
//	public int updateData(gugunDto dto);//구군 업데이트
	public int updateData(dongDto dto);//동 업데이트

}
