package com.gyshop.member.dao;

import com.gyshop.main.dao.DAO;
import com.gyshop.member.vo.LoginVO;
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
	
	// 3-1. id 중복체크
	public String checkId(String id) throws Exception {
		// 결과 저장 변수 선언 및 초기화(null)
		String result = null;
		
		try {
			// 1. 드라이버 확인
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL쿼리작성 - CHECKID - 클래스 하단 상수
			System.out.println(CHECKID);
			// 4. 실행객체에 SQL + 데이터세팅(?: 1개)
			pstmt = con.prepareStatement(CHECKID);
			pstmt.setString(1, id);
			// 5. 실행 및 결과 리턴
			rs = pstmt.executeQuery();
			// 6. 결과 담기
			if (rs != null && rs.next()) {
				result = rs.getString("id");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. DB닫기
			DB.close(con, pstmt, rs);
		}
		
		// 결과 리턴
		return result;
	} // end of checkId(String id)
	
	// 6. 로그인 처리
	public LoginVO login(LoginVO vo) throws Exception {
		// 결과 저장 변수 선언 및 초기화
		LoginVO login = null;
		
		try {
			// 1. 드라이버확인
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL작성 - LOGIN - 클래스하단 상수
			System.out.println(LOGIN);
			// 4. 실행객체 - SQL과 데이터세팅 (?: 2개)
			System.out.println("id: " + vo.getId() +", pw: "+ vo.getPw());
			pstmt = con.prepareStatement(LOGIN);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			System.out.println("4. 실행객체 세팅 완료---");
			// 5. 실행 및 결과 리턴
			rs = pstmt.executeQuery();
			System.out.println("5. 실행종료 ----");
			// 6. 결과 저장
			if (rs != null && rs.next()) {
				login = new LoginVO();
				login.setId(rs.getString("id"));
				login.setPw(rs.getString("pw"));
				login.setName(rs.getString("name"));
				login.setGradeNo(rs.getInt("gradeNo"));
				login.setGradeName(rs.getString("gradeName"));
				login.setPhoto(rs.getString("photo"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			
		}
		
		// 결과 리턴
		return login;
	} // end of login(LoginVO vo)
	
	
	// SQL
	private static final String WRITE = ""
			+ "insert into member "
			+ " (id, pw, name, gender, birth, "
			+ " tel, zipcode, addr1, addr2, "
			+ " email, photo) values "
			+ " (?, ?, ?, ?, ?, ?, ?, ?, "
			+ " ?, ?, ?)";
	// insert 시 자동으로 값이 들어가는 것을 제외한 모든 열을 세팅합니다.
	
	private static final String CHECKID = ""
			+ "select id from member where id = ?";
	
	private static final String LOGIN = ""
			+ "select m.id, m.pw, m.name, m.gradeNo, "
			+ " g.gradeName, m.photo "
			+ " from member m, grade g "//as는 생략가능
			+ " where " // 조건을 물어봅니다.
			+ " (id = ? and pw = ? and status='정상') "//일반조건
			+ " and (m.gradeNo = g.gradeNo)";//조인조건(테이블결합)
	
}







