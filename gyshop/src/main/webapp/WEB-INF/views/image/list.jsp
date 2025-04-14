<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	
});
</script>
</head>
<body>
<div class="container topbox">
	<h2><i class="fa fa-list"></i> Gallery</h2>
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
</div>
</body>
</html>