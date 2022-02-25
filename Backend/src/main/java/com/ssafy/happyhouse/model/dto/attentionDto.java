package com.ssafy.happyhouse.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class attentionDto {
	private int no;
	private String userid;
	private String bname;
	private String sigungucode;
	private String postnum;
	private String address;
	private String detailaddress;
	private String lat;
	private String lng;
	private String nickname;
	private String totaladdress;
}
