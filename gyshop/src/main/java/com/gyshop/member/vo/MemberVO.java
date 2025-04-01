package com.gyshop.member.vo;

import lombok.Data;

@Data
public class MemberVO {

	private String id;	// O
	private String pw;	// O
	private String name;	// O
	private String gender;	// O
	private String birth;	// O
	private String tel;
	private String zipcode;
	private String addr1;
	private String addr2;
	private String email;	// O
	private String regDate;	// 자동(default)
	private String conDate;	// 자동(default)
	private String photo;	
	private String status;	// 자동(default)
	private Integer gradeNo;	// 자동(default
	private String gradeName;	// member table에 사용하는 값은 아닙니다.
}
