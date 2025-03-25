<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- /WEB-INF/views/board/writeForm.jsp -->    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반게시판</title>
</head>
<body>
<div class="container topbox">
	<h2><i class="fa fa-edit"></i> 일반게시판 글쓰기</h2>
	<form action="write.do" method="post">
	  <div class="form-group">
	    <label for="title">제목:</label>
	    <input type="text" class="form-control"
	    	placeholder="제목을 입력하세요" id="title"
	    	name="title" required>
	  </div>
	  <div class="form-group">
	  	<label for="content">내용:</label>
	  	<textarea class="form-control" id="content"
	  		name="content" rows="10" required></textarea>
	  </div>
	  <div class="form-group">
	    <label for="writer">작성자:</label>
	    <input type="text" class="form-control"
	    	placeholder="이름을 입력하세요" id="writer"
	    	name="writer" required>
	  </div>
	  <div class="form-group">
	    <label for="pw">비밀번호:</label>
	    <input type="password" class="form-control"
	    	placeholder="비밀번호를 입력하세요" id="pw" name="pw" required>
	  </div>
	  <div class="form-group">
	    <label for="pw2">비밀번호확인:</label>
	    <input type="password" class="form-control"
	    	placeholder="비밀번호확인을 입력하세요" id="pw2" required>
	  </div>
	  <button type="submit" class="btn btn-primary">등록</button>
	</form>
</div>
</body>
</html>