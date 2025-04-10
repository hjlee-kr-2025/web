package com.gyshop.boardreply.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gyshop.boardreply.vo.BoardReplyVO;
import com.gyshop.main.controller.Init;
import com.gyshop.member.vo.LoginVO;
import com.gyshop.util.exe.Execute;

public class BoardReplyController {

	public String execute(HttpServletRequest request) {
		System.out.println("BoardReplyController.execute() -----");
		// 이동할 주소저장할 변수 선언 및 초기화
		String jsp = null;
		
		// 로그인정보
		HttpSession session = request.getSession();
		
		// "/member/login.do" 에서 처리하고 session에 저장한 로그인정보를
		// 꺼내오는 부분입니다.
		LoginVO loginVO = (LoginVO)session.getAttribute("login");
		// loginVO에는 로그인을 했다면 6가지(id,pw,name,grade,gradeName,photo)
		// 값이 세팅이 됩니다.
		String id = null;
		if (loginVO != null) id = loginVO.getId();
		// 로그인한 사람이 글을 작성/수정/삭제하도록 했습니다.
		
		// 결과 담을 변수
		Object result = null;
		
		// 메뉴로 이동할 uri
		String uri = request.getRequestURI();
		
		try {
			switch(uri) {
			case "/boardreply/write.do":
				System.out.println("일반게시판 댓글 등록 처리 -----");
				
				// 데이터 수집 (board/view.jsp(form태그) -> request -> 이곳)
				Long no = Long.parseLong(request.getParameter("no"));
				String content = request.getParameter("content");
				//id = request.getParameter("id");
				// 서비스로 넘어갈 수 있도록 세팅
				BoardReplyVO replyVO = new BoardReplyVO();
				replyVO.setNo(no);
				replyVO.setContent(content);
				replyVO.setId(id);
				
				// 서비스 실행
				result = Execute.execute(Init.get(uri), replyVO);
				
				if ((Integer)result != 0) {
					session.setAttribute("msg",
						"댓글이 등록되었습니다.");
				}
				
				// view로 이동합니다.
				jsp = "redirect:/board/view.do?no=" + no + "&inc=0";
				break;
			case "/boardreply/update.do":
				System.out.println("일반게시판 댓글 수정 처리 -----");
				// 데이터 수집
				Long rno = Long.parseLong(request.getParameter("rno"));
				content = request.getParameter("content");
				no = Long.parseLong(request.getParameter("no"));
				// 일반게시판 글번호(no)는 원래 페이지로 이동하기 위해 수집했습니다.
				
				// id는 로그인한 아이디 사용
				replyVO = new BoardReplyVO();
				replyVO.setRno(rno);
				replyVO.setContent(content);
				replyVO.setId(id);
				// switch문 전에 session 에서 login 정보를 가져와
				// id를 미리 세팅해놨습니다.
				
				// 서비스 실행
				result = Execute.execute(Init.get(uri), replyVO);
				
				// 실행결과 확인
				if ((Integer)result != 0) {
					session.setAttribute("msg",
						"댓글이 수정되었습니다.");
				}
				
				// view로 이동합니다.
				jsp = "redirect:/board/view.do?no=" + no + "&inc=0";
				break;
			case "/boardreply/delete.do":
				System.out.println("일반게시판 댓글 삭제 처리 -----");
				// 데이터 수집
				rno = Long.parseLong(request.getParameter("rno"));
				// id는 로그인정보
				no = Long.parseLong(request.getParameter("no"));
				
				// rno, id : 댓글삭제를 위한 정보
				// no : 원래페이지로 이동하기 위한 정보
				replyVO = new BoardReplyVO();
				replyVO.setRno(rno);
				replyVO.setId(id);
				
				// 서비스 실행
				result = Execute.execute(Init.get(uri), replyVO);
				
				// 실행결과 확인
				if ((Integer)result != 0) {
					session.setAttribute("msg",
						"댓글이 삭제되었습니다.");
				}
				
				// 원래페이지로 이동
				jsp = "redirect:/board/view.do?no=" + no + "&inc=0";
				break;
			default:
				request.setAttribute("uri", uri);
				jsp = "error/noModule_404";
				// "/WEB-INF/views/error/noModule_404.jsp"
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		// 이동할 주소또는 로딩할 jsp파일경로 리턴
		return jsp;
	}
}
