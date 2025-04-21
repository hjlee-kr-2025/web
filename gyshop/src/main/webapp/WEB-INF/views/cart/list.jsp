<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!-- /WEB-INF/views/cart/list.jsp -->    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<script type="text/javascript">
$(function(){
	//console.log(${list});
	
	
	let total_price1 = 0;// 개별배송료
	let total_price2 = 0;// 토탈
	let total_price = 0;// 두가지를 합치는 변수
	let delivery = 0;
 	
	$(".dataRow").each(function(){
		const price = parseInt($(this).find(".price").text());
		const count = parseInt($(this).find(".count").text());
		const delivery_cost = parseInt($(this).find(".delivery_cost").text());
		const delivery_option = $(this).find(".delivery_option").data("option");
		
		if (delivery_option == '1') {
			total_price1 += (price * count) + delivery_cost;
		}
		else {
			total_price2 += (price * count);
			if (delivery < delivery_cost)
				delivery = delivery_cost; 
		}
	});
	
	if (total_price2 >= 50000) delivery = 0;// 무료배송가격을 정하면 됩니다.
	total_price = total_price1 + total_price2 + delivery;
	console.log("total_price", total_price);
	$("#total").text(total_price);
	
	$(".changeCount").click(function(){
		console.log("click event");
		let no = $(this).data("no");
		console.log("no = ", no);
		let dataRow = $(this).closest(".dataRow");
		let name = dataRow.find(".name").text();
		console.log("name = ", name);
		let count = dataRow.find(".count").text();
		console.log("count = ", count);
		
		$("#no").val(no);
		$("#name").val(name);
		$("#count").val(count);
		
		$("#updateModal").modal("show");
	});
});
</script>
</head>
<body>
<div class="container topbox">
	<h2>장바구니</h2>
	<table class="table">
		<thead>
			<tr>
				<th>제품사진</th>
				<th>제품명</th>
				<th>수량</th>
				<th>가격</th>
				<th>배송료</th>
				<th>배송료옵션</th>
				<th><button class="btn btn-danger" type="button"
					data-toggle="modal" data-target="#deleteModal">장바구니비움</button></th>
			</tr>
		</thead>
		<c:if test="${empty list }">
			<tr>
				<td colspan="7">장바구니가 비었습니다.</td>
			</tr>
		</c:if>
		<c:if test="${!empty list }">
			<c:forEach items="${list }" var="vo">
				<tr class="dataRow">
					<td><img src="${vo.photo }" width="30px" height="30px"></td>
					<td class="name">${vo.name }</td>
					<td class="count">${vo.count }</td>
					<td class="price">${vo.price }</td>
					<td class="delivery_cost">${vo.delivery_cost }</td>
					<td data-option="${vo.delivery_option }" class="delivery_option">
						<c:if test="${vo.delivery_option == 1 }">
							개별배송료
						</c:if>
					</td>
					<td>
						<button type="button" class="btn btn-primary changeCount"
					 		data-no="${vo.no }">수량변경</button>
					 	<a href="delete.do?no=${vo.no }" class="btn btn-danger">삭제</a>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="3">합계</td>
				<td colspan="3" id="total"></td>
				<td></td>
			</tr>
		</c:if>
	</table>
	
	<!-- The Modal -->
	<div class="modal" id="updateModal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	
	      <!-- Modal Header -->
	      <div class="modal-header">
	        <h4 class="modal-title">수량변경</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	
				<form action="update.do" method="post">
					<input type="hidden" name="no" id="no">
		      <!-- Modal body -->
		      <div class="modal-body">
		      	<div class="input-group">
			        <input name="name" id="name" class="form-control" readonly>
			        <div class="input-group-append">
			        	<input name="count" id="count" type="number"
			        		class="form-control">
			        </div>
		      	</div>
		      </div>
		
		      <!-- Modal footer -->
		      <div class="modal-footer">
		      	<button class="btn btn-primary">수정</button>
		        <button type="button" class="btn btn-success" data-dismiss="modal">취소</button>
		      </div>
				</form>
	    </div>
	  </div>
	</div>
	
	<!-- The Modal -->
	<div class="modal" id="deleteModal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	
	      <!-- Modal Header -->
	      <div class="modal-header">
	        <h4 class="modal-title">장바구니 비움확인</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	
				<form action="deleteAll.do" method="post">
		      <!-- Modal body -->
		      <div class="modal-body">
		      	장바구니를 비우시겠습니까?
		      </div>
		
		      <!-- Modal footer -->
		      <div class="modal-footer">
		      	<button class="btn btn-danger">장바구니비움</button>
		        <button type="button" class="btn btn-success" data-dismiss="modal">취소</button>
		      </div>
				</form>
	    </div>
	  </div>
	</div>
	
</div>
</body>
</html>