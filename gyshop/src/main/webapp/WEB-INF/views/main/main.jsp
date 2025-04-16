<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
<script type="text/javascript">
$(function(){
	let imgWidth = $(".imageDiv:first").width();
	let imgHeight = $(".imageDiv:first").height();
	
	// box의 가로는 이미지가로와 동일하게 처리
	// 높이는 비율로 (가로 : 세로 = 4 : 3)
	/* 세로 * 4 = 가로 * 3
			세로 = 가로 * 3 / 4
	*/
	let height = imgWidth * 3 / 4;
	console.log("height : ", height);
	// box크기를 동일하게 만들어 줍니다.
	$(".imageDiv").height(height);
	// box크기와 맞춰 이미지크기를 비율로 조절해 줍니다.
	$(".imageDiv > img").each(function(idx, image){
		if ($(image).height() > height) {
			// 현재 이미지크기수집
			let image_width = $(image).width();
			let image_height = $(image).height();
			// 변경될 가로 사이즈  (width : height = image_width : image_height)
			let width = height * image_width / image_height;
			// 이미지 크기 변경
			$(image).width(width);
			$(image).height(height);
		}
	});
	
	// 게시글을 클릭했을때 이벤트 처리
	$(".dataRow").click(function(){
		// 글번호 수집
		let no = $(this).find(".no").text();
		console.log("no: ", no);
		// 모듈체크(공지사항(.notice), 일반게시판(.board), Gallery(.image))
		if ($(this).hasClass("notice")) {
			console.log("공지사항입니다.");
			// 페이지를 이동합니다.
			location = "/notice/view.do?no=" + no + "&inc=1";
		}
		else if ($(this).hasClass("board")) {
			console.log("일반게시판입니다.");
			location = "/board/view.do?no=" + no + "&inc=1";
		}
		else if ($(this).hasClass("image")) {
			console.log("Gallery입니다.");
			location = "/image/view.do?no=" + no + "&inc=1";
		}
	});
	
	
});
</script>
</head>
<body>
<div class="container topbox">
	<h2>MAIN</h2>
	<div class="row">
		<div class="col-sm-6">
			<!-- 공지사항 리스트 (5) -->
			<!-- 제목/게시종료일/조회수 -->
			<h4>공지사항 리스트</h4>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>게시종료일</th>
						<th>조회수</th>
					</tr>
				</thead>
				<c:forEach items="${noticeList }" var="noticeVO">
					<tr class="dataRow notice">
						<td class="no">${noticeVO.no }</td>
						<td>
							<c:choose>
								<c:when test="${fn:length(noticeVO.title) > 10 }">
									${fn:substring(noticeVO.title, 0, 10) }...
								</c:when>
								<c:otherwise>
									${noticeVO.title }
								</c:otherwise>
							</c:choose>
						</td>
						<td>${noticeVO.endDate }</td>
						<td>${noticeVO.hit }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="col-sm-6">
			<!-- 일반게시판 리스트 (5) -->
			<!-- 제목/작성자/조회수 -->
			<h4>일반게시판 리스트</h4>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>조회수</th>
					</tr>
				</thead>
				<c:forEach items="${boardList }" var="boardVO">
					<tr class="dataRow board">
						<td class="no">${boardVO.no }</td>
						<td>${boardVO.title }</td>
						<td>${boardVO.writer }</td>
						<td>${boardVO.hit }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<!-- 이미지게시판 리스트 (3) -->
			<h4>이미지게시판 리스트</h4>
			<c:if test="${empty imageList }">
				<h5>데이터가 존재하지 않습니다.</h5>
			</c:if>
			<c:if test="${!empty imageList }">
				<%-- gallery list 구현 --%>
				<div class="row mb-2">
					<c:forEach items="${imageList }" var="vo" varStatus="vs">
						<!-- 3개표시후 줄을 바꾸도록 구현해 줍니다. -->
						<c:if test="${(vs.index != 0) && (vs.index%3 == 0)}">
							${"</div>"}
							${"<div class='row mb-2'>"}
						</c:if>
						<!-- 데이터표시 시작 -->
						<div class="col-sm-4 dataRow image">
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
		</div>
	</div>
</div>
</body>
</html>