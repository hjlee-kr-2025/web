package com.gyshop.notice.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gyshop.main.controller.Init;
import com.gyshop.util.exe.Execute;

public class NoticeController {

	public String execute(HttpServletRequest request) {
		// 페이지이동변수 선언 및 초기화
		String jsp = null;
		System.out.println("NoticeController.execute() -----");
		
		// 세션정보 가져오기
		HttpSession session = request.getSession();
		
		// 메뉴를 위한 uri
		String uri = request.getRequestURI();
		
		// 결과 저장용 변수
		Object result = null;
		
		/* 파일 업로드를 위한 설정
		 * */
		// upload 폴더가 미리 만들어져 있어서 /upload/notice로 설정
		String savePath = "/upload/notice";
		String realSavePath
			= request.getServletContext().getRealPath(savePath);
		// 파일업로드 제한
		int sizeLimit = 20 * 1024 * 1024; // 20MBytes
		// 실제경로를 File객체에 연결
		File realSavePathFile = new File(realSavePath);
		if (!realSavePathFile.exists()) {
			// notice 폴더가 존재하지 않으면 만들어 줍니다.
			realSavePathFile.mkdir();
		}
		
		try {
			switch(uri) {
			case "/notice/list.do":
				System.out.println("공지사항 리스트 ---");
				// 서비스로 넘길 데이터 수집
				// 리스트는 현재는 없습니다.
				
				// 서비스 실행
				result = Execute.execute(Init.get(uri), null);
				
				// DB에서 넘어온 데이터를 담습니다.
				request.setAttribute("list", result);
				// 리스트 jsp로 이동
				jsp = "notice/list";
				// "WEB-INF/views/notice/list.jsp"
				break;
			case "/notice/writeForm.do":
				System.out.println("공지사항 글쓰기 폼 -----");
				jsp = "notice/writeForm";
				// "/WEB-INF/views/notice/writeForm.jsp"
				break;
			case "/notice/write.do":
				System.out.println("공지사항 글쓰기 처리 -----");
				break;
			default:
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		// 이동할 경로 리턴
		return jsp;
	}
}
