package com.gyshop.board.service;

import com.gyshop.board.dao.BoardDAO;
import com.gyshop.main.dao.DAO;
import com.gyshop.main.service.Service;

public class BoardViewService implements Service {
	
	private BoardDAO dao;

	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		Long[] arr = (Long[]) obj; 
		Long inc = arr[1];
		Long no = arr[0];
		// 1. 조회수 증가 또는 안함
		if (inc == 1L) {
			// 조회수를 증가합니다.
			// 글보기에서는 증가,
			// 수정을 위해서 글을 가져올때는 증가하지 않도록 구현
			dao.increase(no);
		}
		// 2. 글번호로 데이터 가져오기
		return dao.view(no);
	}

	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		// BoardViewService에서 BoardDAO를 사용할 수 있는 주소 설정
		this.dao = (BoardDAO)dao;
	}

}
