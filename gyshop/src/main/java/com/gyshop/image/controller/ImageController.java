package com.gyshop.image.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gyshop.main.controller.Init;
import com.gyshop.member.vo.LoginVO;
import com.gyshop.util.exe.Execute;

public class ImageController {

	public String execute(HttpServletRequest request) {
		System.out.println("ImageController.execute() -----");
		// 이동할 페이지 저장 변수 선언 및 초기화
		String jsp = null;
		
		// 결과저장변수 선언
		Object result = null;
		
		// 메뉴확인
		String uri = request.getRequestURI();
		
		// 로그인 확인하고 id를 가져옵니다.
		HttpSession session = request.getSession();
		LoginVO loginVO = (LoginVO)session.getAttribute("login");
		String id = null;
		if (loginVO != null) id = loginVO.getId();
		
		// 파일업로드를 위한 설정
		String savePath = "/upload/image";
		String realSavePath = 
				request.getServletContext().getRealPath(savePath);
		// 파일용량제한
		int sizeLimit = 100 * 1024 * 1024;//100MB
		File realSavPathFile = new File(realSavePath);
		// 폴더가 존재하지 않으면 만들어 줍니다.
		if (!realSavPathFile.exists()) {
			realSavPathFile.mkdir();
		}
		
		try {
			// 메뉴처리: CRUD(Create, Read, Update, Delete)
			// DB-SQL(insert, select, update, delete)
			switch(uri){
			case "/image/list.do":
				System.out.println("이미지게시판 리스트 ----");
				// 웹브라우저에서 주소이동후 구현되는 프로그램구현순서
				// 데이터 가져오기 (request)
				// 데이터 세팅 (서비스로 넘길 데이터)
				// 서비스실행
				result = Execute.execute(Init.get(uri), null);
				// 결과 담기 (jsp에 사용할 데이터, request에 저장한다)
				request.setAttribute("list", result);
				// 페이지이동 (로딩할 jsp파일 설정)
				jsp = "image/list";
				// "/WEB-INF/views/image/list.jsp"
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
		
		// 이동할 페이지 리턴
		return jsp;
	}
}
