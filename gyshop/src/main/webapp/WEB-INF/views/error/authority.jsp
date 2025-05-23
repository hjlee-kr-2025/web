<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- /WEB-INF/views/error/authority.jsp -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>권한오류</title>
<style>
#errorDiv>.row {
	padding: 10px;
	border-top: 1px dotted #ccc;
	margin: 0px 10px;
}
</style>
</head>
<body>
<div class="container topbox">
	<div class="card">
		<div class="card-header">
			<h3><i class="fa fa-times-circle"></i> 권한오류</h3>
		</div>
		<div class="card-body" id="errorDiv">
			<div class="text-center">
				<i class="material-icons" style="font-size:100px;color:red">error</i>
			</div>
			<div class="row">
				<div class="col-sm-3"><i class="fa fa-check"></i> 요청 URI</div>
				<div class="col-sm-9">${uri }</div>
			</div>
			<div class="row">
				<div class="col-sm-3"><i class="fa fa-check"></i> 오류 메시지</div>
				<div class="col-sm-9">요청하신 페이지에 접근할 권한 없습니다.</div>
			</div>
			<div class="row">
				<div class="col-sm-3"><i class="fa fa-check"></i> 조치 사항</div>
				<div class="col-sm-9">
					로그인 등급을 확인해 주세요.<br>오류가 있는 경우 관리자에게 문의하세요.
				</div>
			</div>
		</div>
		<div class="card-footer">
			<span class="float-right">
				<a href="/board/list.do" class="btn btn-primary">메인으로가기</a>
			</span>
		</div>
	</div>
</div>
</body>
</html>