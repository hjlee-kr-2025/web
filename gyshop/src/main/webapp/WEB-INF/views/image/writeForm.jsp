<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- /WEB-INF/views/image/writeForm.jsp -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gallery</title>
<script type="text/javascript">
$(function(){
	// 이미지미리보기
	$("#imageFile").change(function(){
		let file = this.files[0];
		let reader = new FileReader();
		// 파일로딩이 끝나면 실행코드
		reader.onloadend = function() {
			$("#viewImage").attr("src", reader.result);
		}
		// 파일을 읽어오는 명령
		reader.readAsDataURL(file);
	});
});
</script>
</head>
<body>
<div class="container topbox">
	<h2><i class="fa fa-edit"></i> Gallery 등록</h2>
	<form action="write.do" method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label for="title">제목</label>
			<input class="form-control" id="title" name="title"
				pattern="^[^ .].{1,99}$"
				placeholder="제목을 입력하세요 (2~100자)" required>
		</div>
		<div class="form-group">
			<label for="content">내용</label>
			<textarea class="form-control" rows="7"
				id="content" name="content" required
				placeholder="내용을 입력하세요."
			></textarea>
		</div>
		<div class="form-group">
			<label for="imageFile">첨부이미지</label>
			<input class="form-control" id="imageFile" name="imageFile"
				type="file" required
			>
			<div>
				<img src="" id="viewImage">
			</div>
		</div>
		<button type="submit" class="btn btn-primary">등록</button>
		<button type="reset" class="btn btn-secondary">새로입력</button>
		<button type="button" class="btn btn-success"
			onclick="history.back();"
		>취소</button>
	</form>
</div>
</body>
</html>