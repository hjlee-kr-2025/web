package com.gyshop.goods.service;

import com.gyshop.goods.dao.GoodsDAO;
import com.gyshop.goods.vo.GoodsVO;
import com.gyshop.main.dao.DAO;
import com.gyshop.main.service.Service;

public class GoodsWriteService implements Service {

	private GoodsDAO dao;
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return dao.write((GoodsVO)obj);
	}

	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (GoodsDAO)dao;
	}

}
