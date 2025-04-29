package com.gyshop.movie.dao;

import java.sql.PreparedStatement;
import java.util.List;

import com.gyshop.main.dao.DAO;
import com.gyshop.movie.vo.MovieVO;
import com.gyshop.util.db.DB;

public class MovieDAO extends DAO {

	public Integer writeList(List<MovieVO> list) throws Exception {
		Integer result = 0;
		
		try {
			con = DB.getConnection();
			//(movieCd, movieNm, movieNmEn, "
			//+ " openDt, typeNm, prdtStatNm, repNationNm, "
			//+ " repGenreNm, peopleNm)
			System.out.println(getWriteListQuery(list));
			pstmt = con.prepareStatement(getWriteListQuery(list));
			int idx = 0;
			idx = setWriteListQuery(list, pstmt, idx);
			result = pstmt.executeUpdate();
			System.out.println("저장된 데이터 수 : " + result);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt);
		}
		
		
		return result;
	}
	
	public String searchCode(String code) throws Exception {
		String result = null;
		
		try {
			con = DB.getConnection();
			pstmt = con.prepareStatement("select movieCd from movie where movieCd = ?");
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				result = rs.getString("movieCd");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}
		
		return result;
	}
	
	private int setWriteListQuery(List<MovieVO> list,
			PreparedStatement pstmt, int idx) throws Exception {
		
		for (int i = 0 ; i<list.size() ; i++) {
			
			if (list.get(i).getSaveFlag() == 1) {
				pstmt.setString(++idx, list.get(i).getMovieCd());
				pstmt.setString(++idx, list.get(i).getMovieNm());
				pstmt.setString(++idx, list.get(i).getMovieNmEn());
				pstmt.setString(++idx, list.get(i).getOpenDt());
				pstmt.setString(++idx, list.get(i).getTypeNm());
				pstmt.setString(++idx, list.get(i).getPrdtStatNm());
				pstmt.setString(++idx, list.get(i).getRepNationNm());
				pstmt.setString(++idx, list.get(i).getRepGenreNm());
				pstmt.setString(++idx, list.get(i).getPeopleNm());
			}
		}
		
		return idx;
	}
	
	
	private String getWriteListQuery(List<MovieVO> list) {
		String str = WRITELIST;
		int start = 0;
		
		for (int i = 0 ; i<list.size() ; i++) {
			if (list.get(i).getSaveFlag() == 1) {
				if (start != 0) str += ",";
				start = 1;
				str += "(?,?,?,?,?,?,?,?,?)";
			}
		}
		
		return str;
	}
	
	private final static String WRITELIST = ""
			+ "insert into movie (movieCd, movieNm, movieNmEn, "
			+ " openDt, typeNm, prdtStatNm, repNationNm, "
			+ " repGenreNm, peopleNm) values ";
}
