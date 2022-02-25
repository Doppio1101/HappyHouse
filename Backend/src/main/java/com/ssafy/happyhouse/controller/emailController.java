package com.ssafy.happyhouse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.service.EmailService;
import com.ssafy.happyhouse.model.service.EmailServiceImpl;

@RestController
@RequestMapping("/email")
@CrossOrigin("*")
public class emailController {
	
	private static final Logger logger = LoggerFactory.getLogger(emailController.class);
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private EmailService emailService;

	@PostMapping("/mailConfirm/{email}")
	public ResponseEntity<String> emailConfirm(@PathVariable String email) throws Exception {
		logger.debug("emailConfirm");
		// System.out.println("전달 받은 이메일 : " + email);
		String authKey = emailService.sendSimpleMessage(email);
		// System.out.println("authKey:" + authKey);

		if (authKey != null) {
			return new ResponseEntity<String>(authKey, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
//	@PostMapping("/verifyCode")
//	@ResponseBody
//	public int verifyCode(String code) {
//		logger.info("Post verifyCode");
//		
//		int result = 0;
//		System.out.println("code : "+code);
//		System.out.println("code match : "+ EmailServiceImpl.ePw.equals(code));
//		if(EmailServiceImpl.ePw.equals(code)) {
//			result =1;
//		}
//		
//		return result;
//	}
}
