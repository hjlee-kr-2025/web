package com.gyshop.cart.service;

import com.gyshop.cart.dao.CartDAO;
import com.gyshop.main.dao.DAO;
import com.gyshop.main.service.Service;

public class CartListService implements Service {

	private CartDAO dao;
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return dao.list((String)obj);
	}

	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (CartDAO)dao;
	}

}
