package com.gyshop.image.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gyshop.image.vo.ImageVO;
import com.gyshop.main.controller.Init;
import com.gyshop.member.vo.LoginVO;
import com.gyshop.util.exe.Execute;
import com.gyshop.util.page.PageObject;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

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
				// 페이지 관련 정보 수집
				PageObject pageObject = PageObject.getInstance(request);
				if (pageObject.getPerPageNum() == 10) {
					pageObject.setPerPageNum(6L);
					// Gallery 게시판 리스트는 한줄에 3개씩 구성되어있어서
					// 초기값을 6으로 사용했습니다.
					// 만약에 10이면 일반게시판의 초기값이어서 이미지게시판 초기값으로
					// 변경합니다.
				}
				// 데이터 세팅 (서비스로 넘길 데이터)
				// 서비스실행
				result = Execute.execute(Init.get(uri), pageObject);
				// 결과 담기 (jsp에 사용할 데이터, request에 저장한다)
				request.setAttribute("list", result);
				request.setAttribute("pageObject", pageObject);
				// 페이지이동 (로딩할 jsp파일 설정)
				jsp = "image/list";
				// "/WEB-INF/views/image/list.jsp"
				break;
			case "/image/view.do":
				System.out.println("이미지게시판 글보기 -----");
				// 데이터수집
				Long no = Long.parseLong(request.getParameter("no"));
				Long inc = Long.parseLong(request.getParameter("inc"));
				// 서비스로 넘어갈 데이터 세팅 (service 파라메터에서 바로 만들어주겠습니다.)
				// 서비스 실행
				result = Execute.execute(Init.get(uri),
						new Long[] {no, inc});
				// 서비스로 받은 데이터를 request에 저장합니다.
				request.setAttribute("vo", result);
				// jsp파일 로딩
				jsp = "image/view";
				// "/WEB-INF/views/image/view.jsp"
				break;
			case "/image/writeForm.do":
				System.out.println("이미지게시판 글등록 폼 -----");
				jsp = "image/writeForm";
				// "/WEB-INF/views/image/writeForm.jsp"
				break;
			case "/image/write.do":
				System.out.println("이미지게시판 글등록 처리 -----");
				// 파일이 있으면 MultipartRequest를 사용합니다.
				// MultipartRequest()생성자 파라매터
				// 1. request (HTTPServletRequest 자료형)
				// 2. 파일이 저장될 실제(서버의 실제)경로
				// 3. 파일사이즈 (MAX)
				// 4. encoding (한글지원, utf-8)
				// 5. 중복파일처리 : new DefaultFileRenamePolicy()
				// 				는 파일이 중복되면 파일이름뒤에 숫자를 붙입니다.
				MultipartRequest multi =
					new MultipartRequest(request, realSavePath,
							sizeLimit, "utf-8",
							new DefaultFileRenamePolicy());
				// => 파일이 서버에 저장됩니다.
				
				// 데이터수집
				String title = multi.getParameter("title");
				String content = multi.getParameter("content");
				String fileName = multi.getFilesystemName("imageFile");
				// => writeForm.jsp에서 위 3가지 데이터를 받아옵니다.
				
				// 서비스로 넘어갈 데이터를 세팅
				ImageVO vo = new ImageVO();
				vo.setTitle(title);
				vo.setContent(content);
				vo.setFileName(savePath + "/" +fileName);
				// 파일은 경로를 포함하여 DB에 저장
				vo.setId(id);// session안의 login(LoginVO)에서 받아온 id변수를 사용
				
				// 서비스 실행
				result = Execute.execute(Init.get(uri), vo);
				
				if ((Integer)result != 0L) {
					 session.setAttribute("msg", 
						"이미지글이 등록되었습니다.");
				}
				// gallery list로 이동
				jsp = "redirect:list.do";
				// "/image/list.do"
				break;
			case "/image/updateForm.do":
				System.out.println("이미지게시판 수정 폼 -----");
				// 수정되는 글번호 수집
				// updateForm.do?no=? 로 url에서 정보가 넘어옵니다.
				no = Long.parseLong(request.getParameter("no"));
				// 기존 데이터를 화면에 표시
				// 표시를 위해서 DB에서 정보를 가져옵니다. ImageViewService
				result = Execute.execute(Init.get("/image/view.do"),
						new Long[] {no, 0L});
				// ==> 글번호와 조회수가 증가안되도록 0값을 array로 전달합니다.
				// 결과값을 request에 담아서 jsp로 전달합니다.
				request.setAttribute("vo", result);
				// jsp 로딩
				jsp = "image/updateForm";
				// "/WEB-INF/views/image/updateForm.jsp"
				break;
			case "/image/update.do":
				System.out.println("이미지게시판 수정 처리 -----");
				// 수정되는 데이터 수집
				multi =	new MultipartRequest(request, realSavePath,
								sizeLimit, "utf-8",
								new DefaultFileRenamePolicy());
				no = Long.parseLong(multi.getParameter("no"));
				title = multi.getParameter("title");
				content = multi.getParameter("content");
				fileName = multi.getFilesystemName("imageFile");
				String deleteFileName = multi.getParameter("deleteFile");
				// 서비스로 넘어갈 데이터 세팅
				vo = new ImageVO();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setFileName(savePath + "/" + fileName);// 경로와 함께
				vo.setId(id);// 로그인한 id
				// 서비스 실행
				result = Execute.execute(Init.get(uri), vo);
				// 결과 확인
				if ((Integer)result != 0) {
					session.setAttribute("msg",
						"게시글이 수정되었습니다.");
					if (fileName != null && !fileName.equals("")) {
						File deleteFile =
							new File(request.getServletContext()
									.getRealPath(deleteFileName));
						if (deleteFile.exists()) deleteFile.delete();
					}
				}
				else {
					// 수정이 실패했을때는 새로 올린 파일을 삭제합니다.
					fileName = vo.getFileName();// 경로와 함께 세팅
					File deleteFile =
							new File(request.getServletContext()
									.getRealPath(fileName));
					if (deleteFile.exists()) deleteFile.delete();
					session.setAttribute("msg",
							"게시글이 수정되지 않았습니다. 확인후 시도해주세요.");
				}
				// 글보기로 이동
				jsp = "redirect:view.do?no=" + no + "&inc=0";
				break;
			case "/image/delete.do":
				System.out.println("이미지게시판 삭제 처리 -----");
				// 삭제되는 글번호 수집
				no = Long.parseLong(request.getParameter("no"));
				// 삭제되는 이미지 경로 수집
				deleteFileName = request.getParameter("deleteFile");
				// DB의 image 테이블안에 데이터 1개를 삭제합니다.
				// 웹서버와는 별개입니다
				
				// 서비스로 넘어가는 데이터 세팅
				vo = new ImageVO();
				vo.setNo(no);
				vo.setId(id);
				// 서비스 실행
				result = Execute.execute(Init.get(uri), vo);
				if ((Integer)result != 0) {
					session.setAttribute("msg", 
							"게시글이 삭제되었습니다.");
					File deleteFile =
							new File(request.getServletContext()
									.getRealPath(deleteFileName));
					if (deleteFile.exists()) deleteFile.delete();
					// 웹서버안에 저장되어있는 이미지를 지웁니다.
					jsp = "redirect:list.do";
				}
				else {
					session.setAttribute("msg",
							"게시글이 삭제되지 않았습니다. 확인하시고 시도해주세요.");
					jsp = "redirect:view.do?no=" + no + "&inc=0";
				}
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
