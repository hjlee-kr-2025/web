package javabasic.ch11;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
	
	ResultSet rs;

	public List<BoardVO> list() {
		List<BoardVO> list = null;
		
		try {
			// 데이터를 가져왔다..
			// 6단계
			
			if (rs != null) {
				while (rs.next()) {
					if (list == null) list = new ArrayList<BoardVO>();
					
					// Record 를 사용했을때 DB데이터를 저장하는 방법
					BoardVO vo = new BoardVO(
						rs.getLong("no"), 
						rs.getString("title"),
						null,
						rs.getString("writer"),
						rs.getString("writeDate"),
						rs.getLong("hit"),
						null);
					
					list.add(vo);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return list;
	}
}
