package com.shopdb.board.vo;

import lombok.Data;


// VO (Value Object) - 데이터베이스의 한행을 의미 하거나 Join 한 결과를 담는 역할
// DTO (Data Transfer Object)
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
