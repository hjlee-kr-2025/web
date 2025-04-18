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
			<c:if test="${!empty login && (login.gradeNo == 99) }">
				<a href="updateForm.do?no=${vo.no }"
					 class="btn btn-primary">수정</a>
			</c:if>
			<a href="list.do" class="btn btn-success">리스트</a>
			<c:if test="${!empty login && (login.gradeNo == 99) }">
				<button class="btn btn-danger" type="button"
					 data-toggle="modal" data-target="#deleteModal">삭제</button>
			</c:if>
		</div>
	</div>
	
	<!-- The Modal -->
	<div class="modal" id="deleteModal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	
	      <!-- Modal Header -->
	      <div class="modal-header">
	        <h4 class="modal-title">상품삭제확인</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	
	      <!-- Modal body -->
	      <div class="modal-body">
	        상품을 삭제하시겠습니까?
	      </div>
	
	      <!-- Modal footer -->
	      <div class="modal-footer">
	      	<form action="delete.do" method="post">
	      		<input type="hidden" name="no" value="${vo.no }">
	      		<input type="hidden" name="deletePhoto" value="${vo.photo }">
	      		<input type="hidden" name="deleteSubPhoto1" value="${vo.subPhoto1 }">
	      		<input type="hidden" name="deleteSubPhoto2" value="${vo.subPhoto2 }">
	      		<input type="hidden" name="deleteSubPhoto3" value="${vo.subPhoto3 }">
	      		<input type="hidden" name="deleteSubPhoto4" value="${vo.subPhoto4 }">
	      		<button class="btn btn-danger">삭제</button>
	      	</form>
	        <button type="button" class="btn btn-success" data-dismiss="modal">취소</button>
	      </div>
	
	    </div>
	  </div>
	</div>
</div>
</body>
</html>