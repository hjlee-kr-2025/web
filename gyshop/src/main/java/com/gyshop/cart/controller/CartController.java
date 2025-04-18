package com.gyshop.cart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gyshop.cart.vo.CartVO;
import com.gyshop.main.controller.Init;
import com.gyshop.member.vo.LoginVO;
import com.gyshop.util.exe.Execute;

public class CartController {

	public String execute(HttpServletRequest request) {
		System.out.println("CartController.execute() -----");
		// 이동할 주소 또는 로딩할 페이지를 저장하는 변수 선언, 초기화
		String jsp = null;
		
		// 로그인 정보가 필요합니다.
		HttpSession session = request.getSession();
		LoginVO loginVO = (LoginVO)session.getAttribute("login");
		// 아이디저장
		String id = null;
		if (loginVO != null) {
			id = loginVO.getId();
		}
		
		// 결과저장변수 선언 및 초기화
		Object result = null;
		
		// 메뉴체크(uri)
		String uri = request.getRequestURI();
		
		try {
			switch(uri) {
			case "/cart/write.do":
				System.out.println("장바구니등록 -----");
				// 데이터수집
				Long gno = Long.parseLong(request.getParameter("gno"));
				Integer count = Integer.parseInt(request.getParameter("count"));
				// 서비스로 넘어갈 데이터 세팅
				CartVO vo = new CartVO();
				vo.setGno(gno);
				vo.setCount(count);
				vo.setId(id);
				// 서비스를 실행
				result = Execute.execute(Init.get(uri), vo);
				if ((Integer)result != 0) {
					session.setAttribute("msg",
						"장바구니에 등록되었습니다.");
					jsp = "redirect:list.do";
				}
				else {
					jsp = "redirect:/goods/list.do";
				}
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
		
		
		// 이동할 주소 또는 페이지 리턴
		return jsp;
	}
}
