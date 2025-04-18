package com.gyshop.cart.vo;

import lombok.Data;

// 장바구니에서 사용할 객체
@Data
public class CartVO {

	// cart 테이블에 있는 열이름
	private Long no;
	private String id;
	private Long gno;
	private Integer count;
	
	// 장바구니 모듈에서 사용할 데이터
	private String name;
	private String photo;
	private Integer price;
	private Integer delivery_cost;
}
