package com.shopdb.main.controller;

import java.util.HashMap;
import java.util.Map;

import com.shopdb.board.dao.BoardDAO;
import com.shopdb.board.service.BoardDeleteService;
import com.shopdb.board.service.BoardListService;
import com.shopdb.board.service.BoardUpdateService;
import com.shopdb.board.service.BoardViewService;
import com.shopdb.board.service.BoardWriteService;
import com.shopdb.board.service.Service;
import com.shopdb.main.dao.DAO;

public class Init {

	// Service를 생성해서 저장하는 객체 - <URI, Service>
	private static Map<String, Service> serviceMap
		= new HashMap<>();
	// dao를 생성해서 저장하는 객체 - <className, dao>
	private static Map<String, DAO> daoMap
		= new HashMap<>();
	
	// Init 클래스를 만든이유
	// 자바에서 클래스를 사용하기 위해서는 주소할당(생성)이 필요합니다.
	// new 생성자()를 사용해서 메모리를 할당합니다.
	// new 할때마다 메모리 공간이 할당되어서 서버프로그램에서는 한번만
	// 할당하고 공유해서 사용합니다.
	// 한번만 new 한값을 key값에 저장해 두고 사용합니다.
	
	// 한번만 초기화 하는 메서드 - static 메서드 사용
	static {
		// 일반게시판
		// dao를 생성
		daoMap.put("boardDAO", new BoardDAO());
		// 일반게시판 서비스 생성
		// new BoardListService() ==> 주소값
		// key : url, value : 주소값
		serviceMap.put("/board/list.do", new BoardListService());
		serviceMap.put("/board/view.do", new BoardViewService());
		serviceMap.put("/board/write.do", new BoardWriteService());
		serviceMap.put("/board/update.do", new BoardUpdateService());
		serviceMap.put("/board/delete.do", new BoardDeleteService());
		
		// 조립 dao -> service 에서 사용할 수 있도록
		serviceMap.get("/board/list.do").setDAO(daoMap.get("boardDAO"));
		serviceMap.get("/board/view.do").setDAO(daoMap.get("boardDAO"));
		serviceMap.get("/board/write.do").setDAO(daoMap.get("boardDAO"));
		serviceMap.get("/board/update.do").setDAO(daoMap.get("boardDAO"));
		serviceMap.get("/board/delete.do").setDAO(daoMap.get("boardDAO"));
	}
	
	public static Service get(String uri) {
		// uri 키값으로 서비스주소를 리턴
		return serviceMap.get(uri);
	}
	
}
