package com.gyshop.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MemberController {

	public String execute(HttpServletRequest request) {
		// 이동할 페이지를 저장하는 변수 선언
		String jsp = null;
		System.out.println("MemberController.execute() -----");
		
		// 세션정보 가져오기
		HttpSession session = request.getSession();
		
		// 메뉴를 위한 uri
		String uri = request.getRequestURI();
		
		// 결과 저장용 변수
		Object result = null;
		
		try {
			switch (uri) {
			case "/member/writeForm.do":
				System.out.println("회원가입 폼 ---");
				jsp = "member/writeForm";
				// "/WEB-INF/views/member/writeForm.jsp"
				break;
			case "/member/write.do":
				System.out.println("회원가입 처리---");
				break;
			default:
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		// 이동할 페이지 리턴
		return jsp;
	} // end of execute()
} 
