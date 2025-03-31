package com.gyshop.member.dao;

import java.util.ArrayList;
import java.util.List;

import com.gyshop.main.dao.DAO;
import com.gyshop.member.vo.GradeVO;
import com.gyshop.util.db.DB;

public class GradeDAO extends DAO {

	// 1. 회원등급 리스트
	public List<GradeVO> list() throws Exception {
		// 결과 저장 변수 선언 및 초기화
		List<GradeVO> list = null;
		
		try {
			// 1. 드라이버 확인
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL - LIST - 클래스 하단 상수
			System.out.println(LIST);
			// 4. 실행객체에 SQL + 데이터세팅(?: 0)
			pstmt = con.prepareStatement(LIST);
			// 5. 실행 및 결과 리턴
			rs = pstmt.executeQuery();
			// 6. 결과 저장
			if (rs != null) {
				while (rs.next()) {
					if (list == null) list = new ArrayList<GradeVO>();
					GradeVO vo = new GradeVO();
					vo.setGradeNo(rs.getInt("gradeNo"));
					vo.setGradeName(rs.getString("grageName"));
					
					list.add(vo);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. DB 닫기
			DB.close(con, pstmt, rs);
		}
		
		// 결과 리턴
		return list;
	} // end of list()
	
	// 3. 회원등급 등록
	public Integer write(GradeVO vo) throws Exception {
		// 결과 저장 변수 선언 및 초기화(null)
		Integer result = null;
		
		try {
			// 1. 드라이버확인
			// 2. DB 연결
			con = DB.getConnection();
			// 3. SQL 작성 - WRITE - 클래스하단 상수
			System.out.println(WRITE);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			
		}
		
		// 결과 리턴
		return result;
	}
	
	
	// SQL
	private static final String LIST = ""
			+ "select gradeNo, gradeName from grade order by gradeNo";
	// gradeNo : 1번부터 순서대로 정렬
	private static final String WRITE = ""
			+ "insert into grade (gradeNo, gradeName) values (?, ?)";
	/* grade 테이블에 값을 넣을때는 두가지 칼럼값 모두 들어가기때문에
	 * 칼럼명을 생략할 수 있다.
	 * (단, 생략했을때는 테이블의 열 구성 순서대로 값을 입력해야 합니다.)
	 */
	
}
