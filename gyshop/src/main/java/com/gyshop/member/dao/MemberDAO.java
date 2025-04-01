package com.gyshop.member.dao;

import com.gyshop.main.dao.DAO;
import com.gyshop.member.vo.MemberVO;
import com.gyshop.util.db.DB;

public class MemberDAO extends DAO {

	// 3. 회원가입
	public Integer write(MemberVO vo) throws Exception {
		// 결과 저장 변수 선언 및 초기화
		Integer result = null;
		
		try {
			// 1. 드라이버확인
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL쿼리작성 - WRITE : 클래스하단 상수
			System.out.println(WRITE);
			// 4. 실행객체(pstmt)에 SQL + 데이터 세팅(?: 11개)
			pstmt = con.prepareStatement(WRITE);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getGender());
			pstmt.setString(5, vo.getBirth());
			pstmt.setString(6, vo.getTel());
			pstmt.setString(7, vo.getZipcode());
			pstmt.setString(8, vo.getAddr1());
			pstmt.setString(9, vo.getAddr2());
			pstmt.setString(10, vo.getEmail());
			pstmt.setString(11, vo.getPhoto());
			// 5. 실행 및 결과 리턴
			result = pstmt.executeUpdate();
			// 6. 실행결과 - Controller에서 체크!!
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. DB닫기
			DB.close(con, pstmt);
		}
		
		// 결과 리턴
		return result;
	} // end of write(MemberVO vo)
	
	
	// SQL
	private static final String WRITE = ""
			+ "insert into member "
			+ " (id, pw, name, gender, birth, "
			+ " tel, zipcode, addr1, addr2, "
			+ " email, photo) values "
			+ " (?, ?, ?, ?, ?, ?, ?, ?, "
			+ " ?, ?, ?)";
	// insert 시 자동으로 값이 들어가는 것을 제외한 모든 열을 세팅합니다.
}







