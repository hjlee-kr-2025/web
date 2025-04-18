package com.gyshop.util.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gyshop.member.vo.LoginVO;

/**
 * Servlet Filter implementation class AuthorityFilter
 */
// 권한처리
// 필터를 사용해서 권한처리를 하는 이유?
// 권한이 없는 페이지를 주소창에 직접 입력해서 올 수 없도록 하기 위해서

// 아래 주석은 web.xml을 통해서 설정할 예정
//@WebFilter("/AuthorityFilter")
public class AuthorityFilter extends HttpFilter implements Filter {
       
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// uri에 따른 권한 저장하는 MAP
	private static Map<String, Integer> authMap = new HashMap<>();
	// 생성자의 <>안에 빈칸으로 두면 선언할 때 사용했던 자료형으로 자동지정됩니다.

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		System.out.println("AuthorityFilter.doFilter() -----");
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		// 권한 처리를 위한 로그인 정보를 가져옵니다.
		HttpSession session = req.getSession();
		
		// 권한 처리를 위한 uri 정보
		String uri = req.getRequestURI();
		System.out.println("Auth : " + uri);
		
		session.setAttribute("uri", uri);

		// 페이지 권한을 Integer로 가져옵니다.
		Integer pageGradeNo = authMap.get(uri);
		// 로그인 정보 저장을 위한 변수
		LoginVO loginVO = null;
		
		// pageGradeNo 가 null 이면 누구나 들어올 수 있는 페이지입니다.
		if (pageGradeNo != null) {
			
			loginVO = (LoginVO)session.getAttribute("login");
			
			if (loginVO == null) {
				// 로그인을 안했으면
				session.setAttribute("msg",
					"로그인이 필요한 페이지입니다. 로그인 해 주세요.");
				// 로그인 폼으로 이동
				res.sendRedirect("/member/loginForm.do");
				return ;
			}
			
			// 로그인 사용자의 등급 (1: 일반회원, 99: 관리자)
			Integer userGradeNo = loginVO.getGradeNo();
			
			if (pageGradeNo > 1) {
				System.out.println("+++ 관리자 권한체크 +++");
				if (pageGradeNo > userGradeNo) {
					// 권한이 없다.
					System.out.println("페이지 사용 권한 없음");
					req.getRequestDispatcher("/WEB-INF/views/error/authority.jsp")
						.forward(req, res);
					
					return;
				}
			}
		}
		
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("AuthorityFilter.init() ------");
		// 권한지정
		
		// 공지사항의 글등록, 수정, 삭제 권한을 관리자만
		// 접근할 수 있도록 등록했습니다.
		authMap.put("/notice/writeForm.do", 99);
		authMap.put("/notice/write.do", 99);
		authMap.put("/notice/updateForm.do", 99);
		authMap.put("/notice/update.do", 99);
		authMap.put("/notice/delete.do", 99);
		
		// 일반게시판 댓글의 등록,수정,삭제 권한은 로그인사용자만 허용합니다.
		authMap.put("/boardreply/write.do", 1);
		authMap.put("/boardreply/update.do", 1);
		authMap.put("/boardreply/delete.do", 1);
		
		// 상품관리(Goods권한)
		authMap.put("/goods/writeForm.do", 99);
		authMap.put("/goods/write.do", 99);
		authMap.put("/goods/updateForm.do", 99);
		authMap.put("/goods/update.do", 99);
		authMap.put("/goods/delete.do", 99);
	}

}
