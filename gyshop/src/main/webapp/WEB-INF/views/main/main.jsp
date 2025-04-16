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
			<!-- 제목/게시종료일/조회수 -->
			<h4>공지사항 리스트</h4>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>게시종료일</th>
						<th>조회수</th>
					</tr>
				</thead>
				<c:forEach items="${noticeList }" var="noticeVO">
					<tr>
						<td class="no">${noticeVO.no }</td>
						<td>${noticeVO.title }</td>
						<td>${noticeVO.endDate }</td>
						<td>${noticeVO.hit }</td>
					</tr>
				</c:forEach>
			</table>
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
			<h4>이미지게시판 리스트</h4>
			<c:if test="${empty imageList }">
				<h5>데이터가 존재하지 않습니다.</h5>
			</c:if>
			<c:if test="${!empty imageList }">
				<%-- gallery list 구현 --%>
				<div class="row mb-2">
					<c:forEach items="${imageList }" var="vo" varStatus="vs">
						<!-- 3개표시후 줄을 바꾸도록 구현해 줍니다. -->
						<c:if test="${(vs.index != 0) && (vs.index%3 == 0)}">
							${"</div>"}
							${"<div class='row mb-2'>"}
						</c:if>
						<!-- 데이터표시 시작 -->
						<div class="col-sm-4 dataRow">
							<div class="card" style="width:100%">
								<div class="imageDiv text-center align-content-center">
									<img class="card-img-top" src="${vo.fileName }" alt="Card image">
								</div>
						    <div class="card-body">
						      <h4 class="card-title">
						      	<span class="float-right" style="font-size:14px;">${vo.writeDate }</span>
						      	${vo.name }
						      </h4>
						      <p class="card-text">
						      	<span class="float-right">조회수: ${vo.hit }</span>
						      	<span class="no">${vo.no }</span>. ${vo.title }
						      </p>
						    </div>					
							</div>
						</div>
					</c:forEach>
				</div>
			</c:if>
		</div>
	</div>
</div>
</body>
</html>