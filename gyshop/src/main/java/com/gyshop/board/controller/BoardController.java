package com.gyshop.board.controller;

import javax.servlet.http.HttpServletRequest;

public class BoardController {

	public String execute(HttpServletRequest request) {
		System.out.println("** BoardController.execute()");
		// 이동할 jsp 주소를 담아놓을 변수
		String jsp = null;
		
		// 이동할 주소 리턴
		return jsp;
	}	// end of execute(HttpServletRequest request)
}
