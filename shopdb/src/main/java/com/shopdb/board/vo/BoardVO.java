package com.shopdb.board.vo;

import lombok.Data;

@Data
public class BoardVO {

	// board table 에 7개의 column이 있습니다.
	private Integer no;
	private String title;
	private String content;
	private String writer;
	private String writeDate;
	private Integer hit;
	private String pw;
	
	
	
}
