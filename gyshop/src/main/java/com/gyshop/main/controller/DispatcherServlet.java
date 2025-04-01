package com.gyshop.main.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyshop.ajax.controller.AjaxController;
import com.gyshop.board.controller.BoardController;
import com.gyshop.member.controller.GradeController;
import com.gyshop.member.controller.MemberController;

/* 1. webserver 가 로딩될때 init()가 실행되도록 구성
 * 		(web.xml에 load-on-startup 를 1로 세팅)
 * 		벡엔드에서 사용할 모든 자원의 초기화를 이곳에서 진행합니다.
 * 2. 웹브라우저의 주소를 입력해서 페이지 이동시 service() 메서드가 실행
 * 		(단, web.xml 의 url-pattern에 맞는 주소만)
 */
public class DispatcherServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/* 사용할 컨트롤러를 선언하고 생성합니다.
	 * 
	 */
	private BoardController boardController = new BoardController();
	private GradeController gradeController = new GradeController();
	private MemberController memberController = new MemberController();
	private AjaxController ajaxController = new AjaxController();
	

	/* [shift]+[alt]+[s] -> override/implements method 클릭
	 * -> service(HttpServletRequest, HttpServletResponse)와
	 * 	init(ServletConfit) 두가지 선택
	 * -> OK버튼 클릭
	 */
	@Override
	protected void service
		(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("DispatherServlet.service() --- 실행");
		
		/* webapp 경로가 "/"(root) 경로가 됩니다.
		 * 웹브라우저 주소창에서는 메인주소가 "/" 경로가 됩니다.
		 */
		String uri = request.getRequestURI();
		System.out.println("uri = " + uri);
		
		// uri : /module/기능 -> /board/list.do : 일반게시판 리스트
		// 두번째 "/"의 위치값을 저장합니다. 없으면 -1
		int pos = uri.indexOf("/", 1);
		// uri의 1번 인덱스(방) 부터 "/"를 찾습니다.
		
		if (pos == -1) {
			// 두번째 인덱스가 없다는 의미입니다.
			request.setAttribute("uri", uri);
			request.getRequestDispatcher("").forward(request, response);
			return;
		}
		
		// 모듈을 저장합니다.
		String module = uri.substring(0, pos);
		// 0~(pos-1) 까지 module에 저장
		
		String jsp = null; // 이동할 jsp 주소 초기화
		
		// 메뉴
		switch (module) {
		case "/main":// 테스트용도
			jsp = "main/main";
			break;
		case "/board":
			jsp = boardController.execute(request);
			break;
		case "/grade":
			jsp = gradeController.execute(request);
			break;
		case "/member":
			jsp = memberController.execute(request);
			break;
		case "/ajax":
			jsp = ajaxController.execute(request);
			break;
		default:
			request.setAttribute("uri", uri);
			request.getRequestDispatcher("").forward(request, response);
			return;
		}
		
		System.out.println("jsp = " + jsp);
		
		if (jsp.indexOf("redirect:") == 0) {
			// 웹브라우저 주소창에 새로운 url 입력 : 페이지의 이동
			jsp = jsp.substring("redirect:".length());
			//==> "redirect:xxx" 에서 xxx를 주소값으로 사용한다는 의미
			request.setAttribute("uri", jsp);
			response.sendRedirect(jsp);
		}
		else {
			// jsp 로 이동 (화면에 출력)
			jsp = "/WEB-INF/views/" + jsp + ".jsp";
			request.setAttribute("uri", jsp);
			request.getRequestDispatcher(jsp).forward(request, response);
		}
		
	}	// end of service()

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("DispatcherServlet.init() --- 실행");
		
		try {
			// 객체 생성 및 조립
			Class.forName("com.gyshop.main.controller.Init");
			// mysql jdbc 드라이버 확인
			Class.forName("com.gyshop.util.db.DB");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
