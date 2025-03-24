package com.gyshop.main.controller;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		String uri = request.getRequestURI();
		System.out.println("uri = " + uri);
		
		String jsp = null;
		
		jsp = "main/main";
		jsp = "WEB-INF/views/" + jsp + ".jsp";
		request.getRequestDispatcher(jsp).forward(request, response);
		
		
		
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("DispatcherServlet.init() --- 실행");
		
	}
	
	

}
