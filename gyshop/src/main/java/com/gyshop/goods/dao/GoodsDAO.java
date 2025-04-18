package com.gyshop.goods.dao;
// goods 테이블를 관리하는 클래스

import java.util.ArrayList;
import java.util.List;

import com.gyshop.goods.vo.GoodsVO;
import com.gyshop.main.dao.DAO;
import com.gyshop.util.db.DB;

public class GoodsDAO  extends DAO {

	// 상품리스트
	public List<GoodsVO> list () throws Exception {
		// 결과저장 변수 선인 및 초기화
		List<GoodsVO> list = null;
		// 데이터베이스에서 가져오는 데이터는 이미지게시판 형식기준으로
		// 가져오겠습니다.
		
		try {
			// 1. 드라이버확인
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL : LIST
			System.out.println(LIST);
			// 4. 실행객체 - SQL + 데이터세팅
			pstmt = con.prepareStatement(LIST);
			// 5. 실행 및 결과리턴
			rs = pstmt.executeQuery();
			// 6. 결과저장
			if (rs != null) {
				while (rs.next()) {
					if (list == null) list = new ArrayList<GoodsVO>();
					GoodsVO vo = new GoodsVO();
					vo.setNo(rs.getLong("no"));
					vo.setName(rs.getString("name"));
					vo.setPhoto(rs.getString("photo"));
					vo.setPrice(rs.getInt("price"));
					vo.setDelivery_cost(rs.getInt("delivery_cost"));
					vo.setModelNo(rs.getString("modelNo"));
					
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
		
		// 결과 리턴
		return list;
	} // end of list ()
	
	// 상품상세보기
	public GoodsVO view(Long no) throws Exception {
		// 결과저장변수 선언, 초기화
		GoodsVO vo = null;
		
		try {
			// 1. 드라이버확인
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL - VIEW
			System.out.println(VIEW);
			// 4. 실행객체에 SQL + 데이터세팅(?, 1개-no)
			pstmt = con.prepareStatement(VIEW);
			pstmt.setLong(1, no);
			// 5. 실행 그리고 결과리턴
			rs = pstmt.executeQuery();
			// 6. 결과저장
			if (rs != null && rs.next()) {
				vo = new GoodsVO();
				vo.setNo(rs.getLong("no"));
				vo.setName(rs.getString("name"));
				vo.setContent(rs.getString("content"));
				vo.setPhoto(rs.getString("photo"));
				vo.setSubPhoto1(rs.getString("subPhoto1"));
				vo.setSubPhoto2(rs.getString("subPhoto2"));
				vo.setSubPhoto3(rs.getString("subPhoto3"));
				vo.setSubPhoto4(rs.getString("subPhoto4"));
				vo.setPrice(rs.getInt("price"));
				vo.setDelivery_cost(rs.getInt("delivery_cost"));
				vo.setModelNo(rs.getString("modelNo"));
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
	
	// 상품등록
	public Integer write(GoodsVO vo) throws Exception {
		// 결과저장변수 선언, 초기화
		Integer result = null;
		
		try {
			// 1. 드라이버확인
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL - WRITE (name, content, photo, subphoto1~4,
			// price, delivery_cost, modelNo
			System.out.println(WRITE);
			// 4. 실행객체에 SQL, 데이터세팅(10개)
			pstmt = con.prepareStatement(WRITE);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getPhoto());
			pstmt.setString(4, vo.getSubPhoto1());
			pstmt.setString(5, vo.getSubPhoto2());
			pstmt.setString(6, vo.getSubPhoto3());
			pstmt.setString(7, vo.getSubPhoto4());
			pstmt.setInt(8, vo.getPrice());
			pstmt.setInt(9, vo.getDelivery_cost());
			pstmt.setString(10, vo.getModelNo());
			// 5. 실행, 결과리턴
			result = pstmt.executeUpdate();
			// 6. 결과확인 - controller에서
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
	
	// 상품 정보 수정
	public Integer update(GoodsVO vo) throws Exception {
		// 결과 저장변수선언 및 초기화
		Integer result = null;
		
		try {
			// 1. 드라이버확인
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL쿼리작성 - UPDATE
			System.out.println(UPDATE);
			// 4. 실행객체 - SQL + 데이터세팅
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getPhoto());
			pstmt.setString(4, vo.getSubPhoto1());
			pstmt.setString(5, vo.getSubPhoto2());
			pstmt.setString(6, vo.getSubPhoto3());
			pstmt.setString(7, vo.getSubPhoto4());
			pstmt.setInt(8, vo.getPrice());
			pstmt.setInt(9, vo.getDelivery_cost());
			pstmt.setString(10, vo.getModelNo());
			pstmt.setLong(11, vo.getNo());
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
		
		// 결과리턴
		return result;
	} // end of update(GoodsVO vo)
	
	// 상품 삭제
	public Integer delete(Long no) throws Exception {
		// 결과저장변수 선언, 초기화
		Integer result = null;
		
		try {
			// 1. 드라이버확인
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL쿼리 - DELETE
			System.out.println(DELETE);
			// 4. 실행객체에 SQL + 데이터 세팅
			pstmt = con.prepareStatement(DELETE);
			pstmt.setLong(1, no);
			// 5. 실행, 결과리턴
			result = pstmt.executeUpdate();
			// 6. 결과 - controller에서
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. DB닫기
			DB.close(con, pstmt);
		}
		
		// 결과를 리턴
		return result;
	}
	
	// SQL
	private static final String LIST = ""
			+ "select no, name, photo, price, delivery_cost, modelNo "
			+ " from goods order by no desc";
	
	private static final String VIEW = ""
			+ "select no, name, content, photo, subPhoto1, "
			+ " subPhoto2, subPhoto3, subPhoto4, "
			+ " price, delivery_cost, modelNo "
			+ " from goods where no = ?";
	
	private static final String WRITE = ""
			+ "insert into goods "
			+ " (name, content, photo, subPhoto1, subPhoto2, "
			+ " subPhoto3, subPhoto4, price, delivery_cost, "
			+ " modelNo) values "
			+ " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String UPDATE = ""
			+ "update goods set "
			+ " name = ?, content = ?, photo = ?, "
			+ " subPhoto1 = ?, subPhoto2 = ?, "
			+ " subPhoto3 = ?, subPhoto4 = ?, "
			+ " price = ?, delivery_cost = ?, modelNo = ? "
			+ " where no = ?";
	
	private static final String DELETE = ""
			+ "delete from goods where no = ?";
}





