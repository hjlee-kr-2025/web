<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	
});
</script>
</head>
<body>
<div class="container topbox">
	<h2><i class="fa fa-list"></i> 일반게시판 리스트</h2>
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
  </table>
</div>
</body>
</html>