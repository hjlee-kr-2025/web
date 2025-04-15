package com.gyshop.image.service;

import com.gyshop.image.dao.ImageDAO;
import com.gyshop.main.dao.DAO;
import com.gyshop.main.service.Service;
import com.gyshop.util.page.PageObject;

public class ImageListService implements Service {

	private ImageDAO dao;
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		// 전체 데이터 수 확인
		PageObject pageObject = (PageObject)obj;
		Long totalRow = dao.getTotalRow(pageObject);
		pageObject.setTotalRow(totalRow);
		
		return dao.list(pageObject);
	}

	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (ImageDAO)dao;
	}

}
