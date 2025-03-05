package com.shopdb.main.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopdb.board.controller.BoardController;

// Web프로그램의 main메서드 역할을 합니다.
public class DispatcherServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Controller 선언과 생성 - 1번만 생성됩니다.
	private BoardController boardController = new BoardController();

	public void init(ServletConfig config) throws ServletException {
		// 서버가 시작될 때 한번만 실행되는 부분
		// DB 드라이버 확인 - DB클래스
		// 객체 생성 처리 - Init클래스
		System.out.println("DispatcherServlet.init() -- 초기화 진행 --");
		try {
			// 객체생성 초기화 + 조립
			Class.forName("com.shopdb.main.controller.Init");
			// mySQL DB 드라이버 확인 - DB클래스 static 메서드
			Class.forName("com.shopdb.util.DB");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	protected void service (HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		// 메뉴를 구성하여 출력하고, URI로 선택 처리
		System.out.println("DispatcherServlet.service() ---------");
		
		String uri = request.getRequestURI();
		System.out.println("uri : " + uri);
		
		// uri : /board/list.do
		// uri : /모듈/기능
		// 두번째 /의 위치값을 pos변수에 저장
		int pos = uri.indexOf("/", 1);
		System.out.println("pos : " + pos);
		
		if (pos == -1) {
			request.setAttribute("uri", uri);
			//서버에 전송시 key, value형태로 데이터를 넘겨줍니다.
			// key="uri" 에 uri변수에 담긴 string내용이 서버로 넘어갑니다.
			// 페이지 없음 처리
			return;
		}
		
		String module = uri.substring(0, pos);
		// 0번인텍스에서부터 pos-1인텍스까지 문자열이 return;
		System.out.println("module : " + module);
		
		String jsp = null;
		
		switch (module) {
		case "/board":
			jsp = boardController.execute(request);
			break;
		default:
			// 페이지 없음 처리
			request.setAttribute("uri", uri);
			return;
		}
		
		System.out.println("jsp : " + jsp);
		
		if (jsp.indexOf("redirect:") == 0) {
			// 페이지 이동
			// 이동을 위해 redirect: 문구를 제거
			jsp = jsp.substring("redirect:".length());
			request.setAttribute("uri", jsp);
			response.sendRedirect(jsp);
		}
		else {
			// jsp 문서 화면에 출력 (html)
			jsp = "/WEB-INF/views/" + jsp + ".jsp";
			request.setAttribute("uri", jsp);
			request.getRequestDispatcher(jsp)
				.forward(request, response);
		}
		
		
		
	}
}
