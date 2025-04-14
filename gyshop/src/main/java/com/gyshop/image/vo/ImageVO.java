package com.gyshop.image.vo;

import lombok.Data;

// 이미지게시판에서 사용할 데이터 객체
// 작성은 일반회원이상(로그인회원)
// List, View는 모든사람이 접근가능 
@Data
public class ImageVO {

	private Long no;
	private String title;
	private String content;
	private String fileName;
	private String id;
	private String writeDate;
	private Long hit;
	private String name;
}
