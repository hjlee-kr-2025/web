<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- /WEB-INF/views/member/searchForm.jsp -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디/비밀번호찾기</title>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.14.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.14.1/jquery-ui.js"></script>

<script>
$(function(){
	let now = new Date();// 현재날짜를 가져옵니다.
	let nowYear = now.getFullYear();
	// 년도의 범위설정 : 과거 120년전부터 현재년도까지
	let yearRangeString = (nowYear - 120)+ ":" + nowYear;
	
	
	// 날짜입력을 할수 있는 datepicker - jquery-ui에서 가져왔습니다.
	$(".datepicker").datepicker({
		//json 형식으로 datepicker세팅
		// 입력난의 날짜 포맷
		dateFormat: "yy-mm-dd",
		// 월 선택 입력을 추가
		changeMonth: true,
		// 년도 선택 입력을 추가
		changeYear: true,
		// 월 선택입력 (기본은 영어->한글로 변경)
		monthNamesShort: ["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
		// 요일 표시 변경 (기본영어 -> 한글로 변경)
		dayNamesMin: ["일","월","화","수","목","금","토"],
		// 년도 범위 변경
		yearRange: yearRangeString,//"1905:2025"
	});
});
</script>
</head>
<body>
<div class="container topbox">
	<h2>아이디 / 비밀번호 찾기</h2>
	<!-- Nav tabs -->
	<ul class="nav nav-tabs">
	  <li class="nav-item">
	    <a class="nav-link active" data-toggle="tab" href="#searchId">아이디 찾기</a>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link" data-toggle="tab" href="#searchPw">비밀번호 찾기</a>
	  </li>
	</ul>
	
	<!-- Tab panes -->
	<div class="tab-content">
	  <div class="tab-pane container active" id="searchId">
	  	<form action="searchId.do" method="post">
	  		<div class="form-group">
	  			<label for="name">이름</label>
	  			<input class="form-control" id="name" name="name">
	  		</div>
	  		<div class="form-group">
	  			<label for="email">이메일</label>
	  			<input class="form-control" id="email" name="email">
	  		</div>
	  		<div class="form-group">
	  			<label for="birth">생년월일</label>
	  			<input class="form-control datepicker"
	  				id="birth" name="birth" readonly style="background:white;">
	  		</div>
	  		<button class="btn btn-primary">찾기</button>
	  	</form>
	  </div>
	  <div class="tab-pane container fade" id="searchPw">
	  	<form action="searchPw.do" method="post">
	  		<div class="form-group">
	  			<label for="id">아이디</label>
	  			<input class="form-control" id="id" name="id">
	  		</div>
	  		<div class="form-group">
	  			<label for="name2">이름</label>
	  			<input class="form-control" id="name2" name="name">
	  		</div>
	  		<div class="form-group">
	  			<label for="email2">이메일</label>
	  			<input class="form-control" id="email2" name="email">
	  		</div>
	  		<div class="form-group">
	  			<label for="birth2">생년월일</label>
	  			<input class="form-control datepicker"
	  				id="birth2" name="birth" readonly style="background:white;">
	  		</div>
	  		<button class="btn btn-primary">찾기</button>
	  	</form>
	  </div>
	</div>
</div>
</body>
</html>