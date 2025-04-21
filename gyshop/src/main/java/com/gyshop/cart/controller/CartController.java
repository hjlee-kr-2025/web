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
			case "/cart/loginForm.do":
				System.out.println("장바구니클릭시 로그인화면 -----");
				session.setAttribute("msg",
					"로그인이 필요합니다.");
				jsp = "redirect:/member/loginForm.do";
				break;
			case "/cart/list.do":
				System.out.println("장바구니리스트 -----");
				// 프런트에서 넘어오는 데이터는 별도로 없습니다.
				// 서비스로 넘어가는 데이터는 로그인 id필요로 하는데
				// 미리 세팅되어있습니다.
				// 서비스 실행
				result = Execute.execute(Init.get(uri), id);
				// 결과를 받아서 request에 저장 -> JSP로 넘어갑니다.
				request.setAttribute("list", result);
				// JSP 로딩
				jsp = "cart/list";
				// "/WEB-INF/views/cart/list.jsp"
				break;
			case "/cart/write.do":
				System.out.println("장바구니등록 -----");
				// 데이터수집
				// GoodsVO의 no값을 받어서 CartVO의 gno를 세팅합니다.
				Long gno = Long.parseLong(request.getParameter("no"));
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
			case "/cart/update.do":
				System.out.println("상품수량변경 -----");
				// 필요한 데이터 수집 (cart no, count)
				Long no = Long.parseLong(request.getParameter("no"));
				count = Integer.parseInt(request.getParameter("count"));
				// 서비스로 넘어갈 데이터 세팅
				vo = new CartVO();
				vo.setNo(no);
				vo.setCount(count);
				// 서비스 실행
				result = Execute.execute(Init.get(uri), vo);
				// CartUpdateService()
				// 수정후 리스트로 이동
				jsp = "redirect:list.do";
				// id는 session에 보관되어있기 때문에 안붙여서 주소값을 적어도 됩니다.
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
