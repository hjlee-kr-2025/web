package com.gyshop.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// 1. Mysql jdcb 드라이버 확인
// 2. DB 연결
// 3. DB 해제(닫기)
public class DB {
	
	// 드라이버 경로를 위한 상수
	private static final String DRIVER = ""
			+ "com.mysql.cj.jdbc.Driver";
	// ==> mysql 을 java에서 사용하기 위한 드라이버를 알려줍니다.
	
	// DB접속에 관련된 상수 3가지 (URL, ID, PW)
	private static final String URL = ""
			+ "jdbc:mysql://localhost:3306/gyshop";
	private static final String ID = "java";
	private static final String PW = "java";

	// DB 클래스가 처음 로딩될때 실행할 부분
	// ==> static 메서드 ==> 한 번만 실행됨
	static {
		try {
			// 1. Class.forName(DRIVER);
			Class.forName(DRIVER);
			// 2. Class.forName(DRIVER) 위로 마우스 surrund try~catch 클릭
			System.out.println("------  1. 드라이버 확인(MySQL JDBC)  ------");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 3. 프로그램종료
			System.out.println("<<MySQL 드라이버가 없으므로 프로그램을 종료합니다.>>");
			System.exit(1);
		}
	}
	// => static 메서드에서 JDBC 드라이버를 확인하는 이유는
	// 라이브러리를 포함했는지를 확인하는 내용입니다.
	
	// DB 연결을 위한 메서드 구현
	public static final Connection getConnection() throws Exception {
		return DriverManager.getConnection(URL, ID, PW);
		// 연결이 되면 연결된 상태를 Connection 인터페이스 타입으로 리턴됩니다.
		// DAO에서 con 변수가 받을 예정입니다.
	}
	
	// DB 닫기 - 사용한 객체 닫는다.
	// insert, update, delete : executeUpdate() 메서드 사용시
	public static final void close(Connection con, PreparedStatement pstmt)
		throws Exception {
		if (con != null) con.close();
		if (pstmt != null) pstmt.close();
	}
	
	// select : executeQuery() 메서드 사용시 (ResultSet사용)
	public static final void close(Connection con, PreparedStatement pstmt,
			ResultSet rs)
			throws Exception {
		close(con, pstmt);
		if (rs != null) rs.close();
	}
}








