package com.shopdb.board.dao;

import java.util.ArrayList;
import java.util.List;

import com.shopdb.board.vo.BoardVO;
import com.shopdb.main.dao.DAO;
import com.shopdb.util.DB;

// DAO (Data Access Object) - DB와 연동하는 프로그램부분

public class BoardDAO extends DAO {

	// 1. 리스트
	public List<BoardVO> list() throws Exception {
		// 결과를 저장할 변수 선언
		List<BoardVO> list = null;
		
		try {
			// 1. 드라이버 확인
			// DB 클래스 static 메서드에 구현 - 1번만 실행이 됩니다.
			// 2. DB 연결
			con = DB.getConnection();
			// DB 연결 확인 방법
			if (con != null) {
				System.out.println("DB 연결이 완료되었습니다.");
			}
			// 연결이 되지 않았을때 확인하는 방법
			// - windows 의 서비스에서 mysql 검색 실행중인지 확인
			// - MySQL Workbench 에서 접속이 되는지 확인
			// - 접속후 select @@version; 을 실행해서 version이 표시되는지 확인
			// - URL 이 맞게 작성되었는지 확인
			// - ID, PW 설정이 맞게 되었는지 확인
			
			// 3. SQL 작성 - class 하단 LIST 상수로 작성
			// 4. 실행객체에 SQL 세팅 & DB에 전달할 데이터 세팅
			pstmt = con.prepareStatement(LIST);
			// 5. 실행 및 리턴값 받기
			rs = pstmt.executeQuery();// select 에서의 메서드
			// 6. 리턴받은 데이터를 저장
			if (rs != null) {
				while (rs.next()) {
					if (list == null) list = new ArrayList<BoardVO>();
					// rs -> BoardVO vo
					BoardVO vo = new BoardVO();
					vo.setNo(rs.getInt("no"));
					vo.setTitle(rs.getString("title"));
					vo.setWriter(rs.getString("writer"));
					vo.setWriteDate(rs.getString("writeDate"));
					vo.setHit(rs.getInt("hit"));
					list.add(vo);
				} // end of while (rs.next())
			} // end of if (rs != null)
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. DB 닫기 - select 3개를 닫습니다.
			DB.close(con, pstmt, rs);
		}
		
		
		return list;
	} // end of list()
	
	// 2-1. 조회수 증가
	public Integer increase(Integer no) throws Exception {
		// 결과 담을 변수 선언
		Integer result = 0;
		
		try {
			// 1. 드라이버 확인
			// 2. DB 연결
			con = DB.getConnection();
			// 3. SQL --> INCREASE (class 하단 상수)
			// 4. 실행객체에 데이터세팅
			pstmt = con.prepareStatement(INCREASE);
			pstmt.setInt(1, no);// 순서, 데이터
			// 5. 실행 및 리턴
			result = pstmt.executeUpdate();// update, insert, delete
			// 6. 결과 확인
			// 리턴으로 미룸		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. DB 닫기 - 2개를 닫는 close사용
			// insert, update, delete
			DB.close(con, pstmt);
		}
		
		return result;
	}
	
	// 2-2 글보기
	public BoardVO view(Integer no) throws Exception {
		// 결과 담을 변수 선언
		BoardVO vo = null;
		
		try {
			// 1. 드라이버확인
			// 2. DB 연결
			con = DB.getConnection();
			// 3. SQL 작성 - VIEW (클래스하단 상수)
			// 4. 실행객체에 SQL + 데이터세팅
			pstmt = con.prepareStatement(VIEW);
			pstmt.setInt(1, no);
			// 5. 실행 및 결과리턴 - select ==> rs(ResultSet)
			rs = pstmt.executeQuery();
			// 6. 데이터 저장
			if (rs != null && rs.next()) {
				vo = new BoardVO();
				vo.setNo(rs.getInt("no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setWriteDate(rs.getString("writeDate"));
				vo.setHit(rs.getInt("hit"));
				vo.setPw(rs.getString("pw"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. DB 닫기 - select - 3개 close
			DB.close(con, pstmt, rs);
		}
		
		return vo;
	} // end of view
	
	// 3. 글쓰기
	public Integer write(BoardVO vo) throws Exception {
		// 결과 저장 변수 선언
		Integer result = 0;
		
		try {
			// 1. 드라이버확인 (mysql jdbc 드라이버)
			// 2. DB 연결
			con = DB.getConnection();
			// 3. SQL 작성 - WRITE (class 하단 상수) ?(4개)
			// 4. 실행객체에 데이터 세팅
			pstmt = con.prepareStatement(WRITE);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());
			pstmt.setString(4, vo.getPw());
			// 5. 실행 및 결과 리턴 - insert
			result = pstmt.executeUpdate();
			// 6. 결과확인은 리턴으로
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. DB닫기 - insert (2개)
			DB.close(con, pstmt);
		}
		
		return result;
	}
	
	// 4. 글수정
	public Integer update(BoardVO vo) throws Exception {
		// 결과 담을 변수 선언
		Integer result = 0;
		
		try {
			// 1. 드라이버확인
			// 2. DB 연결
			con = DB.getConnection();
			// 3. SQL작성 - UPDATE (class 하단 상수) : ?(5개)
			// 4. 실행객체 - SQL 및 데이터세팅
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());
			pstmt.setInt(4, vo.getNo());
			pstmt.setString(5, vo.getPw());
			// 5. 실행 및 결과 리턴
			result = pstmt.executeUpdate();
			// 6. 결과 확인 - return 후
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. DB 닫기 - 2개 (update)
			DB.close(con, pstmt);
		}
		
		return result;
	}
	
	// 5. 글삭제
	public Integer delete(BoardVO vo) throws Exception {
		// 결과 담을 변수 선언
		Integer result = 0;
		
		try {
			// 1. 드라이버 확인
			// 2. DB 연결
			con = DB.getConnection();
			// 3. SQL 작성 - DELETE (class 하단 상수)
			// 4. 실행 객체에 sql 및 데이터 세팅 ?:2개
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, vo.getNo());
			pstmt.setString(2, vo.getPw());
			// 5. 실행 및 결과 리턴 - delete
			result = pstmt.executeUpdate();
			// 6. 결과 확인 - 리턴후
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. DB 닫기 - delete (2개)
			DB.close(con, pstmt);
		}
		
		return result;
	}
	
	
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




