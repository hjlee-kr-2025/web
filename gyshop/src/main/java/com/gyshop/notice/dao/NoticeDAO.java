package com.gyshop.notice.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gyshop.main.dao.DAO;
import com.gyshop.notice.vo.NoticeVO;
import com.gyshop.util.db.DB;
import com.gyshop.util.page.PageObject;

// DAO를 만들어 상속받아 사용하는 이유
// 1. 데이터베이스 사용에 필요한 공통변수의 선언이 편하도록
// 2. 서비스생성후 dao를 할당할 때의 보관되는 map자료형을 같이 사용하기 위해(다형성)
public class NoticeDAO extends DAO {
	
	// 1-1. 리스트 데이터 전체 수
	public Long getTotalRow(PageObject pageObject) throws Exception {
		// 결과저장변수 선언 및 초기화
		// 데이터가 없다는 것은 0과 동일하다.
		Long totalRow = 0L;
		System.out.println("NoticeDAO.getTotalRow() -----");
		
		try {
			// 1. 드라이버확인
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL - GETTOTALROW
			System.out.println(GETTOTALROW);
			// 4. 실행객체에 SQL세팅 + 데이터세팅(x)
			pstmt = con.prepareStatement(GETTOTALROW);
			// 5. 실행 및 결과리턴 (select 문은 ResultSet 자료형에 결과를 담습니다)
			rs = pstmt.executeQuery();
			// 6. 결과저장 (ResultSet 자료형에 담긴 데이터를
			// 우리가 사용할 데이터 자료형으로 옮깁니다.)
			if (rs != null && rs.next()) {
				totalRow = rs.getLong(1);// 첫번째 나오는 데이터
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
	} // end of getTotalRow(PageObject pageObject)
	
	
	// 1. 리스트
	/* 번호, 제목, 게시시작일, 게시종료일, 작성일, 조회수
	 * 
	 */
	public List<NoticeVO> list(PageObject pageObject) throws Exception {
		// 결과저장변수 선언 및 초기화
		List<NoticeVO> list = null;
		
		try {
			// 1. 드라이버확인
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL 작성 - LIST - 클래스 하단 상수
			// 조건이 들어가면서 쿼리를 작성하는 함수를 사용했습니다.
			String sql = getList(pageObject);
			System.out.println(sql);
			// 4. 실행객체에 SQL + 데이터 세팅 (?: 2개, startRow, EndRow)
			pstmt = con.prepareStatement(sql);
			int idx = 0;
			idx = setSearchData(pageObject, pstmt, idx);
			pstmt.setLong(++idx, pageObject.getStartRow());
			pstmt.setLong(++idx, pageObject.getEndRow());
			// 5. 실행 및 결과리턴
			rs = pstmt.executeQuery();
			// 6. 결과저장
			if (rs != null) {
				while(rs.next()) {
					if (list == null) list = new ArrayList<NoticeVO>();
					
					NoticeVO vo = new NoticeVO();
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setStartDate(rs.getString("startDate"));
					vo.setEndDate(rs.getString("endDate"));
					vo.setWriteDate(rs.getString("writeDate"));
					vo.setHit(rs.getLong("hit"));
					
					list.add(vo);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7.DB닫기
			DB.close(con, pstmt, rs);
		}
		
		// 결과 리턴
		return list;
	} // end of list()
	
	// 2-1. 조회수 증가
	public Integer increase(Long no) throws Exception {
		// 결과저장변수 선언 및 초기화
		Integer result = null;
		
		try {
			// 1. 드라이버확인
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL 작성 - INCREASE
			System.out.println(INCREASE);
			// 4. 실행객체에 SQL + 데이터세팅(?: 1개, no)
			pstmt = con.prepareStatement(INCREASE);
			pstmt.setLong(1, no);
			// 5. 실행 및 결과 리턴
			result = pstmt.executeUpdate();
			// 6. 체크안함
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. DB닫기
			DB.close(con, pstmt);
		}
		// 결과 리턴
		return result;
	}
	
	// 2-2. 공지사항 상세보기
	public NoticeVO view(Long no) throws Exception {
		// 결과저장변수 선언 및 초기화(null)
		NoticeVO vo = null;
		
		try {
			// 1. 드라이버확인
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL쿼리 - VIEW
			System.out.println(VIEW);
			// 4. 실행객체: SQL쿼리 + 데이터 세팅 (?: 1개, no)
			pstmt = con.prepareStatement(VIEW);
			pstmt.setLong(1, no);
			// 5. 실행 및 결과리턴
			rs = pstmt.executeQuery();
			// 6. 결과 저장
			if (rs != null && rs.next()) {
				vo = new NoticeVO();
				vo.setNo(rs.getLong("no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setImage(rs.getString("image"));
				vo.setStartDate(rs.getString("startDate"));
				vo.setEndDate(rs.getString("endDate"));
				vo.setWriteDate(rs.getString("writeDate"));
				vo.setHit(rs.getLong("hit"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. DB닫기
			DB.close(con, pstmt, rs);
		}
		
		// 결과 리턴
		return vo;
	}
	
	
	// 3. 공지사항 글쓰기
	public Integer write(NoticeVO vo) throws Exception {
		// 결과 저장 변수 선언 및 초기화
		Integer result = null;
		
		try {
			// 1. 드라이버확인
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL 작성 - WRITE
			System.out.println(WRITE);
			// 4. 실행객체(pstmt)에 SQL + 데이터세팅(?: 5개)
			pstmt = con.prepareStatement(WRITE);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getImage());
			pstmt.setString(4, vo.getStartDate());
			pstmt.setString(5, vo.getEndDate());
			// 5. 실행 및 결과 리턴
			result = pstmt.executeUpdate();
			// 6. 결과확인은 컨트롤러에서 체크
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. DB닫기
			DB.close(con, pstmt);
		}
		
		// 결과 리턴
		return result;
	} // end of write(NoticeVO vo)
	
	// 4. 공지사항 글 수정
	public Integer update(NoticeVO vo) throws Exception {
		// 결과저장변수 선언 및 초기화
		Integer result = null;
		
		try {
			// 1. 드라이버확인
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL - UPDATE
			System.out.println(UPDATE);
			// 4. 실행객체에 SQL + 데이터 세팅(?: 6개)
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getImage());
			pstmt.setString(4, vo.getStartDate());
			pstmt.setString(5, vo.getEndDate());
			pstmt.setLong(6, vo.getNo());
			// 5. 실행 및 결과 리턴
			result = pstmt.executeUpdate();
			// 6. 결과확인은 controller에서
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. DB닫기
			DB.close(con, pstmt);
		}
		
		// 결과 리턴
		return result;
	} // end of update(NoticeVO vo)
	
	// 5. 공지사항 글삭제
	public Integer delete(Long no) throws Exception {
		// 결과저장변수 선언 및 초기화
		Integer result = null;
		
		try {
			// 1. 드라이버확인
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL - DELETE
			System.out.println(DELETE);
			// 4. 실행객체에 SQL + 데이터 세팅(?: 1개, no)
			pstmt = con.prepareStatement(DELETE);
			pstmt.setLong(1, no);
			// 5. 실행 및 결과리턴
			result = pstmt.executeUpdate();
			// 6. 결과는 controller에서 처리합니다.
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. DB닫기
			DB.close(con, pstmt);
		}
		
		// 결과 리턴
		return result;
	} // end of delete(Long no)
	
	// 실행객체에 검색데이터 세팅하는 함수
	private int setSearchData(PageObject pageObject,
		PreparedStatement pstmt, int idx) throws SQLException {
		// 어떤항목에서 검색할 것인가?
		String key = pageObject.getKey();
		// 무엇을 검색할 것인가?
		String word = pageObject.getWord();
			
		if (word != null && !word.equals("")) {
			if (key.indexOf("t")>=0) pstmt.setString(++idx, "%"+word+"%");
			if (key.indexOf("c")>=0) pstmt.setString(++idx, "%"+word+"%");
		}
		return idx;
	}
	
	// 검색관련 쿼리를 작성하는 함수
	private String getSearch(PageObject pageObject) {
		String sql = "";
		
		// 어떤항목에서 검색할 것인가?
		String key = pageObject.getKey();
		// 무엇을 검색할 것인가?
		String word = pageObject.getWord();
		
		if (word != null && !word.equals("")) {
			// 2가지 조건 (제목, 내용) or (false 값으로 세팅후 여러조건을 붙인다)
			// false는 or 연산자 사용시 다른 항목에 영향을 주지 않습니다.
			sql += " where 1=0 ";
			if (key.indexOf("t")>=0) sql += " or title like ? ";
			if (key.indexOf("c")>=0) sql += " or content like ? ";
		}
		
		return sql;
	}
	
	// LIST 쿼리를 작성하는 함수
	private String getList(PageObject pageObject) {
		String sql = LIST1;
		// 검색에 관련된 쿼리는 from 과 order 사이에 작성합니다.
		sql += getSearch(pageObject);
		
		// 정렬관련 쿼리를 구성합니다.
		if (pageObject.getOrderStyle() == 2) {
			// 2: 게시종료일로 정렬
			sql += " order by endDate desc ";
		}
		else if (pageObject.getOrderStyle() == 3) {
			// 3: 조회수로 정렬
			sql += " order by hit desc ";
		}
		else {
			// orderStyle=1 : 게시시작일로 정렬
			sql += " order by startDate desc ";
		}
	
		sql += LIST2;
		return sql;
	}
	
	// SQL
	private static final String GETTOTALROW = ""
			+ "select count(*) from notice";
	
	private static final String LIST1 = ""
			+ "select "
			+ " no, title, startDate, endDate, writeDate, hit " 
			+ " from "
			+ " (select " 
			+ " @rownum := @rownum + 1 as rnum, "
			+ " no, title, "
			+ " date_format(startDate, '%Y-%m-%d') as startDate, " 
			+ " date_format(endDate, '%Y-%m-%d') as endDate, "
			+ " date_format(writeDate, '%Y-%m-%d') as writeDate, hit "
			+ " from notice, (select @rownum := 0) as rn ";

	private static final String LIST2 = ""
		    + " ) as pageNotice "
		    + " where rnum >= ? and rnum <= ? ";

	private static final String INCREASE = ""
			+ "update notice set hit = hit + 1 where no = ?";
	
	private static final String VIEW = ""
			+ "select no, title, content, image, "
			+ " date_format(startDate, '%Y-%m-%d') as startDate, "
			+ " date_format(endDate, '%Y-%m-%d') as endDate, "
			+ " date_format(writeDate, '%Y-%m-%d') as writeDate, hit "
			+ " from notice "
			+ " where no = ?";
	
	private static final String WRITE = ""
			+ "insert into notice "
			+ " (title, content, image, startDate, endDate) "
			+ " values (?, ?, ?, ?, ?)";
	
	private static final String UPDATE = ""
			+ "update notice "
			+ " set title = ?, content = ?, image = ?, "
			+ " startDate = ?, endDate = ? "
			+ " where no = ?";
	
	private static final String DELETE = ""
			+ "delete from notice where no = ?";
}






