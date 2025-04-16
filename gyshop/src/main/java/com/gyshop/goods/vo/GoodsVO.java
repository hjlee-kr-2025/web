package com.gyshop.goods.vo;

import lombok.Data;

// 상품관리를 데이터를 저장하는 클래스
@Data
public class GoodsVO {

	private Long no;
	private String name;
	private String content;
	private String photo;
	private String subPhoto1;
	private String subPhoto2;
	private String subPhoto3;
	private String subPhoto4;
	private Integer price;
	private Integer delivery_cost;
	private String modelNo;
}
