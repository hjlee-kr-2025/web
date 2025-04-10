package com.gyshop.member.service;

import com.gyshop.main.dao.DAO;
import com.gyshop.main.service.Service;
import com.gyshop.member.dao.GradeDAO;
import com.gyshop.member.vo.GradeVO;

public class GradeWriteService implements Service {
	
	private GradeDAO dao;

	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return dao.write((GradeVO)obj);
	}

	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (GradeDAO)dao;
	}

}
