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
	
	if (total_price2 >= 50000) delivery = 0;
	total_price = total_price1 + total_price2 + delivery;
	console.log("total_price", total_price);
	$("#total").text(total_price);
});
</script>
</head>
<body>
<div class="container topbox">
	<h2>장바구니</h2>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>제품사진</th>
				<th>제품명</th>
				<th>수량</th>
				<th>가격</th>
				<th>배송료</th>
				<th>배송료옵션</th>
			</tr>
		</thead>
		<c:if test="${empty list }">
			<tr>
				<td colspan="6">장바구니가 비었습니다.</td>
			</tr>
		</c:if>
		<c:if test="${!empty list }">
			<c:forEach items="${list }" var="vo">
				<tr class="dataRow">
					<td><img src="${vo.photo }" width="30px" height="30px"></td>
					<td>${vo.name }</td>
					<td class="count">${vo.count }</td>
					<td class="price">${vo.price }</td>
					<td class="delivery_cost">${vo.delivery_cost }</td>
					<td data-option="${vo.delivery_option }" class="delivery_option">
						<c:if test="${vo.delivery_option == 1 }">
							개별배송료
						</c:if>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="3">합계</td>
				<td colspan="3" id="total"></td>
			</tr>
		</c:if>
	</table>
</div>
</body>
</html>