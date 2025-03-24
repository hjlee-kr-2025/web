package com.shopdb.main.service;

import com.shopdb.main.dao.DAO;

public interface Service {

	// 실행해야할 메서드
	public Object service (Object obj) throws Exception;
	// DAO 조립을 위한 메서드
	public void setDAO(DAO dao);
}
