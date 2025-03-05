package com.shopdb.board.service;

import com.shopdb.main.dao.DAO;

public interface Service {
	
	// 실행해야할 메서드
	public Object service (Object obj) throws Exception;
	public void setDAO(DAO dao);
}
