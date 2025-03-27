package com.gyshop.util.page;

import javax.servlet.http.HttpServletRequest;

public class PageObject {

	// 현재 페이지를 화면에 보여줄 때 필요한 정보
	private Long page;			// 현재페이지
	private Long perPageNum;	// 한 페이지에 보여줄 데이터 수
	private Long startRow;		// 보여주는 데이터 시작
	private Long endRow;		// 보여주는 데이터 끝
	
	// 페이지 계산을 위한 정보 + 페이지네이션 처리를 위한 정보
	private Long perGroupPageNum; // 한 화면에 보여줄 페이지 링크
	private Long startPage; // 페이지링크 : 보여주는 시작페이지
	private Long endPage;	// 페이지링크 : 보여주는 마지막페이지
	private Long totalPage;	// 전체 페이지 수
	private Long totalRow;	// 전체 데이터 수
	
	// 생성자
	public PageObject() {
		this.page = 1L;
		this.perPageNum = 10L;
		this.startRow = 1L;
		this.endRow = 1L;
		
		this.perGroupPageNum = 10L;
		this.startPage = 1L;
		this.endPage = 1L;
	}
	
	// 실제 페이지 처리를 위한 세팅시 호출하는 메서드
	public static PageObject getInstance(HttpServletRequest request) {
		return getInstance(request, "page", "perPageNum");
	}
	
	// 객체 정보를 가져오는 메서드
	// 파라메터 3개중
	// page 정보와 perPageNum 정보가 request 에 담아서 전달됩니다.
	// request에 담긴 key값으로 가져오기 위해 String으로 전달합니다.
	public static PageObject getInstance(HttpServletRequest request,
			String pageName, String perPageNumName) {
		// 페이지 처리를 위한 객체 생성 : 리턴할 변수
		PageObject pageObject = new PageObject();
		// 페이지 처리를 위한 정보
		String strPage = request.getParameter(pageName);
		String strPerPageNum = request.getParameter(perPageNumName);
		if (strPage != null && !strPage.equals("")) {
			pageObject.setPage(Long.parseLong(strPage));
		}
		if (strPerPageNum != null && !strPerPageNum.equals("")) {
			pageObject.setPerPageNum(Long.parseLong(strPerPageNum));
		}
		
		// PageObject 객체 리턴
		return pageObject;
	}

	// getter, setter를 구성 후 수정해서 사용할 예정입니다.
	// 그래서 lombok 라이브러리를 사용하지 않고 자동완성으로 구성합니다.
	public Long getPage() {
		return page;
	}

	public void setPage(Long page) {
		this.page = page;
	}

	public Long getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(Long perPageNum) {
		this.perPageNum = perPageNum;
	}

	public Long getStartRow() {
		return startRow;
	}

	public void setStartRow(Long startRow) {
		this.startRow = startRow;
	}

	public Long getEndRow() {
		return endRow;
	}

	public void setEndRow(Long endRow) {
		this.endRow = endRow;
	}

	public Long getPerGroupPageNum() {
		return perGroupPageNum;
	}

	public void setPerGroupPageNum(Long perGroupPageNum) {
		this.perGroupPageNum = perGroupPageNum;
	}

	public Long getStartpage() {
		return startPage;
	}

	public void setStartpage(Long startPage) {
		this.startPage = startPage;
	}

	public Long getEndPage() {
		return endPage;
	}

	public void setEndPage(Long endPage) {
		this.endPage = endPage;
	}

	public Long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

	public Long getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(Long totalRow) {
		// 전체 데이터 개수가 들어왔을 때 
		// 페이지 처리를 위한 계산을 진행합니다.
		this.totalRow = totalRow;
		
		System.out.println("page = " + page);
		System.out.println("perPageNum = " + perPageNum);
		// 시작 줄번호와 끝번호 계산
		// perPageNum = 10;
		// page = 1 -> startRow = 1, endRow = 10;
		// page = 2 -> startRow = 11, endRow = 20;
		// page = 3 -> startRow = 21, endRow = 30;
		startRow = (page - 1) * perPageNum + 1;
		endRow = startRow + perPageNum - 1;
		System.out.println("startRow = " + startRow);
		System.out.println("endRow = " + endRow);
		
		System.out.println("---- 페이지 링크 ----");
		System.out.println("perGroupPageNum = " + perGroupPageNum);
		// 페이지 계산
		//전체 데이터수 / 한페이지데이터수 + ((전체데이터수%한페이지데이터수==0)?0:1);
		totalPage = (totalRow - 1L) / perPageNum + 1L;
		// 1-10, 11-20, 21-30
		startPage = (page-1)/perGroupPageNum*perGroupPageNum + 1;
		endPage = startPage + perGroupPageNum - 1;
		
		// endPage는 totalPage보다 크면 안됩니다.
		if (endPage > totalPage) endPage = totalPage;
		System.out.println("totalPage = " + totalPage);
		System.out.println("startPage = " + startPage);
		System.out.println("endPage = " + endPage);
	}
	
	
	
}
