package com.ssafy.happyhouse.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class qaDto {
	private int no;
	private String userid;
	private String title;
	private String content;
	private String registTime;
	private String uptTime;
}
