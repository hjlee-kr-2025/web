<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- /WEB-INF/views/image/list.jsp -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gallery</title>
</head>
<body>
<div class="container topbox">
	<h2><i class="fa fa-list"></i> Gallery</h2>
	<c:if test="${empty list }">
		<h4>데이터가 존재하지 않습니다.</h4>
	</c:if>
	<c:if test="${!empty list }">
		<%-- gallery list 구현 --%>
		<div class="row">
			<c:forEach items="${list }" var="vo" varStatus="vs">
				<!-- 데이터표시 시작 -->
				<div class="col-sm-4 dataRow">
					<div class="card" style="width:100%">
						<img class="card-img-top" src="${vo.fileName }" alt="Card image">
				    <div class="card-body">
				      <h4 class="card-title">
				      
				      ${vo.name }
				      </h4>
				      <p class="card-text">
				      	<span class="no">${vo.no }</span>. ${vo.title }
				      </p>
				    </div>					
					</div>
				</div>
			</c:forEach>
		</div>
	</c:if>
	<c:if test="${!empty login }">
		<div>
			<a href="writeForm.do" class="btn btn-primary">등록</a>
		</div>
	</c:if>
</div>
</body>
</html>