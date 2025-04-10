package com.gyshop.boardreply.service;

import com.gyshop.boardreply.dao.BoardReplyDAO;
import com.gyshop.boardreply.vo.BoardReplyVO;
import com.gyshop.main.dao.DAO;
import com.gyshop.main.service.Service;

public class BoardReplyDeleteService implements Service {

	private BoardReplyDAO dao;
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return dao.delete((BoardReplyVO)obj);
	}

	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (BoardReplyDAO)dao;
	}

}
