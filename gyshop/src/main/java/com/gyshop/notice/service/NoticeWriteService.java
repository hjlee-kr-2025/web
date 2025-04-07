package com.gyshop.notice.service;

import com.gyshop.main.dao.DAO;
import com.gyshop.main.service.Service;
import com.gyshop.notice.dao.NoticeDAO;
import com.gyshop.notice.vo.NoticeVO;

public class NoticeWriteService implements Service {

	private NoticeDAO dao;
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return dao.write((NoticeVO)obj);
	}

	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (NoticeDAO)dao;
	}

}
