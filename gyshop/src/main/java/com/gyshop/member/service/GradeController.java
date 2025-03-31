package com.gyshop.member.service;

import javax.servlet.http.HttpServletRequest;

import com.gyshop.main.controller.Init;
import com.gyshop.util.exe.Execute;

public class GradeController {

	public String execute(HttpServletRequest request) {
		// 이동할 주소를 담는 변수 - 리턴으로사용
		String jsp = null;
		System.out.println("GradeController.execute() ---------");
		
		// 메뉴를 받습니다.
		String uri = request.getRequestURI();
		// ==> getRequestURI() 는 메인주소를 제외한 나머지 주소 리턴
		
		// DB처리후 리턴받는 데이터 수집하는 변수
		Object result = null;
		
		try {
			switch(uri) {
			case "/grade/list.do":
				System.out.println("1. 회원등급 리스트");
				// 서비스로 넘어가는 데이터
				// list는 없습니다.
				// 서비스 실행 및 결과 리턴받기
				result = Execute.execute(Init.get(uri), null);
				// 받은 데이터를 request에 담아서 jsp로 전달합니다.
				request.setAttribute("list", result);
				// jsp 로 이동
				jsp = "grade/list";
				// "/WEB-INF/views/grade/list.jsp"
				break;
			case "/grade/write.do":
				break;
			case "/grade/update.do":
				break;
			case "/grade/delete.do":
				break;
			default:
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		// 이동할 주소 리턴
		return jsp;
	} // end of execute(
}
