package com.gyshop.board.controller;

import javax.servlet.http.HttpServletRequest;

import com.gyshop.board.vo.BoardVO;
import com.gyshop.main.controller.Init;
import com.gyshop.util.exe.Execute;

public class BoardController {

	public String execute(HttpServletRequest request) {
		System.out.println("** BoardController.execute()");
		// 이동할 jsp 주소를 담아놓을 변수
		String jsp = null;
		
		// 메뉴입력(uri)
		String uri = request.getRequestURI();
		// 데이터 수집을 위한 객체 선언 :
		// 초기값 null : 실행이 정상으로 끝났는지 확인하기 위해
		Object result = null;
		
		try {
			switch (uri) {
			case "/board/list.do":
				System.out.println("1. 일반게시판 리스트");
				System.out.println("웹브라우저 주소창에 "
					+ "localhost/board/list.do 를 실행했을때");
				
				// 서비스로 넘어가는 데이터 세팅
				// 리스트에는 없습니다.
				
				// 서비스를 실행하고 결과를 리턴받습니다.
				result = Execute.execute(Init.get(uri), null);
				
				// 결과 데이터를 request에 담습니다.
				request.setAttribute("list", result);
				// => jsp로 데이터를 전달하기 위해서
				
				jsp = "board/list";
				// "/WEB-INF/views/board/list.jsp" 파일을 웹에 로딩합니다.
				break;
			case "/board/view.do":
				System.out.println("2. 일반게시판 글보기");
				
				// 서비스로 전달하는 데이터 세팅
				Long no = Long.parseLong(request.getParameter("no"));
				Long inc = Long.parseLong(request.getParameter("inc"));
				// 주소창으로 넘어온 변수값을 java에서 받는 방법은
				// request.getParameter() 메서드를 이용합니다.
				// request.getParameter()의 리턴값은 String이므로
				// 적절한 자료형으로 변환하여 사용합니다.
				
				// 서비스를 실행 : 전달데이터 Long[] - 글번호, 조회수증가변수
				result = Execute.execute(Init.get(uri),
						new Long[] {no, inc});
				
				// DB에서 가져온 데이터를 담는다.
				request.setAttribute("vo", result);
				
				// jsp 파일로 이동
				jsp = "board/view";
				break;
			case "/board/writeForm.do":
				System.out.println("3-1. 일반게시판 글씨기 폼");
				jsp = "board/writeForm";
				break;
			case "/board/write.do":
				System.out.println("3-2. 일반게시판 글쓰기 처리");

				System.out.println(request);
				// 서비스로 전달할 데이터 세팅
				BoardVO vo = new BoardVO();
				vo.setTitle(request.getParameter("title"));
				vo.setContent(request.getParameter("content"));
				vo.setWriter(request.getParameter("writer"));
				vo.setPw(request.getParameter("pw"));
				// request.getParameter() 로 웹에서 입력한 데이터를
				// 전달 받습니다.
				// ""안에 들어가는 값은 name 속성에 적힌 값입니다.
				
				// 서비스 실행
				result = Execute.execute(Init.get(uri), vo);
				
				// 실행결과 확인
				jsp = "redirect:list.do";
				break;
			case "/board/updateForm.do":
				System.out.println("4-1. 일반게시판 글수정 폼");
				// 1. 수정할 글번호 수집
				no = Long.parseLong(request.getParameter("no"));
				// 2. 글번호로 수정할 데이터 DB에서 가져오기
				result = Execute.execute(Init.get("/board/view.do"),
						new Long[] {no, 0L});
				// 3. 웹페이지로 넘어갈 데이터 세팅
				request.setAttribute("vo", result);
				// 4. jsp페이지로 이동
				jsp = "board/updateForm";
				break;
			case "/board/update.do":
				System.out.println("4-2. 일반게시판 글수정 처리");
				
				break;
			default:
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		// 이동할 주소 리턴
		return jsp;
	}	// end of execute(HttpServletRequest request)
}


