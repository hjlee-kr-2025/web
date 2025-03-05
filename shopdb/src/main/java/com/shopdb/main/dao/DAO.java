package com.shopdb.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// DB에 필요한 객체변수를 선언합니다.
public class DAO {
	// 연결 객체
	public Connection con = null;
	// 실행 객체
	public PreparedStatement pstmt = null;
	// 데이터저장 객체
	public ResultSet rs = null;
}
