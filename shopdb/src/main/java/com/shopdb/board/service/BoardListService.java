package com.shopdb.board.service;

import com.shopdb.board.dao.BoardDAO;
import com.shopdb.main.dao.DAO;

public class BoardListService implements Service {

	private BoardDAO dao;
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return dao.list();
	}

	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (BoardDAO)dao;
	}

}
