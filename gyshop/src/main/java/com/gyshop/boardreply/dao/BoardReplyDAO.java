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
	
	// 댓글에는 글보기가 별도로 없습니다.
	// 리스트 보여줄 때 내용이 보여집니다.
	
	// 3. 댓글 등록
	public Integer write(BoardReplyVO vo) throws Exception {
		// 결과저장변수 선언 및 초기화
		Integer result = null;
		
		try {
			// 1. 드라이버 확인
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL - WRITE
			System.out.println(WRITE);
			// 4. 실행객체 - SQL + 데이터세팅(?: 3개, no, content, id)
			pstmt = con.prepareStatement(WRITE);
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getId());
			// 5. 실행 및 결과리턴
			result = pstmt.executeUpdate();
			// 6. 결과는 controller에서 체크
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. DB닫기
			DB.close(con, pstmt);
		}
		
		// 결과리턴
		return result;
	} // end of write(BoardReplyVO vo)
	
	// 4. 댓글 수정 (내용만 수정, 댓글번호, id확인 후)
	public Integer update(BoardReplyVO vo) throws Exception {
		// 결과 저장 변수 선언 및 초기화
		Integer result = null;
		
		try {
			// 1. 드라이버 확인
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL - UPDATE
			System.out.println(UPDATE);
			// 4. 실행객체 - SQL + 데이터세팅(?: 3개, content, rno, id)
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, vo.getContent());
			pstmt.setLong(2, vo.getRno());
			pstmt.setString(3, vo.getId());
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
		
		// 결과 리턴
		return result;
	} // end of update(BoardReplyVO vo)
	
	// SQL 문
	private static final String LIST = ""
			+ "select rno, content, id "
			+ " from boardreply "
			+ " where no = ? "
			+ " order by rno desc ";
	
	private static final String WRITE = ""
			+ "insert into boardreply "
			+ " (no, content, id) "
			+ " values (?, ?, ?)";
	
	private static final String UPDATE = ""
			+ "update boardreply set content = ? "
			+ " where rno = ? and id = ?";
	// 댓글번호와 로그인한 id를 가지고 수정을 진행합니다.
}





