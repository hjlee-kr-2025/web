<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- /WEB-INF/views/member/writeForm.jsp -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.14.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.14.1/jquery-ui.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script type="text/javascript">
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
	
	
	// 다음 우편번호 검색서비스
	$("#searchPostcode").click(function(){
		new daum.Postcode({
			oncomplete: function(data) {
				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

				// 각 주소의 노출 규칙에 따라 주소를 조합한다.
				// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
				var addr = ''; // 주소 변수
				var extraAddr = ''; // 참고항목 변수

				//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
				if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
					addr = data.roadAddress;
				} else { // 사용자가 지번 주소를 선택했을 경우(J)
					addr = data.jibunAddress;
				}

				// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
				if(data.userSelectedType === 'R'){
					// 법정동명이 있을 경우 추가한다. (법정리는 제외)
					// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
					if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
						extraAddr += data.bname;
					}
					// 건물명이 있고, 공동주택일 경우 추가한다.
					if(data.buildingName !== '' && data.apartment === 'Y'){
						extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
					}
					// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
					if(extraAddr !== ''){
						extraAddr = ' (' + extraAddr + ')';
					}
					// 조합된 참고항목을 해당 필드에 넣는다.
					document.getElementById("sample6_extraAddress").value = extraAddr;
                
				} else {
					document.getElementById("sample6_extraAddress").value = '';
				}
	
				// 우편번호와 주소 정보를 해당 필드에 넣는다.
				document.getElementById('zipcode').value = data.zonecode;
				document.getElementById("addr1").value = addr;
				// 커서를 상세주소 필드로 이동한다.
				document.getElementById("addr2").focus();
			}
		}).open();
	});
	
});
</script>
</head>
<body>
<!-- 
정규표현식
시작: ^, 끝: $
[]: 한글자의 패턴
{최소,최대}: 앞에 쓰여진 패턴이 적용되는 최소값, 최대값
.: 엔터를 제외한 모든 문자
^: 뒤에 나오는 문자는 제외(사용불가)
예1) 알파벳대소문자, 한글사용이 가능한 3-20자패턴: ^[a-zA-Z가-힣]{3,20}$
예2) 공백을 제외한 모든문자 3-20자패턴: ^[^ .]{3,20}$
 -->
<div class="container topbox">
	<h2><i class="fa fa-edit"></i> 회원 가입</h2>
	<form action="write.do" method="post" id="writeForm">
		<div class="form-group">
	    <label for="id">아이디:</label>
	    <input type="text" class="form-control" maxlength="20"
	    	pattern="^[a-zA-Z][a-zA-Z0-9]{2,19}$"
	    	placeholder="맨앞글자는 영문자, 뒤에는 영문자,숫자로 구성, 3~20자"
	    	id="id" name="id" required>
	  </div>
	  <div class="alert alert-danger" id="checkIdDiv">
	  	아이디는 필수입력입니다. 3글자에서 20자까지 사용합니다.
	  </div>
		<div class="form-group">
	    <label for="pw">비밀번호:</label>
	    <input type="password" class="form-control" maxlength="20"
	    	pattern="^.{5,20}$"
	    	placeholder="비밀번호는 5-20자"
	    	id="pw" name="pw" required>
	  </div>
	  <div class="alert alert-danger" id="pwDiv">
	  	비밀번호는 필수입력입니다. 5글자에서 20자까지 사용합니다.
	  </div>
		<div class="form-group">
	    <label for="pw2">비밀번호확인:</label>
	    <input type="password" class="form-control" maxlength="20"
	    	pattern="^.{5,20}$"
	    	placeholder="비밀번호는 5-20자"
	    	id="pw2" name="pw2" required>
	  </div>
	  <div class="alert alert-danger" id="pw2Div">
	  	비밀번호확인은 필수입력입니다. 5글자에서 20자까지 사용합니다.
	  </div>
	  <div class="form-group">
	    <label for="name">이름:</label>
	    <input type="text" class="form-control" maxlength="10"
	    	pattern="^[가-힣a-zA-Z]{2,10}$"
	    	placeholder="이름은 2-10자로 입력해주세요"
	    	id="name" name="name" required>
	  </div>
	  <div class="form-group">
	  	<label>성별: </label>
		  <div class="custom-control custom-radio custom-control-inline">
		    <input type="radio" class="custom-control-input" id="man"
		    	name="gender" value="남자">
		    <label class="custom-control-label" for="man">남자</label>
		  </div>
		  <div class="custom-control custom-radio custom-control-inline">
		    <input type="radio" class="custom-control-input" id="woman"
		    	name="gender" value="여자">
		    <label class="custom-control-label" for="woman">여자</label>
		  </div>
	  </div>
	  <div class="form-group">
	  	<label for="birth">생년월일: </label>
	  	<input type="text" class="form-control datepicker" required
	  		id="birth" name="birth" readonly style="background:white;">
	  </div>
	  <div class="form-group">
	  	<label for="tel">연락처: </label>
	  	<input type="text" class="form-control"
	  		id="tel" name="tel">
	  </div>
	  <div class="form-group">
	  	<label for="email">이메일: </label>
	  	<input type="text" class="form-control"
	  		id="email" name="email">
	  </div>
	  <!-- 우편번호서비스: https://postcode.map.daum.net/guide 이용 -->
	  <div class="input-group mb-3">
		  <input type="text" class="form-control"
		  	id="zipcode" placeholder="우편번호" name="zipcode">
		  <div class="input-group-append">
		    <button class="btn btn-success" id="searchPostcode"
		    	type="button">우편번호찾기</button>
		  </div>
		</div>
	  <div class="form-group">
			<input type="text" id="addr1" name="addr1"
				class="form-control" placeholder="주소">
			<input type="text" id="addr2" name="addr2"
				class="form-control" placeholder="상세주소">
			<input type="text" id="sample6_extraAddress" placeholder="참고항목">
	  </div>


<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
</script>
	</form>
</div>
</body>
</html>





