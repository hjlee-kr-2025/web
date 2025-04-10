package com.gyshop.notice.dao;

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
			System.out.println(LIST);
			// 4. 실행객체에 SQL + 데이터 세팅 (?: 0개)
			pstmt = con.prepareStatement(LIST);
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
	
	// SQL
	private static final String LIST = ""
			+ "select "
			+ " no, title, startDate, endDate, writeDate, hit " 
			+ " from "
			+ " (select " 
			+ " @rownum := @rownum + 1 as rnum, "
			+ " no, title, "
			+ " date_format(startDate, '%Y-%m-%d') as startDate, " 
			+ " date_format(endDate, '%Y-%m-%d') as endDate, "
			+ " date_format(writeDate, '%Y-%m-%d') as writeDate, hit "
			+ " from notice, (select @rownum := 0) as rn "
		    + " order by startDate desc) as pageNotice "
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






