package com.gyshop.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gyshop.main.controller.Init;
import com.gyshop.member.vo.GradeVO;
import com.gyshop.util.exe.Execute;

public class GradeController {

	public String execute(HttpServletRequest request) {
		// 이동할 주소를 담는 변수 - 리턴으로사용
		String jsp = null;
		System.out.println("GradeController.execute() ---------");
		
		// session 선언
		HttpSession session = request.getSession();
				
		// 메뉴를 받습니다.
		String uri = request.getRequestURI();
		// ==> getRequestURI() 는 메인주소를 제외한 나머지 주소 리턴
		
		// DB처리후 리턴받는 데이터 수집하는 변수
		Object result = null;
		
		try {
			switch(uri) {
			case "/grade/list.do":
				System.out.println("1. 회원등급 리스트");
				// 서비스로 넘어가는 데이터
				// list는 없습니다.
				// 서비스 실행 및 결과 리턴받기
				result = Execute.execute(Init.get(uri), null);
				// 받은 데이터를 request에 담아서 jsp로 전달합니다.
				request.setAttribute("list", result);
				// jsp 로 이동
				jsp = "grade/list";
				// "/WEB-INF/views/grade/list.jsp"
				break;
			case "/grade/write.do":
				// 회원등급리스트 -> "등록" 버튼클릭
				// -> 데이터입력 -> 모달창 "등록" 버튼클릭
				// 서비스로 넘어갈 데이터를 수집
				GradeVO vo = new GradeVO();
				vo.setGradeNo(Integer.parseInt
						(request.getParameter("gradeNo")));
				vo.setGradeName(request.getParameter("gradeName"));
				result = Execute.execute(Init.get(uri), vo);
				if ((Integer)result != 0) {
					session.setAttribute("msg",
						"회원등급이 등록되었습니다.");
				}
				jsp="redirect:list.do";
				break;
			case "/grade/update.do":
				// 서비스로 넘어가는 데이터 구성
				vo = new GradeVO();
				vo.setGradeNo(Integer.parseInt
						(request.getParameter("gradeNo")));
				vo.setGradeName(request.getParameter("gradeName"));
				result = Execute.execute(Init.get(uri), vo);
				if ((Integer)result != 0) {
					session.setAttribute("msg",
						"회원등급이 수정되었습니다.");
				}
				jsp="redirect:list.do";
				break;
			case "/grade/delete.do":
				// 서비스 넘어가는 데이터 수집
				Integer gradeNo = Integer.parseInt
					(request.getParameter("gradeNo"));
				// 서비스 실행
				result = Execute.execute(Init.get(uri), gradeNo);
				// 결과확인 - 메시지
				if ((Integer)result != 0) {
					session.setAttribute("msg",
						"회원등급이 삭제되었습니다.");
				}
				// 리스트로 이동
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
		
		// 이동할 주소 리턴
		return jsp;
	} // end of execute(
}
