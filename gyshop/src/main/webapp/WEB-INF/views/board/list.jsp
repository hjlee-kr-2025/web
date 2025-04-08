<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>
<!-- /WEB-INF/views/board/list.jsp -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반게시판</title>

<script type="text/javascript">
/* javascript 는 jquery library를 사용해서 구현하겠습니다. 
 * $(document).ready(function(){~~~~~});
 */
$(function(){
	// 이 안에 구현한 javascript코드는 page가 로딩된후 처리됩니다.
	
	// 리스트의 데이터를 클릭했을때 처리하는 이벤트
	$(".dataRow").click(function(){
		//alert("dataRow click 이벤트발생");
		let no = $(this).find(".no").text();
		// 클릭이벤트가 발생한 곳에서 <tr> 아래방향으로 찾습니다.
		// class="no" 로 되어있는 태그를 찾고,
		// 그안에 적힌 값을 가져옵니다.
		//alert("클릭한 no : " + no);
		
		// 페이지를 이동합니다. location에 주소를 입력합니다.
		location = "view.do?no="+no+"&inc=1";
		// ==> view.do?no=글번호
	});
	
	// 글쓰기 버튼 이벤트
	$("#writeBtn").click(function(){
		location = "writeForm.do";
	});
	
	$("#perPageNum").change(function(){
		$("#searchForm").submit();
	});
	
	// 초기데이터 세팅
	$("#perPageNum")
		.val("${(empty pageObject.perPageNum)?'10':pageObject.perPageNum}");
	
});
</script>
</head>
<body>
<div class="container topbox">
	<h2><i class="fa fa-list"></i> 일반게시판 리스트</h2>
	<form id="searchForm" action="list.do">
		<div class="row">
			<div class="col-sm-6">
			</div>
			<div class="col-sm-3">
				<!-- 게시글 정렬 방식 지정 -->
				<div class="input-group mb-3">
			    <div class="input-group-prepend">
			      <span class="input-group-text">정렬방법</span>
			    </div>
			    <select class="form-control" id="orderStyle" name="orderStyle">
			    	<option value="1">최신글</option>
			    	<option value="2">과거글</option>
			    	<option value="3">조회수</option>
			    </select>
			  </div>
			</div>
			<div class="col-sm-3">
				<!-- 한페이지의 게시글수 지정 -->
				<div class="input-group mb-3">
			    <div class="input-group-prepend">
			      <span class="input-group-text">Rows/page</span>
			    </div>
			    <select class="form-control" id="perPageNum" name="perPageNum">
			    	<option>10</option>
			    	<option>15</option>
			    	<option>20</option>
			    	<option>25</option>
			    </select>
			  </div>
			</div>
		</div>
	</form>
	<table class="table table-hover">
    <thead><!-- 칼럼의 제목 -->
      <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일</th>
        <th>조회수</th>
      </tr>
    </thead>
    <tbody>
    	<c:forEach items="${list }" var="vo">
    		<tr class="dataRow">
					<!-- class="dataRow"는 클릭이벤트를 처리하려고 선언 -->
					<!-- class 를 사용한 이유는 같은 종류의 여러데이터 중 하나를
						처리해야하기때문에 사용했습니다. id는 하나만 처리가능 -->
    			<td class="no">${vo.no }</td>
    			<td>${vo.title }</td>
    			<td>${vo.writer }</td>
    			<td>${vo.writeDate }</td>
    			<td>${vo.hit }</td>
    		</tr>
    	</c:forEach>
    </tbody>
    <tfoot>
    	<tr>
    		<td colspan="5" style="text-align:right;">
    			<button class="btn btn-primary" id="writeBtn">글쓰기</button>
    		</td>
    	<tr>
    </tfoot>
  </table>
  <div>
  	<pageNav:pageNav listURI="list.do" pageObject="${pageObject }"></pageNav:pageNav>
  </div>
</div>
</body>
</html>