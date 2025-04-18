package com.gyshop.cart.dao;

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
	
	// SQL 쿼리
	private static final String LIST = ""
			+ "";
/* LIST
 * select c.no, c.id, c.gno, c.count, g.name, g.photo, g.price, g.delivery_cost
	from cart c, goods g 
    where (id = 'kim')
    and (c.gno = g.no);	
 */
	
	private static final String WRITE = ""
			+ "insert into cart (id, gno, count) "
			+ "	values (?, ?, ?)";
	
}
