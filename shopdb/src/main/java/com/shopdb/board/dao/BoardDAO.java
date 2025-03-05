package com.shopdb.board.dao;

import com.shopdb.main.dao.DAO;

public class BoardDAO extends DAO {

	
	
	// 사용할 SQL 쿼리 세팅
	// 1. 리스트
	final String LIST = ""
			+ "select no, title, writer, "
			+ " date_format(writeDate, '%Y-%m-%d') as writeDate,"
			+ " hit "
			+ " from board order by no desc";
	// 2. 글보기
	// 2-1 조회수증가
	final String INCREASE = ""
			+ "update board set hit = hit + 1 "
			+ " where no=?";
	// 2-2 글 상세보기
	final String VIEW = ""
			+ "select no, title, content, writer, "
			+ " date_format(writeDate, '%Y-%m-%d') as writeDate,"
			+ " hit, pw "
			+ " from board "
			+ " where no=?";
	// 3. 글쓰기
	final String WRITE = ""
			+ "insert into board (title, content, writer, pw) "
			+ " values (?,?,?,?)";
	// 4. 글수정
	final String UPDATE = ""
			+ "update board set title=?, content=?, writer=? "
			+ " where no=? and pw=?";
	// 5. 글삭제
	final String DELETE = ""
			+ "delete from board where no=? and pw=?";
	
} // end of class




