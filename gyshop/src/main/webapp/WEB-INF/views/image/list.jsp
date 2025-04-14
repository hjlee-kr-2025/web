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
	</c:if>
	<c:if test="${!empty login }">
		<div>
			<a href="writeForm.do" class="btn btn-primary">등록</a>
		</div>
	</c:if>
</div>
</body>
</html>