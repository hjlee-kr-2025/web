<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- "/WEB-INF/views/notice/view.jsp -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<script type="text/javascript">
$(function(){
	
});
</script>

</head>
<body>
<div class="container topbox">
	<h2><i class="fa fa-file-text-o"></i> 공지사항 글보기</h2>
	<div class="card">
	  <div class="card-header">
	  	<div class="d-flex justify-content-between">
	  		<div style="font-size:20px;">${vo.no }. ${vo.title }</div>
	  		<div>조회수: ${vo.hit }</div>
	  	</div>
		</div>
	  <div class="card-body">
	  	<pre>${vo.content }</pre>
	  	<img src="${vo.image }">
	  </div>
	  <div class="card-footer">
	  	<div>
	  		<div>게시시작일: ${vo.startDate }</div>
	  		<div>게시종료일: ${vo.endDate }</div>
	  		<div>작성일: ${vo.writeDate }</div>
	  	</div>
	  	<div style="margin-top: 15px;">
			  <a href="updateForm.do?no=${vo.no }">
			  	<button class="btn btn-primary">수정</button>
			  </a>
			  <button class="btn btn-danger" data-target="#deleteModal"
			  	data-toggle="modal">삭제</button>
			  <a href="list.do">
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
	        <h4 class="modal-title">공지사항 삭제확인</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	
				<form action="delete.do" method="post" id="deleteForm">
					<input type="hidden" name="no" value="${vo.no }">
	      	<!-- Modal body -->
		      <div class="modal-body">
		        ${vo.no }번 공지사항을 삭제하시겠습니까?
		      </div>
		
		      <!-- Modal footer -->
		      <div class="modal-footer">
		        <button class="btn btn-danger">삭제</button>
						<button type="button" class="btn btn-success"
							data-dismiss="modal">취소</button>		        
		     	</div>
	     	</form>

	    </div>
	  </div>
	</div>
	
</div><!-- end of class="container" -->
</body>
</html>




