package com.gyshop.board.dao;

import java.util.ArrayList;
import java.util.List;

import com.gyshop.board.vo.BoardVO;
import com.gyshop.main.dao.DAO;
import com.gyshop.util.db.DB;

public class BoardDAO extends DAO {

	// DAO 클래스에는 DB사용에 필요한 3가지 객체가 선언되어있습니다
	
	// 1. 일반게시판 리스트
	public List<BoardVO> list() throws Exception {
		// 1) 결과 저장 변수 선언 - 리턴타입과 같은 자료형 선언, 초기값 null
		List<BoardVO> list = null;
		System.out.println("** BoardDAO.list() -----");
		
		// 3) try ~ catch ~ finally 구성
		try {
			// 5) DB 상호작용구현 - try에 2~6단계, finally : 마지막7단계
			// 1. 드라이버확인 - DB클래스 static메서드 - 프로그램시작시 한번으로 끝
			// 2. DB 연결
			con = DB.getConnection();
			System.out.println("----- 2. DB연결완료 -----");
			// 3. SQL 작성 - 클래스하단 LIST 상수로 구현
			System.out.println("3. SQL(LIST) : " + LIST);
			// 4. 실행객체에 SQL + 데이터세팅(?: 0)
			pstmt = con.prepareStatement(LIST); // SQL 세팅
			// 데이터세팅은 없습니다. (?가 없기 때문에)
			System.out.println("4. 실행객체 세팅 완료 ---");
			// 5. 실행 + 결과리턴
			rs = pstmt.executeQuery();
			System.out.println("5. 실행 완료 ---");
			// 6. 결과를 담기(저장)
			if (rs != null) {
				// 리스트는 while문 사용 , 한개의 데이터는 if를 사용
				while (rs.next()) {
					if (list == null) list = new ArrayList<BoardVO>();
					
					BoardVO vo = new BoardVO();
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setWriter(rs.getString("writer"));
					vo.setWriteDate(rs.getString("writeDate"));
					vo.setHit(rs.getLong("hit"));
				}
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			// 4) 예외발생 내용을 보기위한 메서드
			e.printStackTrace();
		} finally {
			
		}
		
		// 2) 결과 리턴 - 선언한 결과저장변수를 리턴
		return list;
	}	// end of list()
	
	// SQL ==================
	private final String LIST = ""
			+ "select no, title, writer, "
			+ " date_format(writeDate, '%Y-%m-%d') as writeDate, hit "
			+ " from board order by no desc";
			// order by no desc -> 최근글 부터 앞에서 보여줌
			// order by no -> 오래된 글부터 앞에서 보여줌
			// order by hit desc -> 조회수 많은 순으로
			// order by hit -> 조회수 적은 순으로
	
}	// end of class




