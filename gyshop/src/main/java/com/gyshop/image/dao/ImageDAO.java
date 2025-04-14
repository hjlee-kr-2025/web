package com.gyshop.image.dao;

import java.util.ArrayList;
import java.util.List;

import com.gyshop.image.vo.ImageVO;
import com.gyshop.main.dao.DAO;
import com.gyshop.util.db.DB;

public class ImageDAO extends DAO {

	// 1-2. LIST
	public List<ImageVO> list() throws Exception {
		// 결과저장변수 선언 및 초기화
		List<ImageVO> list = null;
		
		try {
			// 1. 드라이버확인 - JDBD드라이버(MySQL)
			// - DispatcherServlet.init() - Init클래스 static메서드
			// - DB의 static메서드 - Class.forName()으로 로딩됩니다.
			// 2. DB연결 - DriverManager.getConnection(URL, ID, PW);
			// 를 통해서 이루어집니다. 그 내용을 DB클래스에 구현했습니다.
			con = DB.getConnection();
			// 3. SQL 작성 - LIST
			System.out.println(LIST);
			// 4. 실행객체에 SQL + 데이터세팅
			pstmt = con.prepareStatement(LIST);
			// 5. 실행 및 결과리턴
			rs = pstmt.executeQuery();
			// 6. 결과저장
			if (rs != null) {
				while(rs.next()) {
					if (list == null) list = new ArrayList<ImageVO>();
					ImageVO vo = new ImageVO();
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setFileName(rs.getString("fileName"));
					vo.setName(rs.getString("name"));
					vo.setWriteDate(rs.getString("writeDate"));
					vo.setHit(rs.getLong("hit"));
					
					list.add(vo);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. DB닫기
			DB.close(con, pstmt, rs);
		}
		
		// 결과리턴
		return list;
	} // end of list()
	
	// 3. 이미지 글등록
	public Integer write(ImageVO vo) throws Exception {
		// 결과저장변수 선언 및 초기화
		Integer result = null;
		
		try {
			// 1. 드라이버확인
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL - WRITE
			System.out.println(WRITE);
			// 4. 실행객체에 SQL + 데이터세팅(?: 4개, title,content,fileName,id)
			pstmt = con.prepareStatement(WRITE);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getFileName());
			pstmt.setString(4, vo.getId());
			// 5. 실행 및 결과리턴
			result = pstmt.executeUpdate();
			// 6. 실행결과는 controller에서 체크
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. DB닫기
			DB.close(con, pstmt);
		}
		
		// 결과리턴
		return result; 
	}
	
	// SQL 작성
	private static final String LIST = ""
			+ "select i.no, i.title, i.fileName, m.name,"
			+ " date_format(i.writeDate, '%Y-%m-%d') as writeDate,"
			+ " i.hit "
			+ " from image as i, member as m "// as 는 생략가능합니다.
			+ " where (i.id = m.id) "
			+ " order by no desc";
	
	private static final String WRITE = ""
			+ "inset into image (title, content, fileName, id) "
			+ "	values (?, ?, ? ,?)";
}
