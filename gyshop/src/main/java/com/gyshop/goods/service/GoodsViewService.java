package com.gyshop.goods.service;

import com.gyshop.goods.dao.GoodsDAO;
import com.gyshop.main.dao.DAO;
import com.gyshop.main.service.Service;

public class GoodsViewService implements Service {

	private GoodsDAO dao;
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return dao.view((Long)obj);
	}

	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (GoodsDAO)dao;
	}
}
