package com.gyshop.cart.dao;

import java.util.ArrayList;
import java.util.List;

import com.gyshop.cart.vo.CartVO;
import com.gyshop.main.dao.DAO;
import com.gyshop.util.db.DB;

public class CartDAO extends DAO {
	
	// 장바구니 리스트
	public List<CartVO> list(String id) throws Exception {
		// 결과저장변수 선언 , 초기화
		List<CartVO> list = null;
		
		try {
			// 1. 드라이버확인
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL 쿼리 - LIST
			System.out.println(LIST);
			// 4. 실행객체에 SQL + 데이터 세팅(?, 1개-id)
			pstmt = con.prepareStatement(LIST);
			pstmt.setString(1, id);
			// 5. 실행 및 결과리턴
			rs = pstmt.executeQuery();
			// 6. 결과저장
			if (rs != null) {
				while(rs.next()) {
					if (list == null) list = new ArrayList<CartVO>();
					CartVO vo = new CartVO();
					vo.setNo(rs.getLong("no"));
					vo.setId(rs.getString("id"));
					vo.setGno(rs.getLong("gno"));
					vo.setCount(rs.getInt("count"));
					vo.setName(rs.getString("name"));
					vo.setPhoto(rs.getString("photo"));
					vo.setPrice(rs.getInt("price"));
					vo.setDelivery_cost(rs.getInt("delivery_cost"));
					vo.setDelivery_option(rs.getInt("delivery_option"));
					
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
	}
	
	// 장바구니 상품수량 변경
	public Integer update(CartVO vo) throws Exception {
		// 결과저장변수 선언 및 초기화
		Integer result = null;
		
		try {
			// 1. 드라이버확인
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL쿼리 작성 - UPDATE
			System.out.println(UPDATE);
			// 4. 실행객체에 SQL쿼리와 데이터세팅(count, no)
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, vo.getCount());
			pstmt.setLong(2, vo.getNo());
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
		// 결과리턴
		return result;
	}
	
	// 장바구니 담기
	public Integer write(CartVO vo) throws Exception {
		// 결과저장변수 선언, 초기화
		Integer result = 0;
		
		try {
			// 1. 드라이버확인
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL쿼리작성 - WRITE
			System.out.println(WRITE);
			// 4. 실행객체에 SQL + 데이터세팅(id, gno, count)
			pstmt = con.prepareStatement(WRITE);
			pstmt.setString(1, vo.getId());
			pstmt.setLong(2, vo.getGno());
			pstmt.setInt(3, vo.getCount());
			// 5. 실행 및 결과리턴받기
			result = pstmt.executeUpdate();
			// 6. 결과는 controller에서 check
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
	
	// 장바구니 상품 1개 삭제
	public Integer delete(Long no) throws Exception {
		// 결과저장변수 선언 및 초기화
		Integer result = null;
		
		try {
			// 1. 드라이버확인
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL쿼리 - DELETE
			System.out.println(DELETE);
			// 4. 실행객체에 SQL + 데이터세팅 (no)
			pstmt = con.prepareStatement(DELETE);
			pstmt.setLong(1, no);
			// 5. 실행 및 결과리턴
			result = pstmt.executeUpdate();
			// 6. 결과확인 controller에서
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. DB닫기
			DB.close(con, pstmt);
		}
		
		// 결과리턴
		return result;
	} // end of delete(Long no)
	
	// 장바구니 비움
	public Integer deleteAll(String id) throws Exception {
		// 결과저장변수 선언 및 초기화
		Integer result = null;
		
		try {
			// 1. 드라이버확인
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL - DELETEALL
			System.out.println(DELETEALL);
			// 4. 실행객체에 SQL + 데이터세팅(id)
			pstmt = con.prepareStatement(DELETEALL);
			pstmt.setString(1, id);
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
		
		// 결과리턴
		return result;
	}
	
	// SQL 쿼리
	private static final String LIST = ""
			+ "select c.no, c.id, c.gno, c.count,"
			+ " g.name, g.photo, g.price, g.delivery_cost,"
			+ " g.delivery_option "
			+ " from cart c, goods g "
			+ " where (id = ?) "
			+ " and (c.gno = g.no)";
/* LIST
 * select c.no, c.id, c.gno, c.count, g.name, g.photo, g.price, g.delivery_cost
	from cart c, goods g 
    where (id = 'kim')
    and (c.gno = g.no);	
 */
	
	private static final String WRITE = ""
			+ "insert into cart (id, gno, count) "
			+ "	values (?, ?, ?)";
	
	private static final String UPDATE = ""
			+ "update cart set count = ? "
			+ " where no = ?";
	
	private static final String DELETE = ""
			+ "delete from cart where no = ?";
	
	private static final String DELETEALL = ""
			+ "delete from cart where id = ?";
}





