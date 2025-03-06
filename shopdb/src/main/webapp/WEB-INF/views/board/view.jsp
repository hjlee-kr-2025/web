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
    </div>
  </div>
</div>
</body>
</html>