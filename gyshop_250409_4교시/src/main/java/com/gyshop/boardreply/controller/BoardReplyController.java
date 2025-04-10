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
		
		// TEST를 위한 로그인 정보 입력 시작 ===
		LoginVO loginVO = new LoginVO();
		loginVO.setId("kim");
		String id = loginVO.getId();
		// TEST를 위한 로그인 정보 입력 끝 ===
		
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
			default:
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		// 이동할 주소또는 로딩할 jsp파일경로 리턴
		return jsp;
	}
}
