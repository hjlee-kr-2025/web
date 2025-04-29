<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- "/WEB-INF/views/board/view.jsp -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반게시판</title>
<script type="text/javascript">
$(function(){
	// 글보기의 삭제버튼 클릭시
	$("#deleteBtn").click(function(){
		console.log("#deleteBtn click event----")
		// 비밀번호 입력창 클리어
		$("#pw").val("");
		// 삭제확인 모달창 open
		$("#deleteModal").modal("show");
	});
	
	// 모달창의 취소버튼 클릭시
	$("#deleteCancelBtn").click(function(){
		// 비밀번호 입력창 클리어
		$("#pw").val("");
		// 삭제확인 모달창 close
		$("#deleteModal").modal("hide");		
	});
	
	
	
});
</script>

</head>
<body>
<div class="container topbox">
	<h2><i class="fa fa-file-text-o"></i> 일반게시판 글보기</h2>
	<div class="card">
	  <div class="card-header">
	  	<div class="d-flex justify-content-between">
	  		<div style="font-size:20px;">${vo.no }. ${vo.title }</div>
	  		<div>조회수: ${vo.hit }</div>
	  	</div>
		</div>
	  <div class="card-body">
	  	<%-- jstl 의 <c:out /> 을 사용하면
	  		HTML태그가 나오면 escape 시켜준다.
	  	 --%>
	  	<pre><c:out value="${vo.content }" /></pre>
	  </div>
	  <div class="card-footer">
	  	<div class="d-flex justify-content-between">
	  		<div>작성자: ${vo.writer }</div>
	  		<div>작성일: ${vo.writeDate }</div>
	  	</div>
	  	<div style="margin-top: 15px;">
			  <a href="updateForm.do?no=${vo.no }&page=${param.page}&perPageNum=${param.perPageNum}&orderStyle=${param.orderStyle}&key=${param.key}&word=${param.word}">
			  	<button class="btn btn-primary">수정</button>
			  </a>
			  <button class="btn btn-danger" id="deleteBtn">삭제</button>
			  <a href="list.do?page=${param.page}&perPageNum=${param.perPageNum}&orderStyle=${param.orderStyle}&key=${param.key}&word=${param.word}">
			  	<button class="btn btn-success">리스트</button>
			  </a>
		  </div>
	  </div>
	</div><!-- end of class="card" -->
	
	<!-- The Modal -->
	<div class="modal" id="deleteModal">
	  <div class="modal-dialog">
	    <div class="modal-content">

	      <!-- Modal Header -->
	      <div class="modal-header">
	        <h4 class="modal-title">비밀번호 확인</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	
				<form action="delete.do" method="post" id="deleteForm">
					<input type="hidden" name="no" value="${vo.no }">
					<!-- 정렬방법과 페이지당 데이터수를 유지하기 위한 데이터세팅 -->
					<input type="hidden" name="orderStyle" value="${param.orderStyle }">
					<input type="hidden" name="perPageNum" value="${param.perPageNum }">
					<!-- 아래데이터는 비밀번호가 틀려서 지워지지 않았을때 필요한 정보입니다.0 -->
					<input type="hidden" name="page" value="${param.page }">
					<input type="hidden" name="key" value="${param.key }">
					<input type="hidden" name="word" value="${param.word }">
	      	<!-- Modal body -->
		      <div class="modal-body">
		        <input type="password" name="pw" id="pw"
		        	placeHolder="본인 확인용 비밀번호 입력" required
		        	maxlength="20" pattern="^.{4,20}$"
		        	title="비밀번호는 4자이상입력하세요."
		        	class="form-control"
		        	>
		      </div>
		
		      <!-- Modal footer -->
		      <div class="modal-footer">
		        <button class="btn btn-danger">삭제</button>
						<button type="button" class="btn btn-success"
						id="deleteCancelBtn">취소</button>		        
		     	</div>
	     	</form>

	    </div>
	  </div>
	</div>
	
	<!-- 댓글처리시작 -->
	<jsp:include page="reply.jsp"></jsp:include>
	<!-- 댓글처리끝 -->
	
</div><!-- end of class="container" -->
</body>
</html>




