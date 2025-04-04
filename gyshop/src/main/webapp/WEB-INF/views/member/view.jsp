<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- /WEB-INF/views/member/view.jsp -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[${vo.id }] 회원정보</title>
</head>
<body>
<div class="container topbox">
	<div class="card">
		<div class="card-header">
			<div>
				<button type="button" style="border:none;"
					data-toggle="modal" data-target="#imageChangeModal">
					<c:if test="${empty vo.photo }">
						<i class="fa fa-user-circle" style="font-size:50px;"></i>
					</c:if>
					<c:if test="${!empty vo.photo }">
						<img src="${vo.photo }" style="width:50px;height:50px;">
					</c:if>
				</button>
				${vo.name } (${vo.id })
			</div>
		</div>
		<div class="card-body">
			<div class="row">
				<!-- bootstrap grid 에서 한줄은 12개로 나눌 수 있습니다. -->
				<div class="col-sm-3"><i class="fa fa-check"></i> 성별</div>
				<div class="col-sm-9">${vo.gender }</div>
			</div>
			<div class="row">
				<div class="col-sm-3"><i class="fa fa-check"></i> 생년월일</div>
				<div class="col-sm-9">${vo.birth }</div>
			</div>
			<div class="row">
				<div class="col-sm-3"><i class="fa fa-check"></i> 연락처</div>
				<div class="col-sm-9">${vo.tel }</div>
			</div>
			<div class="row">
				<div class="col-sm-3"><i class="fa fa-check"></i> 이메일</div>
				<div class="col-sm-9">${vo.email }</div>
			</div>
			<div class="row">
				<div class="col-sm-3"><i class="fa fa-check"></i> 우편번호</div>
				<div class="col-sm-9">${vo.zipcode }</div>
			</div>
			<div class="row">
				<div class="col-sm-3"><i class="fa fa-check"></i> 주소</div>
				<div class="col-sm-9">${vo.addr1 }</div>
			</div>
			<div class="row">
				<div class="col-sm-3"><i class="fa fa-check"></i> 상세주소</div>
				<div class="col-sm-9">${vo.addr2 }</div>
			</div>
			<div class="row">
				<div class="col-sm-3"><i class="fa fa-check"></i> 가입일</div>
				<div class="col-sm-9">${vo.regDate }</div>
			</div>
			<div class="row">
				<div class="col-sm-3"><i class="fa fa-check"></i> 최근접속일</div>
				<div class="col-sm-9">${vo.conDate }</div>
			</div>
			<div class="row">
				<div class="col-sm-3"><i class="fa fa-check"></i> 회원등급</div>
				<div class="col-sm-9">${vo.gradeName }</div>
			</div>
			<div class="row">
				<div class="col-sm-3"><i class="fa fa-check"></i> 회원상태</div>
				<div class="col-sm-9">${vo.status }</div>
			</div>
		</div>
		<div class="card-footer">
			<a href="/member/updateForm.do?id=${vo.id }"
				class="btn btn-primary">수정</a>
			<button type="button" class="btn btn-danger"
				id="deleteBtn">탈퇴</button>
			<button class="btn btn-success"
				onclick="history.back();">되돌아가기</button>
		</div>
	</div>
	
	<!-- The Modal -->
	<div class="modal" id="imageChangeModal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	
	      <!-- Modal Header -->
	      <div class="modal-header">
	        <h4 class="modal-title">바꿀사진(이미지) 선택하기</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	

				<!-- 서버에 전송되는 데이터는 form 태그안에 구성합니다. -->
				<!-- 사진을 교체하려면 어떤데이터가 서버로 전송되어야 할까요?
					1. 바꿀사진
					2. id
					3. 확인용 비밀번호: pw
					4. 기존 이미지(사진) 경로 : 지워야하니까.
					화면에 보여져야할 것 - 바꿀사진, pw
				 -->
				<form action="changePhoto.do" method="post"
					enctype="multipart/form-data">
					<!-- 숨겨서 넘길 데이터: id, 기존파일경로 -->
					<input type="hidden" name="id" value="${vo.id }">
					<input type="hidden" name="deleteFileName" value="${vo.photo }">
		      <!-- Modal body -->
		      <div class="modal-body">
		        <div class="form-group">
		        	<label for="imageFile">바꿀이미지</label>
		        	<input type="file" class="form-control" id="imageFile"
		        		name="imageFile" required>
		        </div>
		        <div class="form-group">
		        	<label for="pw">비밀번호확인</label>
		        	<input type="password" class="form-control"
		        		id="pw" name="pw" required>
		        </div>
		      </div>
		
		      <!-- Modal footer -->
		      <div class="modal-footer">
		      	<button class="btn btn-primary">바꾸기</button>
		        <button type="button" class="btn btn-secondary"
		        	data-dismiss="modal">취소</button>
		      </div>
				</form>
	    </div>
	  </div>
	</div>
	
	
</div>
</body>
</html>