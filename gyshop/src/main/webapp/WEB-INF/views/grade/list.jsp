<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- /webapp/WEB-INF/views/grade/list.jsp -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 등급 관리</title>
</head>
<body>
<div class="container topbox">
	<h2><i class="fa fa-list"></i> 회원 등급 리스트</h2>
	<table class="table table-hover">
    <thead><!-- 칼럼의 제목 -->
      <tr>
        <th>회원 등급 번호</th>
        <th>회원 등급 이름</th>
      </tr>
    </thead>
    <tbody>
    	<c:forEach items="${list }" var="vo">
    		<tr class="dataRow">
    			<td class="no">${vo.gradeNo }</td>
    			<td>${vo.gradeName }</td>
    		</tr>
    	</c:forEach>
    </tbody>
    <tfoot>
    	<tr>
    		<td colspan="2" style="text-align:right;">
    			<button class="btn btn-primary" id="writeBtn">등록</button>
    		</td>
    	<tr>
    </tfoot>
  </table>
  
  
  <!-- The Modal -->
	<div class="modal" id="gradeModal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	
	      <!-- Modal Header -->
	      <div class="modal-header">
	        <h4 class="modal-title" id="title">회원 등급 등록</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	
				<form id="gradeForm" method="post">
		      <!-- Modal body -->
		      <div class="modal-body">
		        <div class="form-group">
		        	<label for="gradeNo">회원등급번호</label>
		        	<input id="gradeNo" name="gradeNo" type="text"
		        		placeHolder="1부터 99까지 입력하세요." required
		        		class="form-control">
		        </div>
		        <div class="form-group">
		        	<label for="gradeName">회원등급이름</label>
		        	<input id="gradeName" name="gradeName" type="text"
		        		placeHolder="한글로 10자까지 입력가능합니다." required
		        		class="form-control">
		        </div>
		      </div>
		
		      <!-- Modal footer -->
		      <div class="modal-footer">
		      	<button type="button" class="btn btn-primary"
		      		id="gradeWriteBtn">등록</button>
		        <button type="button" class="btn btn-warning"
		        	data-dismiss="modal">취소</button>
		      </div>
				</form>
	    </div>
	  </div>
	</div>
</div>
</body>
</html>