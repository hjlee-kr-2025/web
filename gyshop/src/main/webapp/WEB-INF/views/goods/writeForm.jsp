<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- /WEB-INF/views/goods/writeForm.jsp -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품등록</title>
</head>
<body>
<div class="container topbox">
	<h2><i class="fa fa-edit"></i> 상품 등록</h2>
	<form action="write.do" method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label for="name">상품이름</label>
			<input id="name" name="name" class="form-control"
				required placeholder="상품이름을 입력해주세요.">
		</div>
		<div class="form-group">
			<label for="content">상품상세설명</label>
			<textarea rows="5" class="form-control"
				id="content" name="content"
				placeholder="상품에 대한 상세설명을 입력해주세요."></textarea>
		</div>
		<div class="form-group">
			<label for="price">상품가격</label>
			<input id="price" name="price" required placeholder="숫자만입력하세요."
				pattern="^[1-9][0-9]{1,9}$" maxlength="10" class="form-control">
		</div>
		<div class="form-group">
			<label for="delivery_cost">배송료</label>
			<input id="delivery_cost" name="delivery_cost" required 
				placeholder="숫자만 입력하세요." pattern="^[0-9]{1,6}"
				maxlength="6" class="form-control">
		</div>
		<div class="form-group">
			<label for="modelNo">모델No</label>
			<input id="modelNo" name="modelNo" required
				placeholder="모델No를 입력하세요." class="form-control">
		</div>
		<div class="form-group">
			<label for="photo">상품대표이미지</label>
			<input id="photo" name="photo" class="form-control"
				type="file">
			<div>
				<img id="photoImg" src="">
			</div>
		</div>
		<div class="form-group">
			<label for="subPhoto1">상품 추가이미지1</label>
			<input id="subPhoto1" name="subPhoto1" class="form-control"
				type="file">
			<div>
				<img id="photoImg1" src="">
			</div>
		</div>
		<div class="form-group">
			<label for="subPhoto2">상품 추가이미지2</label>
			<input id="subPhoto2" name="subPhoto2" class="form-control"
				type="file">
			<div>
				<img id="photoImg2" src="">
			</div>
		</div>
		<div class="form-group">
			<label for="subPhoto3">상품 추가이미지3</label>
			<input id="subPhoto3" name="subPhoto3" class="form-control"
				type="file">
			<div>
				<img id="photoImg3" src="">
			</div>
		</div>
		<div class="form-group">
			<label for="subPhoto4">상품 추가이미지4</label>
			<input id="subPhoto4" name="subPhoto4" class="form-control"
				type="file">
			<div>
				<img id="photoImg4" src="">
			</div>
		</div>
		
		<!-- button의 기본 type은 submit 입니다. -->
		<button class="btn btn-primary">등록</button>
		<button type="reset" class="btn btn-success">새로입력</button>
		<button type="button" class="btn btn-secondary"
			onclick="history.back();">취소</button>
	</form>
</div>
</body>
</html>