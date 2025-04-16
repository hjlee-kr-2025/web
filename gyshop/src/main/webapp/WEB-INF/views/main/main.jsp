<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
</head>
<body>
<div class="container topbox">
	<h2>MAIN</h2>
	<div class="row">
		<div class="col-sm-6">
			<!-- 공지사항 리스트 (5) -->
		</div>
		<div class="col-sm-6">
			<!-- 일반게시판 리스트 (5) -->
			<!-- 제목/작성자/조회수 -->
			<h4>일반게시판 리스트</h4>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>조회수</th>
					</tr>
				</thead>
				<c:forEach items="${boardList }" var="boardVO">
					<tr class="dataRow">
						<td class="no">${boardVO.no }</td>
						<td>${boardVO.title }</td>
						<td>${boardVO.writer }</td>
						<td>${boardVO.hit }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<!-- 이미지게시판 리스트 (3) -->
		</div>
	</div>
</div>
</body>
</html>