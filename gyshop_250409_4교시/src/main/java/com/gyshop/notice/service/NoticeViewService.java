package com.gyshop.notice.service;

import com.gyshop.main.dao.DAO;
import com.gyshop.main.service.Service;
import com.gyshop.notice.dao.NoticeDAO;

public class NoticeViewService implements Service {

	private NoticeDAO dao;
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stuba
		System.out.println("NoticeViewService.service() ------");
		Long[] arr = (Long[])obj;
		Long inc = arr[1];
		Long no = arr[0];
		// 조회수 증가시
		if (inc == 1L) {
			// 조회수를 증가합니다.
			dao.increase(no);
		}
		
		return dao.view(no);
	}

	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (NoticeDAO)dao;
	}

}
