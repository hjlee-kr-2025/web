package com.gyshop.notice.service;

import com.gyshop.main.dao.DAO;
import com.gyshop.main.service.Service;
import com.gyshop.notice.dao.NoticeDAO;
import com.gyshop.util.page.PageObject;

public class NoticeListService implements Service {

	private NoticeDAO dao;
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		PageObject pageObject = (PageObject)obj;
		
		// 전체데이터수를 가져와서 페이지세팅을 진행합니다.
		Long totalRow = dao.getTotalRow(pageObject);
		// pageObject에 totalRow로 페이지 세팅 진행
		pageObject.setTotalRow(totalRow);
		
		return dao.list((PageObject)obj);
	}

	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (NoticeDAO)dao;
	}

}
