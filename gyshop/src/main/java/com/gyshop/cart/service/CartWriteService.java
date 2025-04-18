package com.gyshop.cart.service;

import com.gyshop.cart.dao.CartDAO;
import com.gyshop.cart.vo.CartVO;
import com.gyshop.main.dao.DAO;
import com.gyshop.main.service.Service;

public class CartWriteService implements Service {

	private CartDAO dao;
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return dao.write((CartVO)obj);
	}

	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (CartDAO)dao;
	}

}
