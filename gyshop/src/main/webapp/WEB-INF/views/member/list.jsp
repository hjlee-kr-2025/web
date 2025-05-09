<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원리스트</title>
<script type="text/javascript">
$(function(){
	// 이벤트 - 회원리스트에서 회원정보 클릭시
	$(".dataRow").on("click", function(){
		let id = $(this).find(".id").text();
		console.log("id = ", id);
		
		// 상세보기 페이지로 이동
		location = "view.do?id=" + id;
	});
	
	// 등급과 상태변경시 click이벤트가 적용안되도록 설정
	$(".grade, .status").parent().on("mouseover", function(){
		$(".dataRow").off("click");
	}).on("mouseout", function(){
		$(".dataRow").on("click", function(){
			let id = $(this).find(".id").text();
			location = "view.do?id=" + id;
		});
	});
	
	// 변경버튼 활성화하는 부분
	$(".dataRow").on("change", ".grade, .status", function(){
		// 변경된 값을 알아냅니다.
		let changeData = $(this).val();
		// 원래데이터
		let data = $(this).data("data");
		console.log("changeData: ", changeData);
		console.log("data: ", data);
		if (changeData == data) {
			$(this).next().find("button").prop("disabled", true);
			// 버튼 비활성화
		}
		else {
			$(this).next().find("button").prop("disabled", false);
			// 버튼 활성화
		}
	});
	
	
});
</script>
</head>
<body>
<div class="container topbox">
	<h2><i class="fa fa-list"></i> 회원리스트</h2>
	<table class="table table-hover">
		<!-- 테이블의 제목줄 -->
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>생년월일</th>
			<th>연락처</th>
			<th>등급</th>
			<th>상태</th>
		</tr>
		<!-- 실제 데이터베이스에 저장된 데이터 -->
		<c:forEach items="${list}" var="vo">
			<tr class="dataRow">
				<td class="id">${vo.id }</td>
				<td>${vo.name }</td>
				<td>${vo.birth }</td>
				<td>${vo.tel }</td>
				<td>
					<!-- 회원등급 변경할 수 있는 메뉴 -->
					<form action="changeGradeNo.do">
						<input type="hidden" name="id" value="${vo.id }">
						<div class="input-group">
							<select class="form-control grade" name="gradeNo"
								data-data="${vo.gradeNo }">
								<option value="1" ${(vo.gradeNo == 1)?"selected":"" }>일반회원</option>
								<option value="99" ${(vo.gradeNo == 99)?"selected":"" }>관리자</option>
							</select>
							<div class="input-group-append">
								<button class="btn btn-primary" disabled>변경</button>
							</div>
						</div>
					</form>
				</td>
				<td>
					<form action="changeStatus.do">
						<input type="hidden" name="id" value="${vo.id }">
						<div class="input-group">
							<select class="form-control status" name="status"
								data-data="${vo.status }">
								<option ${(vo.status == "정상")?"selected":"" }>정상</option>
								<option ${(vo.status == "탈퇴")?"selected":"" }>탈퇴</option>
								<option ${(vo.status == "휴면")?"selected":"" }>휴면</option>
								<option ${(vo.status == "강퇴")?"selected":"" }>강퇴</option>
							</select>
							<div class="input-group-append">
								<button class="btn btn-primary" disabled>변경</button>
							</div>
						</div>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>