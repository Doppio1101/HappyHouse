package com.ssafy.happyhouse.model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.ssafy.happyhouse.model.dao.sigugunDao;
import com.ssafy.happyhouse.model.dto.dongDto;
import com.ssafy.happyhouse.model.dto.gugunDto;
import com.ssafy.happyhouse.model.dto.houseInfoDto;
import com.ssafy.happyhouse.model.dto.sidoDto;

@Service
public class sigugunServiceImpl implements sigugunService{

	@Autowired
	sigugunDao dao;
	
	@Override
	public List<sidoDto> getSidoList() {
		return dao.getSidoList();
	}

	@Override
	public List<gugunDto> getGugunList(String sidoCode) {
		return dao.getGugunList(sidoCode);
	}

	@Override
	public List<dongDto> getDongList(String gugunCode) {
		return dao.getDongList(gugunCode);
	}
	
	
	@Override
	public List<houseInfoDto> getAptList(String LAWD_CD) throws Exception {
//		Map<String, String> map = new HashMap<String, String>();
		StringBuilder urlBuilder = new StringBuilder("http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=YUXDqmqEzRerqaMMuLSw5B9%2Fl8pSzgEZDCdPkvFy%2B26jaZeYBBgmhRaHO91sM7xuGXq23HY2d9JN5P4FZzKnQA%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode("YUXDqmqEzRerqaMMuLSw5B9/l8pSzgEZDCdPkvFy+26jaZeYBBgmhRaHO91sM7xuGXq23HY2d9JN5P4FZzKnQA==", "UTF-8")); /*??????????????????????????? ?????? ?????????*/
        urlBuilder.append("&" + URLEncoder.encode("page","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*???????????????*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100000", "UTF-8")); /*??? ????????? ?????? ???*/
        urlBuilder.append("&" + URLEncoder.encode("LAWD_CD","UTF-8") + "=" + URLEncoder.encode(LAWD_CD, "UTF-8")); /*????????? ??????*/
        urlBuilder.append("&" + URLEncoder.encode("DEAL_YMD","UTF-8") + "=" + URLEncoder.encode("202108", "UTF-8")); /*????????? ??????*/
//        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*????????????(xml, json) default : xml*/
        
        URL url = new URL(urlBuilder.toString());
        
        Document info = null;
        info= DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url.toString());
        info.getDocumentElement().normalize();
        System.out.println(info.getDocumentElement().getNodeName());
        
        Element root = info.getDocumentElement();
        NodeList nList = root.getElementsByTagName("items").item(0).getChildNodes();
        List<houseInfoDto> list = new ArrayList<houseInfoDto>();
        for(int i=0; i<nList.getLength(); i++) {
        	Node node = nList.item(i);
        	Element eElement = (Element) node;
        	String dealAmount = eElement.getElementsByTagName("????????????").item(0).getChildNodes().item(0).getNodeValue().trim();
        	String buildYear = eElement.getElementsByTagName("????????????").item(0).getChildNodes().item(0).getNodeValue().trim();
        	String roadName = eElement.getElementsByTagName("?????????").item(0).getChildNodes().item(0).getNodeValue().trim();
        	String dongName = eElement.getElementsByTagName("?????????").item(0).getChildNodes().item(0).getNodeValue().trim();
        	String gugunCode = eElement.getElementsByTagName("????????????????????????").item(0).getChildNodes().item(0).getNodeValue().trim();
        	String dongCode = eElement.getElementsByTagName("????????????????????????").item(0).getChildNodes().item(0).getNodeValue().trim();
        	String aptName = eElement.getElementsByTagName("?????????").item(0).getChildNodes().item(0).getNodeValue().trim();
//        	String aptNum = eElement.getElementsByTagName("????????????").item(0).getChildNodes().item(0).getNodeValue().trim();
        	String area = eElement.getElementsByTagName("????????????").item(0).getChildNodes().item(0).getNodeValue().trim();
        	String jibun = eElement.getElementsByTagName("??????").item(0).getChildNodes().item(0).getNodeValue().trim();
        	String lawdCd = eElement.getElementsByTagName("????????????").item(0).getChildNodes().item(0).getNodeValue().trim();
        	String floor = eElement.getElementsByTagName("???").item(0).getChildNodes().item(0).getNodeValue().trim();
        	
        	list.add(new houseInfoDto(lawdCd+(i+1),dealAmount, buildYear, roadName, dongName, gugunCode, dongCode, aptName, area, jibun, lawdCd, floor,null,null));
//        	System.out.println(eElement);
        
        }
        return list;
//        map.put("data", sb.toString());
//        return map;
	}
	//?????? ?????????
	
//	@Override
//	public int updateData(gugunDto dto) {
//		int check = dao.checkItem(dto.getGugunCode());
//		int result = 0;
//		if(0<check) {
//			//???????????? ?????? ??????.
//		}else {
//			result = dao.updateData(dto);
//		}
//		return result;
//	}

	@Override
	public int updateData(dongDto dto) {
		int check = dao.checkItem(dto.getDongCode());
		int result = 0;
		if(0<check) {
			//???????????? ?????? ??????.
		}else {
			result = dao.updateData(dto);
		}
		return result;
	}
}
