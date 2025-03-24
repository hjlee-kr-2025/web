package com.gyshop.main.controller;

import java.util.HashMap;
import java.util.Map;

import com.gyshop.board.dao.BoardDAO;
import com.gyshop.board.service.BoardListService;
import com.gyshop.main.dao.DAO;
import com.gyshop.main.service.Service;

public class Init {

	// Service 를 생성해서 저장하는 객체 <URI, Service>
	private static Map<String, Service> serviceMap = new HashMap<>();
	// dao 를 생성해서 저장하는 객체 <className, dao>
	private static Map<String, DAO> daoMap = new HashMap<>();
	
	// 자바에서 클래스를 사용하려면? 주소를 할당해야 합니다.
	// ==> 생성자를 사용한 이유
	// 웹프로그램에서는 한번만 생성(할당)해서 공유합니다.
	// 한번 new 한것을 key값으로 저장해서 사용하는 방식으로 구현했습니다.
	
	// 생성하는 내용은 static 메서드에 구현 (Init이 로드될때 처리)
	static {
		// 1. dao 생성
		// BoardDao 생성 (일반게시판) -> daoMap에 저장
		daoMap.put("boardDAO", new BoardDAO());
		
		// 2. service 생성
		serviceMap.put("/board/list.do", new BoardListService());
		
		// 3. 조립 : service에 dao를 할당
		serviceMap.get("/board/list.do")
	}
	
	
	
	
	
}
