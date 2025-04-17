package com.gyshop.goods.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gyshop.main.controller.Init;
import com.gyshop.member.vo.LoginVO;
import com.gyshop.util.exe.Execute;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

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
		
		// 파일 업로드 위한 설정
		String savePath = "/upload/goods";
		// => 웹브러우저 주소창 기준의 폴더 (프런트)
		String realSavePath
			= request.getServletContext().getRealPath(savePath);
		// 컴퓨터의 실제 경로 (하드웨드)
		System.out.println("실제경로: " + realSavePath);
		// 파일 최대 용량 설정
		int sizeLimit = 200 * 1024 * 1024; // 200MB
		// 컴퓨터에 폴더가 없으면 새로 만들어 줍니다.
		File realSavePathFile = new File(realSavePath);
		if (!realSavePathFile.exists()) {
			realSavePathFile.mkdir();
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
				MultipartRequest multi = 
					new MultipartRequest(
						request,// HttpServletRequest
						realSavePath, // 서버실제경로(컴퓨터경로)
						sizeLimit,	// 파일전체size 최대값
						"utf-8",	// 인코팅
						new DefaultFileRenamePolicy());
						// 중복파일처리
				String photo = multi.getFilesystemName("photo");
				String subPhoto1 = multi.getFilesystemName("subPhoto1");
				String subPhoto2 = multi.getFilesystemName("subPhoto2");
				String subPhoto3 = multi.getFilesystemName("subPhoto3");
				String subPhoto4 = multi.getFilesystemName("subPhoto4");
				System.out.println("photo: " + photo);
				System.out.println("subPhoto1: " + subPhoto1);
				System.out.println("subPhoto2: " + subPhoto2);
				System.out.println("subPhoto3: " + subPhoto3);
				System.out.println("subPhoto4: " + subPhoto4);
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
