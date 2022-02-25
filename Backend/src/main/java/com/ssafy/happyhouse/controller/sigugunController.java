package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import com.ssafy.happyhouse.model.dto.dongDto;
import com.ssafy.happyhouse.model.dto.gugunDto;
import com.ssafy.happyhouse.model.dto.houseInfoDto;
import com.ssafy.happyhouse.model.dto.sidoDto;
import com.ssafy.happyhouse.model.service.sigugunService;

@RestController
@RequestMapping("/sigugun")
@CrossOrigin("*")
public class sigugunController {
	
	@Autowired
	private sigugunService service;
	
	@GetMapping("/sido")
	public ResponseEntity<List<sidoDto>> getSidoList(){
		List<sidoDto> result = service.getSidoList();
		if(result != null) {
			return new ResponseEntity<List<sidoDto>>(result,HttpStatus.OK);
		}
		return new ResponseEntity<List<sidoDto>>(result,HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/gugun/{sidoCode}")
	public ResponseEntity<List<gugunDto>> getGugunList(@PathVariable("sidoCode") String sidoCode) throws Exception{
		List<gugunDto> result = service.getGugunList(sidoCode);
		if(result != null) {
			return new ResponseEntity<List<gugunDto>>(result,HttpStatus.OK);
		}
		return new ResponseEntity<List<gugunDto>>(result,HttpStatus.NO_CONTENT);
	}
	
//	@GetMapping("/dong/{gugunCode}")
//	public ResponseEntity<List<dongDto>> getDongList(@PathVariable("gugunCode") String gugunCode) throws IOException{
//		List<dongDto> result = service.getDongList(gugunCode);
//		if(result != null) {
//			return new ResponseEntity<List<dongDto>>(result,HttpStatus.OK);
//		}
//		return new ResponseEntity<List<dongDto>>(result,HttpStatus.NO_CONTENT);
//	}
	
	@GetMapping("/dong/{gugunCode}")
	public ResponseEntity<Map<String, Object>> getAptList(@PathVariable("gugunCode") String gugunCode){
		List<houseInfoDto> result = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			result = service.getAptList(gugunCode);
			map.put("status", "success");
			map.put("data", result);
		} catch (Exception e) {
			
			map.put("status", "fail");
		}

		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@PostMapping("/gu")
	public ResponseEntity<String> updateData(@RequestBody dongDto dto){
		System.out.println(dto);
		int result = service.updateData(dto);
		if(0<result) {
			return new ResponseEntity<String>("새롭게 저장 됨",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("이미 있거나 저장 안 됨",HttpStatus.OK);			
		}
	}
	
	
}
