package com.ssafy.happyhouse.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class answerDto {
	private int qno;
	private String title;
	private String content;
	private Date registTime;
	private Date uptTime;
}
