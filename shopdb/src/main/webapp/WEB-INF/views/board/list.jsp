<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반게시판 리스트</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <script type="text/javascript">
  $(function(){
	  // 이 안에 javascript 코드를 구현하면
	  // body 가 브라우저에 보여진 이후 실행이 됩니다.
	  // jquery 라이브러리를 사용하였습니다.
	  // jquery로 이벤트 부여
	  $(".dataRow").click(function() {
		  let no = $(this).find(".no").text();
			//alert("리스트 클릭!! " + no);
			// 글보기로 이동
			location="view.do?no="+no+"&inc=1";
			// ==> localhost/board/view.do?no=번호&inc=1
	  });
	  
	  
  });
  </script>
</head>
<body>

<div class="container">
  <h2><i class="fa fa-apple"></i> 일반 게시판 리스트</h2>
  <table class="table table-hover">
    <thead class="thead-dark">
      <tr>
        <th>글번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일</th>
        <th>조회수</th>
      </tr>
    </thead>
    <tbody>
    	<c:forEach items="${list }" var="vo">
	      <tr class="dataRow">
	        <td class="no">${vo.no }</td>
	        <td>${vo.title }</td>
	        <td>${vo.writer }</td>
	        <td>${vo.writeDate }</td>
	        <td>${vo.hit }</td>
	      </tr>
			</c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>