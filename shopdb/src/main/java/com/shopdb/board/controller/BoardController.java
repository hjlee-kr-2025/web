package com.shopdb.board.controller;

import javax.servlet.http.HttpServletRequest;

import com.shopdb.main.controller.Init;
import com.shopdb.util.Execute;

// 일반게시판 메뉴 선택, 데이터 수집
public class BoardController {

	public String execute(HttpServletRequest request) {
		// 이동할 주소를 담을 변수
		String jsp = null;
		
		// 메뉴입력 (uri)
		String uri = request.getRequestURI();
		
		// 데이터 수집을 위한 객체 선언
		Object result = null;
		
		try {
			switch (uri) {
			case "/board/list.do":
				System.out.println("1. 일반 게시판 리스트");
				
				// 서비스 실행
				// uri key값으로 service 객체를 가져옵니다.
				result = Execute.execute(Init.get(uri), null);
				
				// DB에서 실행한 결과가 result에 담깁니다.
				// result 를 front 에 출력하기 위해 request에 담습니다.
				request.setAttribute("list", result);
				
				jsp = "board/list";
				//=> DispatcherServlet 에서
				// "/WEB-INF/views/board/list.jsp"
				break;
			case "/board/view.do":
				break;
			case "/board/write.do":
				break;
			case "/board/update.do":
				break;
			case "/board/delete.do":
				break;
			default:
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return jsp;
	}
}
