package com.gyshop.movie.service;

import java.util.List;

import com.gyshop.main.dao.DAO;
import com.gyshop.main.service.Service;
import com.gyshop.movie.dao.MovieDAO;
import com.gyshop.movie.vo.MovieVO;

public class MovieWriteListService implements Service {

	private MovieDAO dao;
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		List<MovieVO> list = (List<MovieVO>) obj;
		int updateCount = 0;
		
		for (MovieVO vo : list) {
			String checkStr = dao.searchCode(vo.getMovieCd());
			if (checkStr == null || !checkStr.equals(vo.getMovieCd())) {
				if (!vo.getRepGenreNm().equals("성인물(에로)")) {
					vo.setSaveFlag(1);
					updateCount++;
				}
			}
		}
		
		if (updateCount != 0) {
			updateCount = dao.writeList(list);
		}
		
		return updateCount;
	}

	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (MovieDAO)dao;
	}

}
