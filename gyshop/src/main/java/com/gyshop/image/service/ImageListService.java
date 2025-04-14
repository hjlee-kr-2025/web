package com.gyshop.image.service;

import com.gyshop.image.dao.ImageDAO;
import com.gyshop.main.dao.DAO;
import com.gyshop.main.service.Service;

public class ImageListService implements Service {

	private ImageDAO dao;
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return dao.list();
	}

	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (ImageDAO)dao;
	}

}
