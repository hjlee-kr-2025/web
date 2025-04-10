package com.gyshop.main.service;

import com.gyshop.main.dao.DAO;

public interface Service {

	// 실행해야할 메서드
	public Object service (Object obj) throws Exception;
	// DAO를 조립해서 사용하기 위한 메서드
	public void setDAO(DAO dao);
}
