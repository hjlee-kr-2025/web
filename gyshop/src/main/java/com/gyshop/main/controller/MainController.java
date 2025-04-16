package com.gyshop.main.controller;

import javax.servlet.http.HttpServletRequest;

import com.gyshop.util.exe.Execute;
import com.gyshop.util.page.PageObject;

public class MainController {

	public String execute(HttpServletRequest request) {
		System.out.println("MainController.execute() -----");
		// 이동할 페이지 주소를 담을 변수 선언 & 초기화
		String jsp = null;
		
		// 결과담을 변수 선언 및 초기화
		Object result = null;
		
		// 메뉴를 담을 변수선언하고 uri경로를 담는다.
		String uri = request.getRequestURI();
		
		try {
			switch(uri) {
			case "/main/main.do":
				System.out.println("MAIN 처리 -----");
				// 일반게시판 리스트를 DB에서 가져옵니다.
				PageObject pageObject = new PageObject();
				
				// 한페이지데이터(5개) 를 가져오도록 세팅
				pageObject.setPerPageNum(5L);
				// 서비스실행
				result = Execute.execute(Init.get("/board/list.do"), pageObject);
				request.setAttribute("boardList", result);
				
				jsp = "main/main";
				// "/WEB-INF/views/main/main.jsp"
				break;
			default:
				request.setAttribute("uri", uri);
				jsp = "error/404";
				// "/WEB-INF/views/error/404.jsp"
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		// 이동할 페이지 리턴
		return jsp;
	}
}
