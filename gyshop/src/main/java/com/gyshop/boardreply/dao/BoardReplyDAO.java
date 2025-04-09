package com.gyshop.boardreply.dao;

import java.util.ArrayList;
import java.util.List;

import com.gyshop.boardreply.vo.BoardReplyVO;
import com.gyshop.main.dao.DAO;
import com.gyshop.util.db.DB;

public class BoardReplyDAO extends DAO {

	// 1. 댓글 리스트 : 일반게시판의 글번호가 필요합니다.
	public List<BoardReplyVO> list(Long no) throws Exception {
		// 결과 저장 변수 선언 및 초기화
		List<BoardReplyVO> list = null;
		
		try {
			// 1. 드라이버확인
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL 작성 - LIST
			System.out.println(LIST);
			// 4. 실행객체: SQL + 데이터(?: 1개, no)
			pstmt = con.prepareStatement(LIST);
			pstmt.setLong(1, no);
			// 5. 실행 및 결과 리턴
			rs = pstmt.executeQuery();
			// 6. 결과저장
			if (rs != null) {
				while(rs.next()) {
					if (list == null) list = new ArrayList<BoardReplyVO>();
					BoardReplyVO vo = new BoardReplyVO();
					vo.setRno(rs.getLong("rno"));
					vo.setContent(rs.getString("content"));
					vo.setId(rs.getString("id"));
					
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
	} // end of list(Long no)
	
	
	
	// SQL 문
	private static final String LIST = ""
			+ "select rno, content, id "
			+ " from boardreply "
			+ " where no = ? "
			+ " order by rno desc ";
}
