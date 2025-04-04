package com.gyshop.member.dao;

import java.util.ArrayList;
import java.util.List;

import com.gyshop.main.dao.DAO;
import com.gyshop.member.vo.LoginVO;
import com.gyshop.member.vo.MemberVO;
import com.gyshop.util.db.DB;

public class MemberDAO extends DAO {

	// 1. 회원리스트(관리자만 볼 수 있습니다.)
	public List<MemberVO> list() throws Exception {
		// 결과저장변수 선언 및 초기화
		List<MemberVO> list = null;
		
		try {
			// 1. 드라이버확인: JDBC - MySQL사용을 위한
			// 2. DB연결(Connection con)
			con = DB.getConnection();
			// 개발자가 연결되었는지 확인하는 메시지
			if (con != null) System.out.println("2. DB가 연결되었습니다.");
			// 3. SQL 작성 - LIST - 클래스 하단 상수
			System.out.println(LIST);
			// 4. 실행객체(pstmt) SQL + 데이터세팅(?: 0개)
			pstmt = con.prepareStatement(LIST);
			// 5. 실행 및 결과 리턴
			rs = pstmt.executeQuery();
			// 6. 결과 저장
			if (rs != null) {
				while(rs.next()) {
					if (list == null) list = new ArrayList<MemberVO>();
					
					MemberVO vo = new MemberVO();
					vo.setId(rs.getString("id"));
					vo.setName(rs.getString("name"));
					vo.setBirth(rs.getString("birth"));
					vo.setTel(rs.getString("tel"));
					vo.setGradeNo(rs.getInt("gradeNo"));
					vo.setGradeName(rs.getString("gradeName"));
					vo.setStatus(rs.getString("status"));
					
					list.add(vo);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			
		}
		
		// 결과 리턴
		return list;
	} // end of list()
	
	// 2. 회원정보 상세보기(관리자) / 내 정보보기(일반회원)
	public MemberVO view(String id) throws Exception {
		// 결과저장변수 선언 및 초기화
		MemberVO vo = null;
		
		try {
			// 1. 드라이버확인
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL작성 - VIEW - 클래스하단 상수
			System.out.println(VIEW);
			// 4. 실행객체에 SQL + 데이터세팅 (?: 1개) - id
			pstmt = con.prepareStatement(VIEW);
			pstmt.setString(1, id);
			// 5. 실행 및 결과 리턴
			rs = pstmt.executeQuery();
			// 6. 결과 저장
			if (rs != null && rs.next()) {
				vo = new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setPw(rs.getString("pw"));
				vo.setName(rs.getString("name"));
				vo.setGender(rs.getString("gender"));
				vo.setBirth(rs.getString("birth"));
				vo.setTel(rs.getString("tel"));
				vo.setEmail(rs.getString("email"));
				vo.setZipcode(rs.getString("zipcode"));
				vo.setAddr1(rs.getString("addr1"));
				vo.setAddr2(rs.getString("addr2"));
				vo.setPhoto(rs.getString("photo"));
				vo.setRegDate(rs.getString("regDate"));
				vo.setConDate(rs.getString("conDate"));
				vo.setStatus(rs.getString("status"));
				vo.setGradeNo(rs.getInt("gradeNo"));
				vo.setGradeName(rs.getString("gradeName"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			
		}
		// 결과 리턴
		return vo;
	} // end of view(String id)
	
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
	
	// 4. 내정보 수정
	public Integer update(MemberVO vo) throws Exception {
		// 결과 저장 변수 선언 및 초기화
		Integer result = null;
		
		try {
			// 1. 드라이버확인 - mySQL의 드라이버를 찾는것
			/* Class.forName("com.mysql.cj.jdbc.Driver");
			 * 이것을 DB클래스에 static메서드에 구현했고
			 * DB클래스를 DispatcherServlet의 init()메서드에서
			 * 호출했습니다.
			 * web.xml에서 init()메서드가 웹서버가 시작시 실행되도록 설정
			 */
			// 2. DB연결
			/* DriverManager.getConnection("DB경로", "접속id", "pw");
			 * ==> 리턴값으로 Connection 자료형의 주소가 넘어옵니다.
			 * 그값은 con 변수에 담아놓고 사용합니다.
			 */
			con = DB.getConnection();
			// 3. SQL 작성 - UPDATE - 클래스하단 상수
			System.out.println(UPDATE);
			// 4. 실행객체에 SQL과 데이터를 세팅 (?: 10개)
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getGender());
			pstmt.setString(3, vo.getBirth());
			pstmt.setString(4, vo.getTel());
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, vo.getZipcode());
			pstmt.setString(7, vo.getAddr1());
			pstmt.setString(8, vo.getAddr2());
			pstmt.setString(9, vo.getId());
			pstmt.setString(10, vo.getPw());
			// 5. 실행 및 결과리턴
			result = pstmt.executeUpdate();
			// 6. 결과확인 controller에 진행

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. DB닫기
			DB.close(con, pstmt);
		}
		
		// 결과리턴
		return result;
	} // end of update(MemberVO vo)
	
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
	private static final String LIST = ""
			+ "select m.id, m.name, "
			+ " date_format(m.birth, '%Y-%m-%d') as birth, "
			+ " m.tel, "
			+ " m.gradeNo, g.gradeName, m.status "
			+ " from member m, grade g "
			+ " where m.gradeNo = g.gradeNo "
			+ " order by m.regDate desc";
	
	private static final String VIEW = ""
			+ "select id, pw, name, gender, "
			+ " date_format(birth, '%Y-%m-%d') as birth, tel, email, "
			+ " zipcode, addr1, addr2, "
			+ " date_format(regDate, '%Y-%m-%d') as regDate, "
			+ " date_format(conDate, '%Y-%m-%d') as conDate, "
			+ " photo, "
			+ " m.gradeNo, gradeName, status "
			+ " from member m, grade g "
			+ " where (id = ?) "
			+ " and (m.gradeNo = g.gradeNo)";
	
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
	
	
	private static final String UPDATE = ""
			+ "update member set name = ?, gender = ?, "
			+ " birth = ?, tel = ?, email = ?, zipcode = ?, "
			+ " addr1 = ?, addr2 = ? "
			+ " where id = ? and pw = ?";
	
	
	
	private static final String LOGIN = ""
			+ "select m.id, m.pw, m.name, m.gradeNo, "
			+ " g.gradeName, m.photo "
			+ " from member m, grade g "//as는 생략가능
			+ " where " // 조건을 물어봅니다.
			+ " (id = ? and pw = ? and status='정상') "//일반조건
			+ " and (m.gradeNo = g.gradeNo)";//조인조건(테이블결합)
	
}







