<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- /WEB-INF/views/goods/view.jsp -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 정보</title>
<style>
	#smallImageDiv img {
		width: 23%;
		height: 80px;
	}
</style>
<script>
$(function(){
	console.log("vo", "${vo }");
});
</script>
</head>
<body>
<div class="container topbox">
	<h2>상품 정보</h2>
	<div class="card">
		<div class="card-body">
			<div class="row">
				<div class="col-sm-6">
				<!-- 이미지 -->
					<div id="smallImageDiv">
						<c:if test="${!empty vo.subPhoto1 }">
							<img src="${vo.subPhoto1 }">
						</c:if>
						<c:if test="${empty vo.subPhoto1 }">
							<img src="/upload/noImage.png">
						</c:if>
						<c:if test="${!empty vo.subPhoto2 }">
							<img src="${vo.subPhoto2 }">
						</c:if>
						<c:if test="${empty vo.subPhoto2 }">
							<img src="/upload/noImage.png">
						</c:if>
						<c:if test="${!empty vo.subPhoto3 }">
							<img src="${vo.subPhoto3 }">
						</c:if>
						<c:if test="${empty vo.subPhoto3 }">
							<img src="/upload/noImage.png">
						</c:if>
						<c:if test="${!empty vo.subPhoto4 }">
							<img src="${vo.subPhoto4 }">
						</c:if>
						<c:if test="${empty vo.subPhoto4 }">
							<img src="/upload/noImage.png">
						</c:if>
					</div>
					<div id="bigImageDiv">
						<img src="${vo.photo }" style="width: 100%;">
					</div>
				</div>
				<div class="col-sm-6">
				<!-- 상품명, 가격정보 -->
					<div>
						상품명 : ${vo.name }
					</div>
					<div>
						모델번호 : ${vo.modelNo }
					</div>
					<div>
						가격 : ${vo.price }
					</div>
					<div>
						배송료 : ${vo.delivery_cost }
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">
				<!-- 상세내용(content) -->
					<pre>${vo.content }</pre>
				</div>
			</div>
		</div>
		<div class="card-footer">
			<a href="updateForm.do?no=${vo.no }"
				 class="btn btn-primary">수정</a>
			<a href="list.do" class="btn btn-success">리스트</a>
		</div>
	</div>
</div>
</body>
</html>