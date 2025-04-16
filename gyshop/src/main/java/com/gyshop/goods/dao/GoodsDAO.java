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
	
	// SQL
	private static final String LIST = ""
			+ "select no, name, photo, price, delevery_cost, modelNo "
			+ " from goods order by no desc";
	
}
