package com.gyshop.member.vo;

import lombok.Data;

/* 로그인했을때 필요한 정보만 최소한으로 저장합니다.
 * 로그인정보는 페이지가 이동될때마다 가지고 있어야 합니다.
 * Application
 * Session -> 로그인VO를 생성하고 정보를 받아오면 이곳에 저장합니다.
 * Request
 * Page
 */
@Data
public class LoginVO {

	private String id;
	private String pw;
	private String name;
	private String photo;
	private Integer gradeNo;
	private String gradeName;
}
