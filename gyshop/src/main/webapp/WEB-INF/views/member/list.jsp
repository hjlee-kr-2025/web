<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원리스트</title>
<script type="text/javascript">
$(function(){
	// 이벤트 - 회원리스트에서 회원정보 클릭시
	$(".dataRow").click(function(){
		let id = $(this).find(".id").text();
		console.log("id = ", id);
		
		// 상세보기 페이지로 이동
	//	location = "view.do?id=" + id;
	});
});
</script>
</head>
<body>
<div class="container topbox">
	<h2><i class="fa fa-list"></i> 회원리스트</h2>
	<table class="table table-hover">
		<!-- 테이블의 제목줄 -->
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>생년월일</th>
			<th>연락처</th>
			<th>등급</th>
			<th>상태</th>
		</tr>
		<!-- 실제 데이터베이스에 저장된 데이터 -->
		<c:forEach items="${list}" var="vo">
			<tr class="dataRow">
				<td class="id">${vo.id }</td>
				<td>${vo.name }</td>
				<td>${vo.birth }</td>
				<td>${vo.tel }</td>
				<td>${vo.gradeName }</td>
				<td>${vo.status }</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>