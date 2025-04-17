package com.gyshop.goods.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gyshop.goods.vo.GoodsVO;
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
			case "/goods/view.do":
				System.out.println("상품 상세보기 -----");
				// 서비스로 넘어갈 자료를 수집
				Long no = Long.parseLong(request.getParameter("no"));
				// 서비스 실행
				result = Execute.execute(Init.get(uri), no);
				// 실행후 결과값(DB에서 가져온 데이터)를 jsp로 넘깁니다.
				request.setAttribute("vo", result);
				// jsp 파일 로딩
				jsp = "goods/view";
				// "/WEB-INF/views/goods/view.jsp"
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
				String name = multi.getParameter("name");
				String content = multi.getParameter("content");
				String modelNo = multi.getParameter("modelNo");
				Integer price = Integer.parseInt(multi.getParameter("price"));
				Integer delivery_cost
					= Integer.parseInt(multi.getParameter("delivery_cost"));
				// 서비스에 전송하기위해 GoodsVO 에 담습니다.
				GoodsVO vo = new GoodsVO();
				vo.setName(name);
				vo.setContent(content);
				vo.setPhoto(savePath + "/" + photo);
				if (subPhoto1 != null) vo.setSubPhoto1(savePath + "/" + subPhoto1);
				if (subPhoto2 != null) vo.setSubPhoto2(savePath + "/" + subPhoto2);
				if (subPhoto3 != null) vo.setSubPhoto3(savePath + "/" + subPhoto3);
				if (subPhoto4 != null) vo.setSubPhoto4(savePath + "/" + subPhoto4);
				vo.setPrice(price);
				vo.setDelivery_cost(delivery_cost);
				vo.setModelNo(modelNo);
				// 서비스실행
				result = Execute.execute(Init.get(uri), vo);
				// uri="/goods/write.do" --> GoodsWriteService 가 실행
				if ((Integer)result == 0) {
					// 상품등록이 실패했습니다.
					session.setAttribute("msg",
							"상품이 등록되지 않았습니다. 확인 후 다시 시도해주세요");
					// 서버로 올라간 파일을 삭제
					String fileName = vo.getPhoto();
					File deleteFile = new File(request.getServletContext()
							.getRealPath(fileName));
					// 파일이 실제 경로와 이름으로 deleteFile에 넣습니다.
					if (deleteFile.exists()) deleteFile.delete();
					
					fileName = vo.getSubPhoto1();
					deleteFile = new File(request.getServletContext()
							.getRealPath(fileName));
					// 파일이 실제 경로와 이름으로 deleteFile에 넣습니다.
					if (deleteFile.exists()) deleteFile.delete();
					
					fileName = vo.getSubPhoto2();
					deleteFile = new File(request.getServletContext()
							.getRealPath(fileName));
					// 파일이 실제 경로와 이름으로 deleteFile에 넣습니다.
					if (deleteFile.exists()) deleteFile.delete();
					
					fileName = vo.getSubPhoto3();
					deleteFile = new File(request.getServletContext()
							.getRealPath(fileName));
					// 파일이 실제 경로와 이름으로 deleteFile에 넣습니다.
					if (deleteFile.exists()) deleteFile.delete();
					
					fileName = vo.getSubPhoto4();
					deleteFile = new File(request.getServletContext()
							.getRealPath(fileName));
					// 파일이 실제 경로와 이름으로 deleteFile에 넣습니다.
					if (deleteFile.exists()) deleteFile.delete();
				}
				else {
					session.setAttribute("msg",
							"상품이 등록되었습니다.");
				}
				// 상품리스트로 이동합니다.
				jsp = "redirect:list.do";
				// "/goods/list.do" 로 페이지 이동합니다.
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
