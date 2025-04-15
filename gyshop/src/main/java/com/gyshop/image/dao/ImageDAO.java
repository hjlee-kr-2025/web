package com.gyshop.image.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.gyshop.image.vo.ImageVO;
import com.gyshop.main.dao.DAO;
import com.gyshop.util.db.DB;
import com.gyshop.util.page.PageObject;

public class ImageDAO extends DAO {

	// 1-1. image테이블의 전체 데이터 수
	public Long getTotalRow(PageObject pageObject) throws Exception {
		// 결과저장변수 선언 및 초기화
		Long totalRow = 0L;
		
		try {
			// 1. 드라이버확인
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL - GETTOTALROW
			System.out.println(GETTOTALROW);
			// 4. 실행객체에 SQL + 데이터세팅
			pstmt = con.prepareStatement(GETTOTALROW);
			// 5. 실행 및 결과리턴
			rs = pstmt.executeQuery();
			// 6. 결과저장
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
	} // end of getTotalRow(PageObject pageObject)
	
	// 1-2. LIST
	public List<ImageVO> list(PageObject pageObject) throws Exception {
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
			System.out.println(getList(pageObject));
			// 4. 실행객체에 SQL + 데이터세팅(rnum 2개)
			pstmt = con.prepareStatement(getList(pageObject));
			int idx = 0;
			idx = setSearchData(pageObject, pstmt, idx);
			pstmt.setLong(++idx, pageObject.getStartRow());
			pstmt.setLong(++idx, pageObject.getEndRow());
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
	
	// 2-1. 조회수 증가
	public Integer increase(Long no) throws Exception {
		// 결과저장변수 선언 및 초기화
		Integer result = null;
		
		try {
			// 1. 드라이버 확인
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL - INCREASE
			System.out.println(INCREASE);
			// 4. 실행객체에 SQL + 데이터 세팅(?: 1개, no)
			pstmt = con.prepareStatement(INCREASE);
			pstmt.setLong(1, no);
			// 5. 실행 및 결과리턴
			result = pstmt.executeUpdate();
			// 6. 결과확인 - controller에서
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. DB닫기
			DB.close(con, pstmt);
		}
		
		// 결과 리턴
		return result;
	} // end of increase(Long no)
	
	// 2-2. 갤러리 글 보기
	public ImageVO view(Long no) throws Exception {
		// 결과저장변수 선언 및 초기화
		ImageVO vo = null;
		
		try {
			// 1. 드라이버확인
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL - VIEW
			System.out.println(VIEW);
			// 4. 실행객체 - SQL + 데이터세팅(?: 1,no)
			pstmt = con.prepareStatement(VIEW);
			pstmt.setLong(1, no);
			// 5. 실행 및 결과리턴
			rs = pstmt.executeQuery();
			// 6. 결과저장
			if (rs != null && rs.next()) {
				vo = new ImageVO();
				vo.setNo(rs.getLong("no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setFileName(rs.getString("fileName"));
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
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
		
		// 결과리턴
		return vo;
	} // end of view(Long no)
	
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
	} // end of write(ImageVO vo)
	
	// 이미지게시판 수정
	public Integer update(ImageVO vo) throws Exception {
		// 결과저장변수 선언, 초기화
		Integer result = null;
		
		try {
			// 1. 드라이버확인
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL - UPDATE
			System.out.println(UPDATE);
			// 4. 실행객체 - SQL + 데이터(title, content, fileName, no, id)
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getFileName());
			pstmt.setLong(4, vo.getNo());
			pstmt.setString(5, vo.getId());
			// 5. 실행 및 결과리턴
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
	} // end of update(ImageVO vo)
	
	// 이미지 게시판 글 삭제
	public Integer delete(ImageVO vo) throws Exception {
		// 결과저장변수 선언, 초기화
		Integer result = null;
		
		try {
			// 1. 드라이버확인
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL - DELETE
			System.out.println(DELETE);
			// 4. 실행객체에 SQL + 데이터세팅(no, id)
			pstmt = con.prepareStatement(DELETE);
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getId());
			// 5. 실행 및 결과리턴
			result = pstmt.executeUpdate();
			// 6. 결과는 controller에서
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7.DB닫기
			DB.close(con, pstmt);
		}
		
		// 결과리턴
		return result;
	}
	
	private int setSearchData(PageObject pageObject,
			PreparedStatement pstmt, int idx) throws Exception {
		String key = pageObject.getKey();
		String word = pageObject.getWord();
		
		System.out.println("key: " + key + ", word: " + word);
		
		if (word != null && !word.equals("")) {
			if (key.indexOf("t")>=0) pstmt.setString(++idx, "%" + word + "%");
			if (key.indexOf("c")>=0) pstmt.setString(++idx, "%" + word + "%");
			if (key.indexOf("w")>=0) pstmt.setString(++idx, "%" + word + "%");
		}
		
		return idx;
	}
	
	
	// SQL 작성
	private static final String GETTOTALROW = ""
			+ "select count(*) from image";

	private static final String LIST1 = ""
			+ "select no, title, fileName, name, writeDate, hit "
			+ " from "
			+ "(select "
			+ " @rownum := @rownum + 1 as rnum, "
			+ " i.no, i.title, i.fileName, m.name,"
			+ " date_format(i.writeDate, '%Y-%m-%d') as writeDate,"
			+ " i.hit "
			+ " from image as i, member as m, "
			+ " (select @rownum := 0) as rn ";

	
	private String getSearch(PageObject pageObject) {
		String sql = "";
		
		String key = pageObject.getKey();
		String word = pageObject.getWord();
		
		sql += " where ";
		
		if (word != null && !word.equals("")) {
			sql += " ((1=0) ";
			if (key.indexOf("t")>=0) sql += " or title like ? ";
			if (key.indexOf("c")>=0) sql += " or content like ? ";
			if (key.indexOf("w")>=0) sql += " or name like ? ";
			
			sql += " ) and ";
		}
		sql += " (i.id = m.id) ";// 조인조건은 일반조건이 완료된 후 사용
		return sql;
	}
	
	private String getList(PageObject pageObject) {
		String str = LIST1;
		str += getSearch(pageObject);
		if (pageObject.getOrderStyle() == 2) {
			str += " order by no ";
		}
		else if (pageObject.getOrderStyle() == 3) {
			str += " order by hit desc, no desc ";
		}
		else {
			str += " order by no desc ";
		}
		str += LIST2;
		return str;
	}
			
	private static final String LIST2 = ""
			+ " ) as pageImage "
			+ " where rnum >= ? and rnum <= ?";
	
	private static final String INCREASE = ""
			+ "update image set hit = hit + 1 where no = ?";
	
	private static final String VIEW = ""
			+ "select i.no, i.title, i.content, i.fileName, i.id, m.name,"
			+ " date_format(i.writeDate, '%Y-%m-%d') as writeDate, i.hit "
			+ " from image as i, member as m "
			+ " where no = ? "
			+ " and (i.id = m.id)";
	
	private static final String WRITE = ""
			+ "insert into image (title, content, fileName, id) "
			+ "	values (?, ?, ? ,?)";
	
	private static final String UPDATE = ""
			+ "update image set title = ?,"
			+ " content = ?, fileName = ? "
			+ " where no = ? and id = ?";
	
	private static final String DELETE = ""
			+ "delete from image "
			+ " where no = ? and id = ?";
}







