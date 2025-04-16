package com.a100.main.controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DispatcherServlet
 */
//@WebServlet("/DispatcherServlet")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("DispatcherServlet.init() -----");
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("DispatcherServlet.service() -----");
		
		
		String uri = request.getRequestURI();
		System.out.println("uri : " + uri);
		
		StringBuffer url = request.getRequestURL();
		System.out.println("url : " + url);
		
		String query = request.getQueryString();
		System.out.println("query : " + query);
		
		String jsp = null;
		
		switch(uri) {
		case "/list.do":
			// 어떤처리를 할것인가?
			// 리스트를 가져옵니다. -> DB / file / ...
			// 서비스 ->  DB 로 접근 -> 데이터를 가져옵니다.
			
			// index.jsp에 적은 값이 이곳으로 넘어옵니다.
			request.setCharacterEncoding("utf-8");// 한글처리를 위한 설정
			
			String name = request.getParameter("name");
			String id = request.getParameter("id");
			//---------------
			
			request.setAttribute("name", name);
			request.setAttribute("id", id);
			jsp = "/WEB-INF/views/list.jsp";
			break;
		case "/view.do":
			break;
		}
		
		// jsp파일을 로딩하는 명령
		request.getRequestDispatcher(jsp).forward(request, response);
		
	}



}
