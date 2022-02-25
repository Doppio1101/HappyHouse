package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.ssafy.happyhouse.model.dto.attentionDto;
import com.ssafy.happyhouse.model.service.JwtServiceImpl;
import com.ssafy.happyhouse.model.service.attentionService;

@RestController
@RequestMapping("/attention")
@CrossOrigin("*")
public class attentionController {

	private static final Logger logger = LoggerFactory.getLogger(attentionController.class);
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private attentionService service;
	
	@Autowired
	private JwtServiceImpl jwtservice;
	
	@PostMapping("/regist")
	public ResponseEntity<String> insertAttention(@RequestBody attentionDto attentionDto) throws SQLException {
		//System.out.println(attentionDto);
		int result = service.insertAttention(attentionDto);
		if(result == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/detail/{userid}")
	public ResponseEntity<Map<String, Object>> detailAttention(@PathVariable String userid, HttpServletRequest request) throws SQLException {
		Map<String, Object> attentionmap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		
		if (jwtservice.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!");
			try {
				//로그인 사용자 정보.
				List<attentionDto> list = service.detailAttention(userid);
				attentionmap.put("attentionInfo", list);
				attentionmap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				attentionmap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!");
			attentionmap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(attentionmap, status);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateAttention(@RequestBody attentionDto attentionDto) throws SQLException {
		int result = service.updateAttention(attentionDto);
		if(result == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/delete/{userid}")
	public ResponseEntity<String> deleteAttention(@PathVariable String userid) throws SQLException {
		int result = service.deleteAttention(userid);
		if(result == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
