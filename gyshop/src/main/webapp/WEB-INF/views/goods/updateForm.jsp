<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!-- /WEB-INF/views/goods/updateForm.jsp -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품수정</title>
<script type="text/javascript">
$(function(){
	// 이곳에 구현된 코드는 웹페이지가 로딩된 후에 실행이 됩니다.
	// 이미지 미리보기
	$("#photo, #subPhoto1, #subPhoto2, #subPhoto3, #subPhoto4").change(function(){
		const element = $(this).closest(".form-group").find("img");
		// img 태그를 가리키는 상수입니다.
		let file = this.files[0];
		let reader = new FileReader();
		reader.onloadend = function() {
			element.attr("src", reader.result);
		}
		reader.readAsDataURL(file);
	});
	
	
});
</script>
</head>
<body>
<div class="container topbox">
	<h2><i class="fa fa-edit"></i> 상품 정보 수정</h2>
	<form action="update.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="no" value="${vo.no }">
		<input type="hidden" name="deletePhoto" value="${vo.photo }">
		<input type="hidden" name="deleteSubPhoto1" value="${vo.subPhoto1 }">
		<input type="hidden" name="deleteSubPhoto2" value="${vo.subPhoto2 }">
		<input type="hidden" name="deleteSubPhoto3" value="${vo.subPhoto3 }">
		<input type="hidden" name="deleteSubPhoto4" value="${vo.subPhoto4 }">
		<div class="form-group">
			<label for="name">상품이름</label>
			<input id="name" name="name" class="form-control"
				required placeholder="상품이름을 입력해주세요."
				value="${vo.name }">
		</div>
		<div class="form-group">
			<label for="content">상품상세설명</label>
			<textarea rows="5" class="form-control"
				id="content" name="content"
				placeholder="상품에 대한 상세설명을 입력해주세요.">${vo.content }</textarea>
		</div>
		<div class="form-group">
			<label for="price">상품가격</label>
			<input id="price" name="price" required placeholder="숫자만입력하세요."
				pattern="^[1-9][0-9]{1,9}$" maxlength="10" class="form-control"
				value="${vo.price }">
		</div>
		<div class="form-group">
			<label for="delivery_cost">배송료</label>
			<input id="delivery_cost" name="delivery_cost" required 
				placeholder="숫자만 입력하세요." pattern="^[0-9]{1,6}"
				maxlength="6" class="form-control"
				value="${vo.delivery_cost }">
		</div>
		<div class="form-group">
			<label for="modelNo">모델No</label>
			<input id="modelNo" name="modelNo" required
				placeholder="모델No를 입력하세요." class="form-control"
				value="${vo.modelNo }">
		</div>
		<div class="form-group">
			<label for="photo">상품대표이미지</label>
			<input id="photo" name="photo" class="form-control"
				type="file">
			<div>
				<img class="fileImage" src="${vo.photo }">
			</div>
		</div>
		<div class="form-group">
			<label for="subPhoto1">상품 추가이미지1</label>
			<input id="subPhoto1" name="subPhoto1" class="form-control"
				type="file">
			<div>
				<c:if test="${!empty vo.subPhoto1 }">
					<img class="fileImage" src="${vo.subPhoto1 }">
				</c:if>
				<c:if test="${empty vo.subPhoto1 }">
					<img class="fileImage" src="">
				</c:if>
			</div>
		</div>
		<div class="form-group">
			<label for="subPhoto2">상품 추가이미지2</label>
			<input id="subPhoto2" name="subPhoto2" class="form-control"
				type="file">
			<div>
				<c:if test="${!empty vo.subPhoto2 }">
					<img class="fileImage" src="${vo.subPhoto2 }">
				</c:if>
				<c:if test="${empty vo.subPhoto2 }">
					<img class="fileImage" src="">
				</c:if>
			</div>
		</div>
		<div class="form-group">
			<label for="subPhoto3">상품 추가이미지3</label>
			<input id="subPhoto3" name="subPhoto3" class="form-control"
				type="file">
			<div>
				<c:if test="${!empty vo.subPhoto3 }">
					<img class="fileImage" src="${vo.subPhoto3 }">
				</c:if>
				<c:if test="${empty vo.subPhoto3 }">
					<img class="fileImage" src="">
				</c:if>
			</div>
		</div>
		<div class="form-group">
			<label for="subPhoto4">상품 추가이미지4</label>
			<input id="subPhoto4" name="subPhoto4" class="form-control"
				type="file">
			<div>
				<c:if test="${!empty vo.subPhoto4 }">
					<img class="fileImage" src="${vo.subPhoto4 }">
				</c:if>
				<c:if test="${empty vo.subPhoto4 }">
					<img class="fileImage" src="">
				</c:if>
			</div>
		</div>
		
		<!-- button의 기본 type은 submit 입니다. -->
		<button class="btn btn-primary">수정</button>
		<button type="reset" class="btn btn-success" id="resetBtn">새로입력</button>
		<button type="button" class="btn btn-secondary"
			onclick="history.back();">취소</button>
	</form>
</div>
</body>
</html>