package com.gyshop.board.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gyshop.board.vo.BoardVO;
import com.gyshop.main.dao.DAO;
import com.gyshop.util.db.DB;
import com.gyshop.util.page.PageObject;

public class BoardDAO extends DAO {

	// DAO 클래스에는 DB사용에 필요한 3가지 객체가 선언되어있습니다
	
	// 1-1 일반게시판 전체 데이터 개수
	public Long getTotalRow(PageObject pageObject) throws Exception {
		// 결과 저장할 변수 선언
		Long totalRow = null;
		System.out.println("BoardDAO.getTotalRow() -----");
		
		try {
			// 1. 드라이버 확인
			// 2. DB 연결
			con = DB.getConnection();
			// 3. SQL 쿼리 작성 - GETTOTALROW - 클래스 하단 상수
			String SQL = GETTOTALROW + getSearch(pageObject);
			System.out.println(SQL);
			// 4. 실행객체에 SQL + 데이터세팅(? 없음)
			pstmt = con.prepareStatement(SQL);
			int idx = 0;
			idx = setSearchData(pageObject, pstmt, idx);
			// 5. 실행 + 결과 리턴 (select문은 ReseultSet에 결과를 담는다)
			rs = pstmt.executeQuery();
			// 6. 결과 저장
			if (rs != null && rs.next()) {
				totalRow = rs.getLong(1);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. DB닫기
			DB.close(con, pstmt, rs);
		}
		
		// 결과 리턴
		return totalRow;
	}	// end of getTotalRow(PageObject pageObject)
	
	// 1-2. 일반게시판 리스트
	public List<BoardVO> list(PageObject pageObject) throws Exception {
		// 1) 결과 저장 변수 선언 - 리턴타입과 같은 자료형 선언, 초기값 null
		List<BoardVO> list = null;
		System.out.println("** BoardDAO.list() -----");
		
		// 3) try ~ catch ~ finally 구성
		try {
			// 5) DB 상호작용구현 - try에 2~6단계, finally : 마지막7단계
			// 1. 드라이버확인 - DB클래스 static메서드 - 프로그램시작시 한번으로 끝
			// 2. DB 연결
			con = DB.getConnection();
			System.out.println("----- 2. DB연결완료 -----");
			// 3. SQL 작성 - 클래스하단 상수와 메서드로 구현
			System.out.println("3. SQL: " + getList(pageObject));
			// 4. 실행객체에 SQL + 데이터세팅(?: 0 -> 2)
			pstmt = con.prepareStatement(getList(pageObject)); // SQL 세팅
			// 데이터세팅은 없습니다. (?가 없기 때문에)
			// 페이지 처리후에 세팅하는 값이 2개 들어갑니다.
			int idx = 0;
			idx = setSearchData(pageObject, pstmt, idx);
			pstmt.setLong(++idx, pageObject.getStartRow());
			pstmt.setLong(++idx, pageObject.getEndRow());
			System.out.println("4. 실행객체 세팅 완료 ---");
			// 5. 실행 + 결과리턴
			rs = pstmt.executeQuery();
			System.out.println("5. 실행 완료 ---");
			// 6. 결과를 담기(저장)
			if (rs != null) {
				// 리스트는 while문 사용 , 한개의 데이터는 if를 사용
				while (rs.next()) {
					if (list == null) list = new ArrayList<BoardVO>();
					
					// ResultSet에 담긴 데이터를 하나씩 꺼내서
					// BoardVO에 담는다.
					BoardVO vo = new BoardVO();
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setWriter(rs.getString("writer"));
					vo.setWriteDate(rs.getString("writeDate"));
					vo.setHit(rs.getLong("hit"));
					
					// BoardVO에 담은 데이터를 list에 추가
					list.add(vo);
					
				}
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			// 4) 예외발생 내용을 보기위한 메서드
			e.printStackTrace();
		} finally {
			
		}
		
		// 2) 결과 리턴 - 선언한 결과저장변수를 리턴
		return list;
	}	// end of list()
	
	
	// 2. VIEW
	// View 서비스에는 두가지가 진행되어야 합니다.
	// 1. 조회수 증가, 2. 글번호로 작성된 데이터를 가져옵니다.
	// 2-1 조회수 증가 : update (hit 를 1증가한 값으로 수정)
	public Integer increase(Long no) throws Exception {
		// 결과를 저장할 변수 선언 null값으로 초기화
		Integer result = null;
		
		System.out.println("BoardDAO.increase() -----");
		
		try {
			// 1. 드라이버확인 - Init클래스에서 확인 끝
			// 2. DB연결
			con = DB.getConnection();
			System.out.println("2. DB 연결확인");
			// 3. SQL작성 - INCREASE - 클래스 하단 상수
			System.out.println(INCREASE);
			// 4. 실행객체(pstmt)에 SQL, 데이터 세팅(? : 1개)
			pstmt = con.prepareStatement(INCREASE);
			pstmt.setLong(1, no);
			// 5. 실행 그리고 결과 리턴
			result = pstmt.executeUpdate();
			// 6. 결과확인은 리턴 후 처리(controller에서)
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. DB 닫기
			DB.close(con, pstmt);
		}
		
		// 결과 리턴합니다.
		return result;
	}	// end of increase(Long no)
	
	// 2-2 글보기 VIEW
	public BoardVO view(Long no) throws Exception {
		// 결과 저장 변수 선언 및 초기화
		BoardVO vo = null;
		// 초기값을 null 로 선언한 이유는 db에서 데이터가 넘어오지
		// 못했을 때 확인하는 용도로 사용합니다.
		System.out.println("BoardDAO.view() -----");
		
		try {
			// 1. 드라이버확인
			// 2. DB연결(con)
			con = DB.getConnection();
			System.out.println("2. DB연결 확인완료");
			// 3. SQL 작성 - VIEW - 클래스하단 상수
			System.out.println(VIEW);
			// 4. 실행객체(pstmt)에 SQL, 데이터세팅 (?: 1개)
			pstmt = con.prepareStatement(VIEW);
			pstmt.setLong(1, no);
			System.out.println("4. 실행객체 준비완료");
			// 5. 실행 및 결과 리턴
			rs = pstmt.executeQuery();
			System.out.println("5. 실행완료");
			// 6. 결과 저장
			if (rs != null && rs.next()) {
				vo = new BoardVO();
				vo.setNo(rs.getLong("no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setWriteDate(rs.getString("writeDate"));
				vo.setHit(rs.getLong("hit"));
			}
			System.out.println("6. 결과담기 완료");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. DB닫기
			DB.close(con, pstmt, rs);
			System.out.println("7. DB닫기 완료");
		}
		
		// 결과 리턴
		return vo;
	}	// end of view(Long no)
	
	// 3. 일반게시판 글쓰기
	// 제목, 내용, 작성자, pw
	public Integer write(BoardVO vo) throws Exception {
		// 결과 저장 변수 선언 및 초기화
		Integer result = null;
		System.out.println("BoardDAO.write() -----");
		
		try {
			// 1. 드라이버확인
			// 2. DB연결
			con = DB.getConnection();
			System.out.println("2. DB연결 완료");
			// 3. SQL작성 - WRITE - 클래스 하단 상수
			System.out.println(WRITE);
			// 4. 실행객체(pstmt)에 SQL, 데이터세팅 (?: 4개)
			pstmt = con.prepareStatement(WRITE);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());
			pstmt.setString(4, vo.getPw());
			System.out.println("4. 실행객체 세팅 완료");
			// 5. 실행 및 결과 리턴
			result = pstmt.executeUpdate();
			System.out.println("5. DB실행 완료");
			// 6. 결과 확인 - 리턴후에 실행 (controller)
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. DB닫기
			DB.close(con, pstmt);
			System.out.println("7. DB닫기 완료");
		}
		
		
		// 결과 리턴
		return result;
	}	// end of write(BoardVO vo)
	
	// 4. 일반게시판 글수정 (글번호, 비밀번호) : (제목, 내용, 이름)
	public Integer update(BoardVO vo) throws Exception {
		// 결과 저장 변수 선언 및 초기화(null)
		Integer result = null;
		System.out.println("BoardDAO.update() -----");
		
		try {
			// 1. 드라이버확인
			// 2. DB연결
			con = DB.getConnection();
			// 3. 퀴리작성 - UPDATE - 클래스하단 상수
			System.out.println(UPDATE);
			// 4. 실행객체(pstmt) - SQL 구성, 데이터 세팅(? : 5개)
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());
			pstmt.setLong(4, vo.getNo());
			pstmt.setString(5, vo.getPw());
			System.out.println("4.실행객체 세팅 완료 ===");
			// 5. 실행 및 결과 리턴
			result = pstmt.executeUpdate();
			System.out.println("5.실행완료 ===");
			// 6. 결과 확인 - controller에서
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. DB닫기
			DB.close(con, pstmt);
		}
		
		// 결과 리턴
		return result;
	}	// end of update(BoardVO vo)
	
	// 5. 일반게시판 글삭제 (no, pw)
	public Integer delete(BoardVO vo) throws Exception {
		// 결과 저장 변수 선언 및 null로 초기화
		Integer result = null;
		System.out.println("BoardDAO.delete() -----");
		
		try {
			// 1. 드라이버확인
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL - DELETE - 클래스하단 상수
			System.out.println(DELETE);
			// 4. 실행객체(pstmt)에 SQL과 데이터를 세팅(?: 2개)
			pstmt = con.prepareStatement(DELETE);
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getPw());
			// 5. 실행 및 결과 리턴
			result = pstmt.executeUpdate();
			// 6. 결과확인은 - controller
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. DB닫기
			DB.close(con, pstmt);
		}
		
		// 결과 리턴
		return result;
	}	// end of delete(BoardVO vo)
	
	
	// 검색관련 쿼리를 정리하는 함수
	private String getSearch(PageObject pageObject) {
		String sql = "";
		
		String key = pageObject.getKey();
		String word = pageObject.getWord();
		
		// 검색어가 존재하는 여부에 따라 구성을 합니다.
		if (word != null && !word.equals("")) {
			// 세가지 조건이 or 로 묶입니다.
			// 여러개의 조건을 or로 묶을때 시작을 where 1=0 으로 진행합니다.
			// or 일때는 거짓값을 만들어서 시작
			// and 일때는 참값을 만들어서 시작
			sql += " where 1=0 ";
			if (key.indexOf("t") >= 0) sql += " or title like ? ";
			if (key.indexOf("c") >= 0) sql += " or content like ? ";
			if (key.indexOf("w") >= 0) sql += " or writer like ? ";
		}
		
		
		return sql;
	}
	
	
	// LIST쿼리를 작성하는 함수
	private String getList(PageObject pageObject) {
		String sql = LIST1;
		// 검색관련 쿼리구성
		sql += getSearch(pageObject);
		// 정렬관련 쿼리구성
		if (pageObject.getOrderStyle() == 2L) {
			sql += " order by no ";
		}
		else if (pageObject.getOrderStyle() == 3L) {
			sql += " order by hit desc, no desc ";
		}
		else {
			sql += " order by no desc ";
		}
		sql += LIST2;
		return sql;
	}
	
	// pstmt(실행객체) 에 검색 데이터 세팅하는 함수
	private int setSearchData(PageObject pageObject,
		PreparedStatement pstmt, int idx) throws SQLException {
		String key = pageObject.getKey();
		String word = pageObject.getWord();
		System.out.println("key = " + key + ", word = " + word);
		if (word != null && !word.equals("")) {
			if (key.indexOf("t") >= 0) pstmt.setString(++idx, "%"+word+"%");
			if (key.indexOf("c") >= 0) pstmt.setString(++idx, "%"+word+"%");
			if (key.indexOf("w") >= 0) pstmt.setString(++idx, "%"+word+"%");
		}
		
		return idx;
	}
	
	
	
	
	
	// SQL ==================
	/* LIST 기본쿼리
	  private final String LIST = ""
			+ "select no, title, writer, "
			+ " date_format(writeDate, '%Y-%m-%d') as writeDate, hit "
			+ " from board order by no desc";*/
			// order by no desc -> 최근글 부터 앞에서 보여줌
			// order by no -> 오래된 글부터 앞에서 보여줌
			// order by hit desc -> 조회수 많은 순으로
			// order by hit -> 조회수 적은 순으로
	// 페이지 처리를 위한 쿼리
	private final String GETTOTALROW = ""
		+ "select count(*) from board";
	
	private final String LIST1 = ""
		+ "select "
		+ "	no, title, writer, writeDate, hit "
		+ " from "
		+ " (select "
		+ " @rownum := @rownum + 1 as rnum, "
		+ "	no, title, writer, "
		+ " date_format(writeDate, '%Y-%m-%d') as writeDate, hit " 
		+ " from board, (select @rownum := 0) as rn ";
	
	private final String LIST2 = ""
		+ " ) as pageBoard " 
		+ " where rnum >= ? and rnum <= ?";
	// ===> 일반게시판의 리스트 전체를 rnum으로 1번부터 순서대로 번호를 매긴
	// 가상의 테이블을 만들고
	// rnum 1부터 5까지 5개의 데이터를 가져오는 쿼리입니다.
	// (최신글 첫번째 부터 다섯번째까지 데이터)
	
	
	private final String INCREASE = ""
			+ "update board set hit = hit + 1 where no = ?";
	
	private final String VIEW = ""
			+ "select no, title, content, writer, "
			+ " date_format(writeDate, '%Y-%m-%d') as writeDate, hit "
			+ " from board where no = ?";
	
	private final String WRITE = ""
			+ "insert into board (title, content, writer, pw) "
			+ " values (?, ?, ?, ?)";
	
	private final String UPDATE = ""
			+ "update board set title = ?, content = ?, writer = ? "
			+ " where no = ? and pw = ?";
	
	private final String DELETE = ""
			+ "delete from board where no = ? and pw = ?";
	
}	// end of class




