<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화정보</title>
</head>
<body>
<div class="container">
	<table class="table">
		<c:forEach items="${movieListMap.movieListResult.movieList }" var="list">
			<c:if test="${list.repGenreNm ne '성인물(에로)' }">
				<tr>
					<td><c:out value="${list.movieNm }" /></td>
					<td><c:out value="${list.typeNm }" /></td>
					<td><c:out value="${list.prdtStatNm }" /></td>
					<td><c:out value="${list.generAlt }" /></td>
					<td><c:out value="${list.repNationNm }" /></td>
					<td><c:out value="${list.repGenreNm }" /></td>
				</tr>
			</c:if>
		</c:forEach>
	</table>
</div>
</body>
</html>