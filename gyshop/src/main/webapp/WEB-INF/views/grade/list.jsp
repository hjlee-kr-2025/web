<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- /webapp/WEB-INF/views/grade/list.jsp -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 등급 관리</title>
<script type="text/javascript">
$(function(){
	// 이곳에 javascript코드를 구현합니다.
	// body가 화면에 로딩된 후 이곳이 처리됩니다.
	// 리스트화면에서 "등록"버튼 클릭시
	$("#writeBtn").click(function(){
		console.log("writeBtn event----");
		// click이 발생되었을때 처리
		$("#title").text("회원 등급 등록");
		// input 창의 값을 초기화
		$("#gradeNo").val("");
		$("#gradeName").val("");
		// 회원등급번호 -> readonly 속성 삭제
		$("#gradeNo").removeAttr("readonly");
		$("#gradeName").removeAttr("readonly");
		
		// 버튼
		// form태그안의 모든 버튼을 보여줍니다.
		$("#gradeForm button").show();
		// form태그안의 "수정","삭제" 버튼을 안보이도록 설정
		$("#gradeUpdateBtn, #gradeDeleteBtn").hide();
		
		// 모달창 show(보이도록)
		$("#gradeModal").modal("show");
	});
	// 리스트화면에서 "수정"버튼 클릭시
	// 리스트마다 버튼이 있어서 class로 updateBtn을 부여했습니다.
	$(".updateBtn").click(function(){
		$("#title").text("회원 등급 수정")
		let dataRow = $(this).closest(".dataRow");
		let gradeNo = dataRow.find(".no").text();
		let gradeName = dataRow.find(".name").text();
		// gradeNo, gradeName 데이터 확인
		console.log("gradeNo = ", gradeNo);
		console.log("gradeName = ", gradeName);
		// 수정전 기존 회원등급번호, 회원등급이름 세팅
		$("#gradeNo").val(gradeNo);
		$("#gradeName").val(gradeName);
		// 회원등급번호 -> readonly
		$("#gradeNo").attr("readonly", "true");
		$("#gradeName").removeAttr("readonly");
		
		// 버튼
		// form태그안의 모든 버튼을 보여줍니다.
		$("#gradeForm button").show();
		// form태그안의 "등록", "삭제" 버튼을 안보이도록 설정
		$("#gradeWriteBtn, #gradeDeleteBtn").hide();
		
		
		// 모달창 open
		$("#gradeModal").modal("show");
	});
	// 리스트화면에서 "삭제"버튼 클릭시
	$(".deleteBtn").click(function(){
		$("#title").text("회원 등급 삭제")
		let dataRow = $(this).closest(".dataRow");
		let gradeNo = dataRow.find(".no").text();
		let gradeName = dataRow.find(".name").text();
		// gradeNo, gradeName 데이터 확인
		console.log("gradeNo = ", gradeNo);
		console.log("gradeName = ", gradeName);
		// 수정전 기존 회원등급번호, 회원등급이름 세팅
		$("#gradeNo").val(gradeNo);
		$("#gradeName").val(gradeName);
		// 회원등급번호 -> readonly
		$("#gradeNo").attr("readonly", "true");
		$("#gradeName").attr("readonly", "true");
		
		// 버튼
		// form태그안의 모든 버튼을 보여줍니다.
		$("#gradeForm button").show();
		// form태그안의 "등록", "수정" 버튼을 안보이도록 설정
		$("#gradeWriteBtn, #gradeUpdateBtn").hide();
		
		
		// 모달창 open
		$("#gradeModal").modal("show");
	});
	
	
	
	
	// 모달창 이벤트 모음
	
	// 모달창에서 "등록"버튼 클릭시
	$("#gradeWriteBtn").click(function(){
		// 이동할 경로 지정
		$("#gradeForm").attr("action", "/grade/write.do");
		// 데이터 전송 및 처리
		$("#gradeForm").submit();
	});
	
	// 모달창에서 "수정"버튼 클릭시
	$("#gradeUpdateBtn").click(function(){
		// 이동할 경로 지정
		$("#gradeForm").attr("action", "/grade/update.do");
		// 데이터 전송 및 처리
		$("#gradeForm").submit();
	});
	
	// 모달창에서 "삭제"버튼 클릭시
	$("#gradeDeleteBtn").click(function(){
		// 이동할 경로 지정
		$("#gradeForm").attr("action", "/grade/delete.do");
		// 데이터 전송 및 처리
		$("#gradeForm").submit();
	});
});
</script>
</head>
<body>
<div class="container topbox">
	<h2><i class="fa fa-list"></i> 회원 등급 리스트</h2>
	<table class="table table-hover">
    <thead><!-- 칼럼의 제목 -->
      <tr>
        <th>회원 등급 번호</th>
        <th colspan="2">회원 등급 이름</th>
      </tr>
    </thead>
    <tbody>
    	<c:forEach items="${list }" var="vo">
    		<tr class="dataRow">
    			<td class="no">${vo.gradeNo }</td>
    			<td class="name">${vo.gradeName }</td>
    			<td style="text-align: right;">
    				<button class="btn btn-success updateBtn"
    					>수정</button>
    				<button class="btn btn-danger deleteBtn"
    					>삭제</button>
    			</td>
    		</tr>
    	</c:forEach>
    </tbody>
    <tfoot>
    	<tr>
    		<td colspan="3" style="text-align:right;">
    			<button class="btn btn-primary" id="writeBtn">등록</button>
    		</td>
    	<tr>
    </tfoot>
  </table>
  
  
  <!-- The Modal -->
	<div class="modal" id="gradeModal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	
	      <!-- Modal Header -->
	      <div class="modal-header">
	        <h4 class="modal-title" id="title">회원 등급 등록</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	
				<form id="gradeForm" method="post">
		      <!-- Modal body -->
		      <div class="modal-body">
		        <div class="form-group">
		        	<label for="gradeNo">회원등급번호</label>
		        	<input id="gradeNo" name="gradeNo" type="text"
		        		placeHolder="1부터 99까지 입력하세요." required
		        		class="form-control">
		        </div>
		        <div class="form-group">
		        	<label for="gradeName">회원등급이름</label>
		        	<input id="gradeName" name="gradeName" type="text"
		        		placeHolder="한글로 10자까지 입력가능합니다." required
		        		class="form-control">
		        </div>
		      </div>
		
		      <!-- Modal footer -->
		      <div class="modal-footer">
		      	<button type="button" class="btn btn-primary"
		      		id="gradeWriteBtn">등록</button>
		      	<button type=button" class="btn btn-success"
		      		id="gradeUpdateBtn">수정</button>
		      	<button type="button" class="btn btn-danger"
		      		id="gradeDeleteBtn">삭제</button>
		        <button type="button" class="btn btn-warning"
		        	data-dismiss="modal">취소</button>
		      </div>
				</form>
	    </div>
	  </div>
	</div>
</div>
</body>
</html>