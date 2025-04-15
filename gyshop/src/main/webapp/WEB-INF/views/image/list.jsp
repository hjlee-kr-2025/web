<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>
<!-- /WEB-INF/views/image/list.jsp -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gallery</title>
<script>
$(function(){
	let imgWidth = $(".imageDiv:first").width();
	let imgHeight = $(".imageDiv:first").height();
	console.log("imgWidth: ", imgWidth);
	console.log("imgHeight: ", imgHeight);
	
	// box 가로size는 imgWidth로 동일
	// 세로size는 비율로 정해줍니다. 
	let height = imgWidth / 4 * 3;  //(가로 : 세로 = 4 : 3)
	console.log("height: ", height);
	$(".imageDiv").height(height);
	
	$(".imageDiv > img").each(function(idx, image){
		if($(image).height() > height) {
			// 이미지 Size가 box Size보다 크면 이곳에서 이미지를 줄여줍니다.
			let image_width = $(image).width();
			let image_height = $(image).height();
			// 변경될 가로 size (width : height = image_width : image_height)
			let width = height / image_height * image_width;
			// 이미지크기를 줄입니다.
			$(image).width(width);
			$(image).height(height);
		}
	});
	
	// 리스트 클릭 이벤트
	$(".dataRow").click(function(){
		// 글번호 수집
		let no = $(this).find(".no").text();
		console.log("no: ", no);
		// 갤러리글 상세보기로 이동
		location = "view.do?no=" + no + "&inc=1";
	});
	
	// select 태그의 데이터가 변경되었을때
	$("#perPageNum, #orderStyle").change(function(){
		$("#searchForm").submit();
	});
	
	// 초기데이터 세팅
	$("#perPageNum")
		.val("${(empty pageObject.perPageNum)?'6':pageObject.perPageNum}");
	
	$("#orderStyle")
		.val("${(empty pageObject.orderStyle)?'1':pageObject.orderStyle}");
	$("#key")
		.val("${(empty pageObject.key)?'t':pageObject.key}");
	
});
</script>
</head>
<body>
<div class="container topbox">
	<h2><i class="fa fa-list"></i> Gallery</h2>
	<form action="list.do" id="searchForm">
		<div class="row">
			<div class="col-sm-6">
				<div class="input-group mb-3 mt-3">
					<div class="input-group-prepend">
						<select class="form-control" id="key" name="key">
							<option value="t">제목</option>
							<option value="c">내용</option>
							<option value="w">작성자</option>
							<option value="tc">제목/내용</option>
							<option value="tw">제목/작성자</option>
							<option value="cw">내용/작성자</option>
							<option value="tcw">모두</option>
						</select>
					</div>
					<input type="text" class="form-control" placeholder="Search"
						id="word" name="word" value="${pageObject.word }">
					<div class="input-group-append">
						<button class="btn btn-primary" type="submit"
							><i class="fa fa-search"></i></button>
					</div>
				</div>
			</div>
			<div class="col-sm-3">
				<!-- 정렬순서 지정 -->
				<div class="input-group mb-3 mt-3">
					<div class="input-group-prepend">
						<span class="input-group-text">정렬방법</span>
					</div>
					<select class="form-control" id="orderStyle" name="orderStyle">
						<option value="1">최신글</option>
						<option value="2">과거글</option>
						<option value="3">조회수</option>
					</select>
				</div>
			</div>
			<div class="col-sm-3">
				<!-- 한페이지의 게시글 수 지정 -->
				<div class="input-group mb-3 mt-3">
					<div class="input-group-prepend">
						<span class="input-group-text">Rows/page</span>
					</div>
					<select class="form-control" id="perPageNum" name="perPageNum">
						<option>3</option>
						<option>6</option>
						<option>9</option>
						<option>12</option>
					</select>
				</div>
			</div>
		</div>
	</form>
	<c:if test="${empty list }">
		<h4>데이터가 존재하지 않습니다.</h4>
	</c:if>
	<c:if test="${!empty list }">
		<%-- gallery list 구현 --%>
		<div class="row mb-2">
			<c:forEach items="${list }" var="vo" varStatus="vs">
				<!-- 3개표시후 줄을 바꾸도록 구현해 줍니다. -->
				<c:if test="${(vs.index != 0) && (vs.index%3 == 0)}">
					${"</div>"}
					${"<div class='row mb-2'>"}
				</c:if>
				<!-- 데이터표시 시작 -->
				<div class="col-sm-4 dataRow">
					<div class="card" style="width:100%">
						<div class="imageDiv text-center align-content-center">
							<img class="card-img-top" src="${vo.fileName }" alt="Card image">
						</div>
				    <div class="card-body">
				      <h4 class="card-title">
				      	<span class="float-right" style="font-size:14px;">${vo.writeDate }</span>
				      	${vo.name }
				      </h4>
				      <p class="card-text">
				      	<span class="float-right">조회수: ${vo.hit }</span>
				      	<span class="no">${vo.no }</span>. ${vo.title }
				      </p>
				    </div>					
					</div>
				</div>
			</c:forEach>
		</div>
	</c:if>
	<c:if test="${!empty login }">
		<div>
			<a href="writeForm.do" class="btn btn-primary">등록</a>
		</div>
	</c:if>
	<div>
		<pageNav:pageNav listURI="list.do" pageObject="${pageObject }"></pageNav:pageNav>
	</div>
</div>
</body>
</html>