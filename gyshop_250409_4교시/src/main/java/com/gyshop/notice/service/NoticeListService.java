package com.gyshop.notice.service;

import com.gyshop.main.dao.DAO;
import com.gyshop.main.service.Service;
import com.gyshop.notice.dao.NoticeDAO;

public class NoticeListService implements Service {

	private NoticeDAO dao;
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return dao.list();
	}

	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (NoticeDAO)dao;
	}

}
