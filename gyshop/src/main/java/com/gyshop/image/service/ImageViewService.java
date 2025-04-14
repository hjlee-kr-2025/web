package com.gyshop.image.service;

import com.gyshop.image.dao.ImageDAO;
import com.gyshop.main.dao.DAO;
import com.gyshop.main.service.Service;

public class ImageViewService implements Service {

	private ImageDAO dao;
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		// 글번호와 조회수증가 flag를 array로 넘겨받습니다.
		Long[] args = (Long[])obj;
		Long no = args[0];
		Long inc = args[1];
		if (inc == 1L) {
			// 조회수 증가
			dao.increase(no);
		}
		
		return dao.view(no);
	}

	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (ImageDAO)dao;
	}

}
