package com.gyshop.movie.vo;

import lombok.Data;

@Data
public class MovieVO {

	private Long no;
	private String movieCd;
	private String movieNm;
	private String movieNmEn;
	private String openDt;
	private String typeNm;
	private String prdtStatNm;
	private String repNationNm;
	private String repGenreNm;
	private String peopleNm;
	private int saveFlag;
}
