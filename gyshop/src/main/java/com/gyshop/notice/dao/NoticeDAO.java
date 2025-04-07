package com.gyshop.notice.dao;

import java.util.ArrayList;
import java.util.List;

import com.gyshop.main.dao.DAO;
import com.gyshop.notice.vo.NoticeVO;
import com.gyshop.util.db.DB;

// DAO를 만들어 상속받아 사용하는 이유
// 1. 데이터베이스 사용에 필요한 공통변수의 선언이 편하도록
// 2. 서비스생성후 dao를 할당할 때의 보관되는 map자료형을 같이 사용하기 위해(다형성)
public class NoticeDAO extends DAO {
	
	// 1. 리스트
	/* 번호, 제목, 게시시작일, 게시종료일, 작성일, 조회수
	 * 
	 */
	public List<NoticeVO> list() throws Exception {
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
	
	
	// SQL
	private static final String LIST = ""
			+ "select no, title, "
			+ " date_format(startDate, '%Y-%m-%d') as startDate, "
			+ " date_format(endDate, '%Y-%m-%d') as endDate, "
			+ " date_format(writeDate, '%Y-%m-%d') as writeDate, hit "
			+ " from notice order by startDate desc";
	
	private static final String WRITE = ""
			+ "insert into notice "
			+ " (title, content, image, startDate, endDate) "
			+ " values (?, ?, ?, ?, ?)";
}






