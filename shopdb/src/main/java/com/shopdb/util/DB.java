package com.shopdb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DB {

	// DB에 관련된 4가지 상수
	private static final String DRIVER
		= "com.mysql.cj.jdbc.Driver";
	private static final String URL
		= "jdbc:mysql://localhost:3306/shopdb";
	private static final String ID
		= "java";
	private static final String PW
	 	= "java";
	
	// 한번만 실행하는 static 메서드
	static {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("<<드라이버가 없습니다>>");
			System.out.println("<<프로그램을 종료합니다>>");
			System.exit(1);
		}
	} // end of static
	
	// DB 연결을 위한 메서드
	public static final Connection getConnection() throws Exception {
		return DriverManager.getConnection(URL, ID, PW);
	}
	
	// DB를 닫는 메서드
	// 2개를 닫는 메서드 - insert, update, delete 사용시
	public static final void close(Connection con,
		PreparedStatement pstmt) throws Exception {
		if (con != null) con.close();
		if (pstmt != null) pstmt.close();
	}
	
	// 3개를 닫는 메서드 - select 사용시
	public static final void close(Connection con,
		PreparedStatement pstmt, ResultSet rs) throws Exception {
		close(con, pstmt);
		if (rs != null) rs.close();
	}
	
}
