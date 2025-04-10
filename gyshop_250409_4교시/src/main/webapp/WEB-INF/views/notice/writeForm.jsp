<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- /WEB-INF/views/notice/writeForm.jsp -->    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.14.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.14.1/jquery-ui.js"></script>

<script type="text/javascript">
// 전역변수 선언
// modal창에 표시할 제목과 내용
var messageTitle;
var messageContent;

$(function(){
	// jquery 코드 작성준비 완료
	let now = new Date();// 현재날짜를 가져옵니다.
	let nowYear = now.getFullYear();
	// 년도의 범위설정 : 현재년도기준 과거10년, 미래10년 범위
	let yearRangeString = (nowYear - 10)+ ":" + (nowYear + 10);
	
	
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
		yearRange: yearRangeString,//"2015:2035"
	});
	
	
	$("#cancelBtn").click(function(){
		// 취소버튼을 클릭하면 리스트로 갑니다.
		// 1. 뒤로가기
		history.back();// 웹브라우저의 뒤로가기 버튼을 누른것과 동일한 처리를 합니다.
		// 2. 리스트 페이지로 이동
		//location = "list.do";
	});
	
	$("#imageFile").change(function(){
		let file = this.files[0];
		let reader = new FileReader();
		// 파일로딩이 끝났을 때 처리하는 곳 구현
		reader.onloadend = function() {
			$("#image").attr("src", reader.result);
		}
		// 파일을 읽어오는 처리
		reader.readAsDataURL(file);
	});
	
});
</script>
</head>
<body>
<div class="container topbox">
	<h2><i class="fa fa-edit"></i> 공지사항 글쓰기</h2>
	<form action="write.do" method="post" id="writeForm"
		enctype="multipart/form-data">
		<!-- 서버로 파일이 전송될때는 아래 2가지는 form태그에 적용되어야 합니다.
			1. method="post"
			2. enctype="multipart/form-data"
		 -->
	  <div class="form-group">
	    <label for="title">제목:</label>
	    <input type="text" class="form-control"
	    	placeholder="제목을 입력하세요" id="title"
	    	name="title" required>
	  </div>
	  <div class="form-group">
	  	<label for="content">내용:</label>
	  	<textarea class="form-control" id="content"
	  		name="content" rows="10"></textarea>
	  </div>
	  <div class="form-group">
	    <label for="imageFile">내용이미지:</label>
	    <input type="file" class="form-control"
	    	id="imageFile" name="imageFile">
	  </div>
	  <div>
	  	<img id="image" src="">
	  </div>
	  <div class="form-group">
	    <label for="startDate">게시시작일:</label>
	    <input type="text" class="form-control datepicker"
	    	id="startDate" name="startDate" required
	    	readonly style="background:white;">
	  </div>
	  <div class="form-group">
	    <label for="endDate">게시종료일:</label>
	    <input type="text" class="form-control datepicker"
	    	id="endDate" name="endDate" required
	    	readonly style="background:white;">
	  </div>
	  <!-- 날짜입력에 readonly 속성을 부여한 이유는 
	  		키보드로 타이핑해서 입력은 안되고 datepicker로만 입력되도록
	  		하기 위해서 입니다.
	  		readonly를 쓰면 배경색이 grey로 되기때문에
	  		background:white; 를 이용해 흰색배경으로 변경했습니다.
	  -->
	  <button type="submit" class="btn btn-primary">등록</button>
	  <button type="reset" class="btn btn-success">새로고침</button>
	  <button type="button" class="btn btn-secondary"
	  	id="cancelBtn">취소</button>
	</form>
</div>
</body>
</html>