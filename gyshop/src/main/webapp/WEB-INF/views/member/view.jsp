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
				<c:if test="${empty vo.photo }">
					<i class="fa fa-user-circle" style="font-size:50px;"></i>
				</c:if>
				<c:if test="${!empty vo.photo }">
					<img src="${vo.photo }" style="width:50px;height:50px;">
				</c:if>
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
</div>
</body>
</html>