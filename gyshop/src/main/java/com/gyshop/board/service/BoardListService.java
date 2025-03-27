package com.gyshop.board.service;

import com.gyshop.board.dao.BoardDAO;
import com.gyshop.main.dao.DAO;
import com.gyshop.main.service.Service;
import com.gyshop.util.page.PageObject;

public class BoardListService implements Service {
	
	private BoardDAO dao;

	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		// 웹브라우저에서 localhost/board/list.do 실행
		// ---> DispatcherServlet 의 service 메서드
		// ---> BoardController 의 execute() 메서드가 실행
		// ---> Execute.execute() 를 통해서 이곳까지 옵니다.
		// ===> 여기서 BoardDAO 의 list()메서드를 호출해서 결과를 리턴받습니다.
		PageObject pageObject = (PageObject) obj;
		
		// 전체 데이터 정보를 DB에서 가져오고 페이지세팅을 합니다.
		pageObject.setTotalRow(dao.getTotalRow(pageObject));
		
		return dao.list(pageObject);
	}

	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		// Servlet init 메서드를 통해 할당한 boardDAO 를 사용합니다.
		this.dao = (BoardDAO)dao;
	}

}
