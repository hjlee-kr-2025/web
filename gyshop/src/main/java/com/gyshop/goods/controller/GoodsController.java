package com.gyshop.goods.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gyshop.main.controller.Init;
import com.gyshop.member.vo.LoginVO;
import com.gyshop.util.exe.Execute;

public class GoodsController {

	public String execute(HttpServletRequest request) {
		System.out.println("GoodsController.execute() -----");
		// 이동할 페이지 저장할 변수 선언/초기화
		String jsp = null;
		
		// 결과저장변수 선언 및 초기화
		Object result = null;
		
		// 메뉴를 위한 uri
		String uri = request.getRequestURI();
		
		// Login 정보
		HttpSession session = request.getSession();
		LoginVO loginVO = (LoginVO)session.getAttribute("login");
		String id = null;
		if (loginVO != null) {
			id = loginVO.getId();
		}
		
		try {
			switch(uri) {
			case "/goods/list.do":
				System.out.println("상품리스트 -----");
				// 서비스 실행
				result = Execute.execute(Init.get(uri), null);
				// 결과저장
				request.setAttribute("list", result);
				// 페이지이동
				jsp = "goods/list";
				// "/WEB-INF/views/goods/list.jsp"
				break;
			case "/goods/writeForm.do":
				System.out.println("상품등록 폼 -----");
				jsp = "goods/writeForm";
				// "/WEB-INF/views/goods/writeForm.jsp"
				break;
			case "/goods/write.do":
				System.out.println("상품등록 처리 -----");
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
		
		// 이동할 페이지 return
		return jsp;
	}
}
