package com.shopdb.board.controller;

import javax.servlet.http.HttpServletRequest;

import com.shopdb.board.vo.BoardVO;
import com.shopdb.main.controller.Init;
import com.shopdb.util.Execute;

// 일반게시판 메뉴 선택, 데이터 수집
public class BoardController {

	public String execute(HttpServletRequest request) {
		// 이동할 주소를 담을 변수
		String jsp = null;
		
		// 메뉴입력 (uri)
		String uri = request.getRequestURI();
		
		// 데이터 수집을 위한 객체 선언
		Object result = null;
		
		try {
			switch (uri) {
			case "/board/list.do":
				System.out.println("1. 일반게시판 리스트");
				
				// 서비스 실행
				// uri key값으로 service 객체를 가져옵니다.
				result = Execute.execute(Init.get(uri), null);
				
				// DB에서 실행한 결과가 result에 담깁니다.
				// result 를 front 에 출력하기 위해 request에 담습니다.
				request.setAttribute("list", result);
				
				jsp = "board/list";
				//=> DispatcherServlet 에서
				// "/WEB-INF/views/board/list.jsp"
				break;
			case "/board/view.do":
				System.out.println("2. 일반게시판 글보기");
				// 정보수집
				Integer no = Integer.parseInt(request.getParameter("no"));
				Integer inc = Integer.parseInt(request.getParameter("inc"));
				// 서비스실행
				result = Execute.execute(Init.get(uri), new Integer[] {no, inc});
				// DB에서 받은 데이터를 프론트로 전달하기 위해
				// request에 담습니다.
				request.setAttribute("vo", result);
				jsp = "board/view";
				// ===> /WEB-INF/views/board/view.jsp
				break;
			case "/board/writeForm.do":
				System.out.println("3-1. 일반게시판 글쓰기 폼");
				jsp = "board/writeForm";
				break;
			case "/board/write.do":
				System.out.println("3-2. 일반게시판 글쓰기 처리");
				
				// 데이터 수집(writeForm에서 작성한) : 제목, 내용, 작성자, 비밀번호
				// request에 담겨져서 이곳으로 넘어옵니다.
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				String writer = request.getParameter("writer");
				String pw = request.getParameter("pw");
				
				// 입력받은 값을 BoardVO 에 담습니다. (DB에 넘겨주기위해)
				BoardVO vo = new BoardVO();
				vo.setTitle(title);
				vo.setContent(content);
				vo.setWriter(writer);
				vo.setPw(pw);
				
				// 서비스 실행
				result = Execute.execute(Init.get(uri), vo);
				
				jsp = "redirect:list.do";
				break;
			case "/board/updateForm.do":
				System.out.println("4-1. 일반게시판 글수정 폼");
				// 정보수집
				no = Integer.parseInt(request.getParameter("no"));
				// 서비스실행
				// uri 와 실행해야할 서비스가 매칭이 되지 않기 때문에
				// ""(문자열) 로 지정된 서비스를 찾도록 적어줍니다.
				result = Execute.execute(Init.get("/board/view.do"),
					new Integer[] {no, 0});
				// DB에서 받은 데이터를 프론트로 전달하기 위해
				// request에 담습니다.
				request.setAttribute("vo", result);
				jsp = "board/updateForm";
				// ===> /WEB-INF/views/board/updateForm.jsp
				break;
			case "/board/update.do":
				System.out.println("4-2. 일반게시판 글수정 처리");
				
				// 데이터 수집(updateForm에서 작성한)
				// : 글번호, 제목, 내용, 작성자, 비밀번호
				// request에 담겨져서 이곳으로 넘어옵니다.
				no = Integer.parseInt(request.getParameter("no"));
				title = request.getParameter("title");
				content = request.getParameter("content");
				writer = request.getParameter("writer");
				pw = request.getParameter("pw");
				
				// 입력받은 값을 BoardVO 에 담습니다. (DB에 넘겨주기위해)
				vo = new BoardVO();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setWriter(writer);
				vo.setPw(pw);
				
				// 서비스 실행
				result = Execute.execute(Init.get(uri), vo);
				
				jsp = "redirect:view.do?no="+no+"&inc=0";
				break;
			case "/board/delete.do":
				break;
			default:
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return jsp;
	}
}
