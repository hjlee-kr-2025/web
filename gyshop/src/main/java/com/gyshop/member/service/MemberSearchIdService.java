package com.gyshop.member.service;

import com.gyshop.main.dao.DAO;
import com.gyshop.main.service.Service;
import com.gyshop.member.dao.MemberDAO;
import com.gyshop.member.vo.MemberVO;

public class MemberSearchIdService implements Service {

	private MemberDAO dao;
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return dao.searchId((MemberVO)obj);
	}

	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (MemberDAO)dao;
	}

}
