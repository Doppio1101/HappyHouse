package com.ssafy.happyhouse.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class houseInfoDto {
	/*
	 * 
	 * <거래금액> 130,000</거래금액> 사용
													<거래유형> </거래유형> 미사용
		<건축년도>2004</건축년도> 사용
													<년>2015</년> 미사용
		<도로명>경희궁2길</도로명> 사용
													<도로명건물본번호코드>00005</도로명건물본번호코드> 미사용
													<도로명건물부번호코드>00005</도로명건물부번호코드> 미사용
													<도로명시군구코드>11110</도로명시군구코드> 미사용
													<도로명일련번호코드>01</도로명일련번호코드> 미사용
													<도로명지상지하코드>0</도로명지상지하코드> 미사용
						<도로명코드>4100005</도로명코드> 미사용? 사용?
		<법정동> 내수동</법정동> 사용
													<법정동본번코드>0110</법정동본번코드> 미사용
													<법정동부번코드>0015</법정동부번코드> 미사용
		<법정동시군구코드>11110</법정동시군구코드> 사용
		<법정동읍면동코드>11800</법정동읍면동코드> 사용
													<법정동지번코드>1</법정동지번코드> 미사용
		<아파트>킹스매너</아파트> 사용
													<월>12</월> 미사용
													<일>8</일> 미사용
													<일련번호>11110-118</일련번호> 미사용
		<전용면적>194.43</전용면적> 사용
													<중개사소재지> </중개사소재지> 미사용
		<지번>110-15</지번> 사용	
		<지역코드>11110</지역코드> 사용 법정동시군구코드와 중복이긴 함.
		<층>6</층> 사용
													<해제사유발생일> </해제사유발생일> 미사용
													<해제여부> </해제여부> 미사용
	 * 
	 * 
	 * */
	private String aptNum;//일련번호-> 임의로 생성
	private String dealAmount;
	private String buildYear;
	private String roadName;//도로명
	private String dongName;//법정동
	private String gugunCode;//구군코드  앞 두자리 sidoCode 뒤 세자리 gugunCode
	private String dongCode;//동코드
	private String aptName;//동코드
	private String area;//동코드
	private String jibun;//지번
	private String lawdCd;//지역코드(사용은 안 할 수 있음)
	private String floor;//층
	private String lat;
	private String lng;
	
	
	
}
