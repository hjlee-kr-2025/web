<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<div class="container">
	<h2><i class="fa fa-check-square-o"></i> 로그인</h2>
	<form action="login.do" method="post">
		<div class="form-group">
			<label for="id">아이디</label>
			<input class="form-control" id="id" name="id"
				placeholder="id입력" autocomplete="none">
			<!-- autocomplete="none" 설정하면 자동완성기능 disable -->
		</div>
		<div class="form-group">
			<label for="pw">비밀번호</label>
			<input class="form-control" id="pw" name="pw"
				placeholder="비밀번호입력" type="password">
		</div>
		<button class="btn btn-primary">로그인</button>
	</form>
</div>
</body>
</html>