package com.gyshop.notice.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gyshop.main.controller.Init;
import com.gyshop.notice.vo.NoticeVO;
import com.gyshop.util.exe.Execute;
import com.gyshop.util.page.PageObject;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

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
				// 페이지 처리를 위한 정보를 만들고 넘겨줍니다.
				PageObject pageObject = PageObject.getInstance(request);
				
				// 서비스 실행 - 페이지처리를 위해서 pageObject 가 전달됩니다.
				result = Execute.execute(Init.get(uri), pageObject);
				
				// DB에서 넘어온 데이터를 담습니다.
				request.setAttribute("list", result);
				// 페이지처리를 위해서 pageObject로 request에 담습니다.
				request.setAttribute("pageObject", pageObject);
				
				// 리스트 jsp로 이동
				jsp = "notice/list";
				// "WEB-INF/views/notice/list.jsp"
				break;
			case "/notice/view.do":
				System.out.println("공지사항 글보기 -----");
				// 상세보기할 글번호 수집(request)->서비스로 넘어감
				Long no = Long.parseLong(request.getParameter("no"));
				Long inc = Long.parseLong(request.getParameter("inc"));
				// 서비스실행
				result = Execute.execute(Init.get(uri), new Long[] {no, inc});
				// DB에서 받은 데이터를 jsp 보내기 위해
				// request에 저장
				request.setAttribute("vo", result);
				// jsp 로딩
				jsp = "notice/view";
				// "/WEB-INF/views/notice/view.jsp"
				break;
			case "/notice/writeForm.do":
				System.out.println("공지사항 글쓰기 폼 -----");
				jsp = "notice/writeForm";
				// "/WEB-INF/views/notice/writeForm.jsp"
				break;
			case "/notice/write.do":
				System.out.println("공지사항 글쓰기 처리 -----");
				
				// 데이터를 받습니다.
				MultipartRequest multi
					= new MultipartRequest(request,
							realSavePath, sizeLimit,
							"utf-8",
							new DefaultFileRenamePolicy());
				// MultipartRequest 클래스는 cos 라이브러리 안에 구현되어있습니다.
				// 사용전 프로젝트 lib폴더에 cos.jar파일을 포함시켜야 합니다.
				
				/* form 태그에 enctype="multipart/form-data"를 사용하면
				 * 데이터 전송시 2진(binary)으로 전송됩니다.(텍스트가 아님)
				 * MultipartReuest 클래스를 통해서 바이너리파일이 우리가 볼수 있는
				 * 텍스트 형태로 구성을 해 줍니다.
				*/
				// 서비스로 넘어가는 데이터를 수집
				String title = multi.getParameter("title");
				String content = multi.getParameter("content");
				String image = multi.getFilesystemName("imageFile");
				String startDate = multi.getParameter("startDate");
				String endDate = multi.getParameter("endDate");
				NoticeVO vo = new NoticeVO();
				vo.setTitle(title);
				vo.setContent(content);
				if (image != null && !image.equals("")) {
					vo.setImage(savePath + "/" + image);
				}
				vo.setStartDate(startDate);
				vo.setEndDate(endDate);
				
				// 서비스를 실행합니다.
				result = Execute.execute(Init.get(uri), vo);
				
				// 결과를 확인합니다.
				if ((Integer)result != 0) {
					session.setAttribute("msg",
						"공지사항이 등록되었습니다.");
				}
				
				// 공지사항 리스트로 이동합니다.
				jsp = "redirect:list.do";
				break;
			case "/notice/updateForm.do":
				System.out.println("공지사항 수정 폼 -----");
				// 데이터를 가져와서 request에 담고
				// updateForm.jsp 파일을 로딩합니다.
				// 수정할 글번호 받기
				no = Long.parseLong(request.getParameter("no"));
				// 글번호로 기존데이터 받기
				result = Execute.execute(Init.get("/notice/view.do"),
						new Long[] {no, 0L});
				request.setAttribute("vo", result);
				jsp = "notice/updateForm";
				// "/WEB-INF/views/notice/updateForm.jsp"
				break;
			case "/notice/update.do":
				System.out.println("공지사항 수정 처리 -----");
				
				multi = new MultipartRequest(request,
							realSavePath, sizeLimit, "utf-8",
							new DefaultFileRenamePolicy());
				
				no = Long.parseLong(multi.getParameter("no"));
				title = multi.getParameter("title");
				content = multi.getParameter("content");
				image = multi.getFilesystemName("imageFile");
				startDate = multi.getParameter("startDate");
				endDate = multi.getParameter("endDate");
				String deleteImage = multi.getParameter("image");
				vo = new NoticeVO();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				if (image != null && !image.equals("")) {
					vo.setImage(savePath + "/" + image);
				}
				else {
					vo.setImage(deleteImage);
				}
				vo.setStartDate(startDate);
				vo.setEndDate(endDate);
				// 서비스 실행
				result = Execute.execute(Init.get(uri), vo);
				if ((Integer)result == 0L) {
					// 수정이 실패
					image = vo.getImage();
					// upload한 파일을 지웁니다.
					File deleteFile =
						new File(request.getServletContext().getRealPath(image));
					if (deleteFile.exists()) deleteFile.delete();
					session.setAttribute("msg",
						"공지사항 수정이 실패했습니다. 다시 확인하시고 시도해주세요");
				}
				else {
					System.out.println("image :" + image + "--");
					System.out.println("deleteImage :" + deleteImage + "--");
					// 새로올린 이미지가 있을때만 기존이미지를 지우도록 합니다.
					if (image != null && !image.equals("")) {
						File deleteFile =
								new File(request.getServletContext()
									.getRealPath(deleteImage));
							if (deleteFile.exists()) deleteFile.delete();
					}
					session.setAttribute("msg",
						"공지사항이 수정되었습니다.");
				}
				jsp = "redirect:view.do?no="+no+"&inc=0";
				break;
			case "/notice/delete.do":
				System.out.println("공지사항 글삭제 -----");
				// 삭제에 필요한 글번호가 request에 담겨져 이곳으로 넘어옵니다.
				no = Long.parseLong(request.getParameter("no"));
				// 글번호를 서비스로 넘기고 실행합니다.
				result = Execute.execute(Init.get(uri), no);
				// 처리결과를 화면에 표시
				if ((Integer)result == 0) {
					session.setAttribute("msg",
						"공지사항이 삭제되지 않았습니다. 다시 시도해 주세요.");
				}
				else {
					session.setAttribute("msg", 
						"공지사항이 삭제되었습니다.");
				}
				// 공지사항 리스트로 이동
				jsp = "redirect:list.do";
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
		
		// 이동할 경로 리턴
		return jsp;
	}
}
