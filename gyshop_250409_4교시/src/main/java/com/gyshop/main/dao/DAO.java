package com.gyshop.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/* DB 연결, 사용에 필요한 객체를 선언
 * 
 */
public abstract class DAO {
	// ==> class 앞에 abstract 를 적어서
	// 추상클래스로 만들었습니다.
	
	// 연결 객체
	public Connection con;
	// 실행 객체
	public PreparedStatement pstmt;
	// 데이터 저장 객체
	public ResultSet rs;
}



