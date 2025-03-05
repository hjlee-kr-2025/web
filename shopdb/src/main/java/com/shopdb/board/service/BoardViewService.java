package com.shopdb.board.service;

import com.shopdb.board.dao.BoardDAO;
import com.shopdb.main.dao.DAO;

public class BoardViewService implements Service {

	private BoardDAO dao;
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		Integer[] objs = (Integer[])obj;
		Integer no = objs[0];
		Integer inc = objs[1];
		if (inc == 1) {
			// 조회수 증가
			dao.increase(no);
		}
		return dao.view(no);
	}

	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (BoardDAO)dao;
	}

}
