<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- /WEB-INF/views/image/view.jsp -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gallery</title>
</head>
<body>
<div class="container topbox">
	<h2><i class="fa fa-file-text-o"></i> Gallery 글 보기</h2>
	<div class="card">
		<div class="card-header">
			<span class="float-right">조회수: ${vo.hit }</span>
			<b>${vo.no }. ${vo.title }</b>
		</div>
		<div class="card-body">
			<div class="card">
				<img class="card-img-top" src="${vo.fileName }">
				<div class="card-body">
					<p class="card-text"><pre>${vo.content }</pre></p>
				</div>
			</div>
		</div>
		<div class="card-footer">
			<span class="float-right">${vo.writeDate }</span>
			${vo.name } (${vo.id })
		</div>
	</div>
	<c:if test="${!empty login && login.id == vo.id }">
		<a href="updateForm.do?no=${vo.no }"
			class="btn btn-primary">수정</a>
		<button type="button" class="btn btn-danger"
			data-toggle="modal" data-target="#deleteModal">삭제</button>
	</c:if>
	<a href="list.do" class="btn btn-success">리스트</a>
	
	<!-- The Modal -->
	<div class="modal" id="deleteModal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	
	      <!-- Modal Header -->
	      <div class="modal-header">
	        <h4 class="modal-title">삭제확인</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	
	      <!-- Modal body -->
	      <div class="modal-body">
	        <b>게시글을 삭제하시겠습니까?</b>
	      </div>
	
	      <!-- Modal footer -->
	      <div class="modal-footer">
	      	<form action="delete.do" method="post">
	      		<input type="hidden" name="no" value="${vo.no }">
	      		<input type="hidden" name="deleteFile" value="${vo.fileName }">
	      		<button class="btn btn-danger">삭제</button>
	        	<button type="button" class="btn btn-success" data-dismiss="modal">Close</button>
	      	</form>
	      </div>
	
	    </div>
	  </div>
	</div>
	
</div>
</body>
</html>