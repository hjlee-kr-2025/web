package com.gyshop.ajax.controller;

import javax.servlet.http.HttpServletRequest;

import com.gyshop.main.controller.Init;
import com.gyshop.util.exe.Execute;

public class AjaxController {

	public String execute(HttpServletRequest request) {
		// 이동경로
		String jsp = null;
		System.out.println("AjaxController.execute() -----");
		
		// 메뉴확인
		String uri = request.getRequestURI();
		
		try {
			switch(uri) {
			case "ajax/checkId.do":
				System.out.println("아이디 중복체크 처리");
				// 서비스로 넘어가는 데이터 수집
				String id = request.getParameter("id");
				// 서비스 실행 -> 결과(id) 받습니다.
				id = (String)Execute.execute(Init.get(uri), id);
				// jsp로 전달되는 데이터 담기
				request.setAttribute("id", id);
				// 로드할 jsp경로 표시
				jsp = "member/chekcId";
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		// 이동할 경로 리턴
		return jsp;
	}
}
