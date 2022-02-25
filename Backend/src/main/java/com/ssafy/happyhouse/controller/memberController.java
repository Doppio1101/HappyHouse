package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.HashMap;
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

import com.ssafy.happyhouse.model.dto.memberDto;
import com.ssafy.happyhouse.model.service.JwtServiceImpl;
import com.ssafy.happyhouse.model.service.memberService;

@RestController
@RequestMapping("/member")
@CrossOrigin("*")
public class memberController {
	
	private static final Logger logger = LoggerFactory.getLogger(memberController.class);

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private memberService service;
	
	@Autowired
	private JwtServiceImpl jwtservice;
	
	@GetMapping("/idchk/{userid}")
	public ResponseEntity<String> idChk(@PathVariable String userid) throws SQLException {
		int result = service.idChk(userid);
		if(result == 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@PostMapping("/regist")
	public ResponseEntity<String> insertMember(@RequestBody memberDto memberDto) throws SQLException {
		int result = service.insertMember(memberDto);
		if(result == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateMember(@RequestBody memberDto memberDto) throws SQLException {
		int result = service.updateMember(memberDto);
		if(result == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/delete/{userid}")
	public ResponseEntity<String> deleteMember(@PathVariable String userid) throws SQLException {
		int result = service.deleteMember(userid);
		if(result == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody memberDto memberDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			memberDto loginUser = service.login(memberDto);
			if (loginUser != null) {
				String token = jwtservice.create("userid", loginUser.getUserid(), "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/detail/{userid}")
	public ResponseEntity<Map<String, Object>> detailMember(@PathVariable String userid, HttpServletRequest request) throws SQLException {
		Map<String, Object> membermap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		
		if (jwtservice.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!");
			try {
				//로그인 사용자 정보.
				memberDto dto = service.detailMember(userid);
				membermap.put("userInfo", dto);
				membermap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				membermap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!");
			membermap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(membermap, status);
	}
	
	@PutMapping("/updatepw")
	public ResponseEntity<String> updatePw(@RequestBody memberDto memberDto) throws SQLException {
		int result = service.updatePw(memberDto);
		if(result == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
