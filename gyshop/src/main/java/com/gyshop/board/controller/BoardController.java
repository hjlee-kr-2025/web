package com.gyshop.board.controller;

import javax.servlet.http.HttpServletRequest;

import com.gyshop.main.controller.Init;
import com.gyshop.util.exe.Execute;

public class BoardController {

	public String execute(HttpServletRequest request) {
		System.out.println("** BoardController.execute()");
		// 이동할 jsp 주소를 담아놓을 변수
		String jsp = null;
		
		// 메뉴입력(uri)
		String uri = request.getRequestURI();
		// 데이터 수집을 위한 객체 선언 :
		// 초기값 null : 실행이 정상으로 끝났는지 확인하기 위해
		Object result = null;
		
		try {
			switch (uri) {
			case "/board/list.do":
				System.out.println("1. 일반게시판 리스트");
				System.out.println("웹브라우저 주소창에 "
					+ "localhost/board/list.do 를 실행했을때");
				
				// 서비스로 넘어가는 데이터 세팅
				// 리스트에는 없습니다.
				
				// 서비스를 실행하고 결과를 리턴받습니다.
				result = Execute.execute(Init.get(uri), null);
				
				// 결과 데이터를 request에 담습니다.
				request.setAttribute("list", result);
				// => jsp로 데이터를 전달하기 위해서
				
				jsp = "board/list";
				// "/WEB-INF/views/board/list.jsp" 파일을 웹에 로딩합니다.
				break;
			default:
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		// 이동할 주소 리턴
		return jsp;
	}	// end of execute(HttpServletRequest request)
}
