<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- /WEB-INF/views/board/writeForm.jsp -->    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반게시판</title>
<script type="text/javascript">
$(function(){
	// jquery 코드 작성준비 완료
	
	// 길이체크하는 함수
	function lengthCheck(objStr, objName, min, max, isTrim) {
		// objStr에 적힌 값을 가져옵니다.
		let str = $(objStr).val();
		if (isTrim) {
			//str안에 저장된 문자열의 양쪽공백제거후 다시 str에 저장
			str = str.trim();
			// 공백제거된 str문자열을 objStr객체에 다시 기록합니다.(화면)
			$(objStr).val(str);
			//objStr="#title" 일때 $("#title").val(str);
		}
		
		// str의 길이를 변수에 저장합니다.
		let len = str.length;
		if (len < min || len > max) {
			alert(objName + "은(는) " + min + "에서 "
					+ max + "자까지 입력할 수 있습니다.");
			$(objStr).focus();
			return false;// submit event 취소
		}
	}
	
	
	// 언제 아래 사항을 확인할 것인가? ==> 등록버튼을 클릭했을 때
	// form 태그에서 type="submit" 인 input 태그 또는 button 태그를
	// 클릭했을때 form 태그를 기준으로 submit event가 발생됩니다.
	$("#writeForm").submit(function(){
		//alert("form 태그 submit 이벤트 발생");
		/*
		// 제목에 쓴 글의 길이가 제한사항에 맞는지 확인
		let str = $("#title").val();
		// 문자열의 양쪽 공백(space)를 제거하는 함수 trim()
		str = str.trim();
		$("#title").val(str);// 공백제거한 문자열로 입력창에 새로입력
		let len = str.length;
		if (len < 2 || len > 100) {
			alert("제목은 2자에서 100자까지 입력할 수 있습니다.")
			$("#title").focus();// 입력커서를 제목난에 위치시킨다.
			return false;
		}
		*/
		lengthCheck("#title", "제목", 2, 100, true);
		// 내용 : ~ 1000자
		lengthCheck("#content", "내용", 0, 1000, false);
		// 작성자 : 2~10자, 양쪽공백제거
		lengthCheck("#writer", "작성자", 2, 10, true);
		// 비밀번호, 비밀번호확인 : 4~20자
		lengthCheck("#pw", "비밀번호", 4, 20, false);
		lengthCheck("#pw2", "비밀번호", 4, 20, false);
		
		// 비밀번호와 비밀번호 확인이 같은지 확인
		let pw = $("#pw").val();
		let pw2 = $("#pw2").val();
		if (pw != pw2) {
			alert("비밀번호와 비밀번호확인이 다릅니다. 다시 확인해 주세요");
			return false; // submit 이벤트를 취소시킨다.
		}
		
		//return false; // submit 이벤트를 취소시킨다.
	});
	
	
});
</script>
</head>
<body>
<div class="container topbox">
	<h2><i class="fa fa-edit"></i> 일반게시판 글쓰기</h2>
	<form action="write.do" method="post" id="writeForm">
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
	
	<!-- The Modal -->
  <div class="modal fade" id="myModal">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Modal Heading</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          Modal body..
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        </div>
        
      </div>
    </div>
  </div>
	
	
	
</div>
</body>
</html>