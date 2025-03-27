<%@ tag language="java" pageEncoding="UTF-8"%>
<!-- /WEB-INF/tags/pageNav.tag -->
<%@ tag trimDirectiveWhitespaces="true" %>
<!-- 위의 태그설정은 쓸데없는 공백을 제거하는 설정 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="pageObject" required="true"
	type="com.gyshop.util.page.PageObject" %>
<%@ attribute name="listURI" required="true"
	type="java.lang.String" %>

	
<ul class="pagination">
  <li data-page="${pageObject.startPage - 1 }" class="page-item">
  	<c:if test="${pageObject.startPage > 1 }">
	  	<a class="page-link"
	  		href="${listURI }?page=${pageObject.startPage - 1}&perPageNum=${pageObject.perPageNum}"
	  		><i class="fa fa-angle-left"></i></a>
  	</c:if>
  	<c:if test="${pageObject.startPage == 1 }">
	  	<a class="page-link" href="" onclick="return false"
	  		><i class="fa fa-angle-left"></i></a>
  	</c:if>
  </li>
  <!-- page의 숫자링크를 구현하는 부분  -->
  <c:forEach begin="${pageObject.startPage }"
  	end="${pageObject.endPage }" var="cnt">
	  <li data-page="${cnt }"
	  	${(pageObject.page == cnt)?"class=\"page-item active\"":"class=\"page-item\"" }>
	  	<a class="page-link"
	  		href="${listURI }?page=${cnt}&perPageNum=${pageObject.perPageNum}"
	  		>${cnt }</a>
	  </li>
  </c:forEach>
  <li data-page="${pageObject.endPage + 1 }" class="page-item">
  	<c:if test="${pageObject.endPage < pageObject.totalPage }">
  		<a class="page-link"
  			href="${listURI }?page=${pageObject.endPage + 1}&perPageNum=${pageObject.perPageNum}"
  			><i class="fa fa-angle-right"></i></a>
  	</c:if>
  	<c:if test="${pageObject.endPage == pageObject.totalPage }">
  		<a class="page-link" href="" onclick="return false"
  			><i class="fa fa-angle-right"></i></a>
  	</c:if>
  </li>
</ul>