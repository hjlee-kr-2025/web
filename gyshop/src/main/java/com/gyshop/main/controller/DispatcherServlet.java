package com.gyshop.main.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("DispatcherServlet.init() --- 실행");

	}
	
	

}
