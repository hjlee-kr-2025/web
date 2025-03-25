<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- /WEB-INF/views/board/list.jsp -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반게시판</title>
<style>
	.container {
		margin-top : 20px;
	}
</style>
<script type="text/javascript">
/* javascript 는 jquery library를 사용해서 구현하겠습니다. 
 * $(document).ready(function(){~~~~~});
 */
$(function(){
	// 이 안에 구현한 javascript코드는 page가 로딩된후 처리됩니다.
	
	// 리스트의 데이터를 클릭했을때 처리하는 이벤트
	$(".dataRow").click(function(){
		alert("dataRow click 이벤트발생");
	});
	
});
</script>
</head>
<body>
<div class="container">
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