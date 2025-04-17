<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쇼핑몰</title>
<script type="text/javascript">
$(function(){
	// 이벤트처리
	$(".dataRow").click(function(){
		let no = $(this).find(".no").text();
		console.log("no : ", no);
		// 상품상세보기 페이지 이동
		location = "view.do?no=" + no;
	});
});
</script>
</head>
<body>
<div class="container topbox">
	<h2><i class="fa fa-list"></i> 상품 리스트</h2>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>상품번호</th>
				<th>상품이미지</th>
				<th>상품명</th>
				<th>가격</th>
				<th>배송료</th>
			</tr>
		</thead>
		<!-- 실제 데이터가 있는 만큼 화면에 보여줍니다. -->
		<c:if test="${empty list }">
			<tr>
				<td colspan="5">
					등록된 상품이 없습니다.
				</td>
			</tr>
		</c:if>
		<c:if test="${!empty list }">
			<c:forEach items="${list }" var="vo">
				<tr class="dataRow">
					<td class="no">${vo.no }</td>
					<td><img src="${vo.photo }" width="30px" height="30px"></td>
					<td>${vo.name }</td>
					<td>${vo.price }</td>
					<td>${vo.delivery_cost }</td>
				</tr>
			</c:forEach>
		</c:if>
		<tr>
			<td colspan="5">
				<a href="writeForm.do" class="btn btn-primary">상품등록</a>
			</td>
		</tr>
	</table>
</div>
</body>
</html>



