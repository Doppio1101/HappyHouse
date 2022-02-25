package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.qaDto;
import com.ssafy.happyhouse.model.service.qaService;


@RestController
@RequestMapping("/QA")
@CrossOrigin("*")
public class qaController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private qaService service;
	
	@PostMapping//글 등록
	public ResponseEntity<String> regist(@RequestBody qaDto qaDto) throws SQLException{
		int result = service.registQA(qaDto);
		if(result == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/{start}")//페이지 처리 된 리스트 불러오기
	public ResponseEntity<List<qaDto>> list(@PathVariable("start") int start) throws SQLException{
		List<qaDto> list = new ArrayList<qaDto>();
		list = service.list(start);
		return new ResponseEntity<List<qaDto>>(list,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> listCount() throws SQLException{
		int result = service.ListCount();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", result);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
//	@GetMapping("/detail/{no}")
//	public ResponseEntity<Map<String, Object>> detail(@PathVariable("no") int no,HttpSession session) throws SQLException{
//		memberDto member = (memberDto) session.getAttribute("userinfo");
//		Map<String, Object> map = new HashMap<String, Object>();
//		int result = service.checkQA(no, member.getUserId());
//		if(result == 1) {
//			map.put("login", SUCCESS);
//		}else {
//			map.put("login", FAIL);			
//		}
//		QADto dto = service.detailQA(no);
//		map.put("detailData", dto);
//		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
//	}
	@GetMapping("/detail/{no}")
	public ResponseEntity<Map<String, Object>> detail(@PathVariable("no") int no) throws SQLException{
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		qaDto dto = service.detailQA(no);
		map.put("detailData", dto);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	// 게시글 수정
	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestBody qaDto qaDto) throws SQLException{
		
		System.out.println(qaDto);
		int result = service.updateQA(qaDto);
		if(result == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	// 게시글 삭제
	@DeleteMapping("/delete/{no}")
	public ResponseEntity<String> delete(@PathVariable("no") int no) throws SQLException{
		int result = service.deleteQA(no);
		if(result == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
