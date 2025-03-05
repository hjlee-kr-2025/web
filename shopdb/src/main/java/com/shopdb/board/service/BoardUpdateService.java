package com.shopdb.board.service;

import com.shopdb.board.dao.BoardDAO;
import com.shopdb.board.vo.BoardVO;
import com.shopdb.main.dao.DAO;

public class BoardUpdateService implements Service {

	private BoardDAO dao;
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return dao.update((BoardVO)obj);
	}

	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (BoardDAO)dao;
	}
	
}
