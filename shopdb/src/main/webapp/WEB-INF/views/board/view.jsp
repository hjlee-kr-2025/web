<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반게시판 글보기</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<script type="text/javascript">
	$(function(){
		$("#deleteBtn").click(function(){
			// 비밀번호 입력창 clear
			$("#pw").val("");
			$("#deleteModal").modal("show");
		});
		
		$("#deleteCancelBtn").click(function(){
			$("#pw").val("");
			$("#deleteModal").modal("hide");
		});
	});
	</script>
</head>
<body>
<div class="container">
  <h2><i class="material-icons">assignment</i> 일반게시판 글보기</h2>
  <div class="card">
    <div class="card-header">
    	${vo.no }. ${vo.title }
    </div>
    <div class="card-body">
    <pre>${vo.content }</pre>
    </div> 
    <div class="card-footer">
    	<div>
    		작성자: ${vo.writer }
    	</div>
    	<div>
    		작성일: ${vo.writeDate }
    	</div>
    	<div>
    		조회수: ${vo.hit }
    	</div>
    	<div>
    		<a href="updateForm.do?no=${param.no }">
    			<button class="btn btn-primary">수정</button></a>
    		<button class="btn btn-danger" id="deleteBtn">삭제</button>
    		<a href="list.do">
    			<button class="btn btn-success">리스트</button></a>
    	</div>
    </div>
  </div>
</div>

<!-- The Modal -->
<div class="modal" id="deleteModal">
  <div class="modal-dialog">
    <div class="modal-content">
		<form action="delete.do" method="post">
      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">비밀 번호 확인</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        	<input type="hidden" name="no" value="${vo.no }">
        	<input type="password" placeHolder="본인확인용 비밀번호입력"
        		name="pw" id="pw" required class="form-control"
        		maxlength="20">
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

</body>
</html>