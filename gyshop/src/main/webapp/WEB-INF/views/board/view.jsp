<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- "/WEB-INF/views/board/view.jsp -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반게시판</title>
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
	  	<pre>${vo.content }</pre>
	  </div>
	  <div class="card-footer">
	  	<div class="d-flex justify-content-between">
	  		<div>작성자: ${vo.writer }</div>
	  		<div>작성일: ${vo.writeDate }</div>
	  	</div>
	  </div>
	</div>
</div>
</body>
</html>