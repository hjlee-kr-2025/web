package com.gyshop.board.service;

import com.gyshop.board.dao.BoardDAO;
import com.gyshop.board.vo.BoardVO;
import com.gyshop.main.dao.DAO;
import com.gyshop.main.service.Service;

public class BoardWriteService implements Service {

	private BoardDAO dao;
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return dao.write((BoardVO)obj);
	}

	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (BoardDAO)dao;
	}

}
