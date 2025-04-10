<%@ tag language="java" pageEncoding="UTF-8"%>
<!-- /WEB-INF/tags/pageNav.tag -->
<%@ tag trimDirectiveWhitespaces="true" %>
<!-- 위의 태그설정은 쓸데없는 공백을 제거하는 설정 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- pageNav 태그라이브러리를 사용할 때 필요한 데이터 --%>
<%-- attribute 명령어 사용 --%>
<%-- name 은 설정할 때 속성의 key값으로 지정 + tag 라이브러리에서 사용하는 변수이름 --%>
<%@ attribute name="pageObject" required="true"
	type="com.gyshop.util.page.PageObject" %>
<%@ attribute name="listURI" required="true"
	type="java.lang.String" %>

<% request.setAttribute("tooltip", " data-toggle='tooltip' data-placement='top' "); %>
<% request.setAttribute("noMove", " title='no move page!' "); %>
<% request.setAttribute("noLinkColor", "#888"); %>
	
<ul class="pagination justify-content-center">
	<%-- 첫 페이지로 이동할 때 사용하는 링크 --%>
	<li data-page="1"
		<%-- ${(pageObject.page == 1)?"class=\"page-item disabled\"":"class=\"page-item\"" } --%>
			class="page-item">
		<c:if test="${pageObject.page > 1 }">
			<a class="page-link" 
				href="${listURI }?page=1&${pageObject.notPageQuery}"
	  		title="click to move first page!!" ${tooltip }
	  		><i class="fa fa-angle-double-left"></i></a>
		</c:if>
		<c:if test="${pageObject.page == 1 }">
			<a class="page-link" href="" onclick="return false"
				${noMove } ${tooltip }
	  		><i class="fa fa-angle-double-left" style="color: ${noLinkColor}"></i></a>
		</c:if>
	</li>
	<%-- 앞 페이지 그룹으로 이동할 때 사용하는 링크 --%>
  <li data-page="${pageObject.startPage - 1 }" class="page-item">
  	<c:if test="${pageObject.startPage > 1 }">
	  	<a class="page-link"
	  		href="${listURI }?page=${pageObject.startPage - 1}&${pageObject.notPageQuery}"
	  		title="click to move previous page group!!" ${tooltip }
	  		><i class="fa fa-angle-left"></i></a>
  	</c:if>
  	<c:if test="${pageObject.startPage == 1 }">
	  	<a class="page-link" href="" onclick="return false"
	  		${noMove } ${tooltip }
	  		><i class="fa fa-angle-left" style="color: ${noLinkColor}"></i></a>
  	</c:if>
  </li>
  <!-- 주석을 테스트 합니다. -->
  <%-- page의 숫자링크를 구현하는 부분 <c:forEach></c:forEach> --%>
  <%-- 10 페이지의 링크 단위 --%>
  <c:forEach begin="${pageObject.startPage }"
  	end="${pageObject.endPage }" var="cnt">
	  <li data-page="${cnt }"
	  	${(pageObject.page == cnt)?"class=\"page-item active\"":"class=\"page-item\"" }>
	  	<a class="page-link"
	  		href="${listURI }?page=${cnt}&${pageObject.notPageQuery}"
	  		>${cnt }</a>
	  </li>
  </c:forEach>
  <%-- 다음 페이지 그룹으로 이동하는 링크 --%>
  <li data-page="${pageObject.endPage + 1 }" class="page-item">
  	<c:if test="${pageObject.endPage < pageObject.totalPage }">
  		<a class="page-link"
  			href="${listURI }?page=${pageObject.endPage + 1}&${pageObject.notPageQuery}"
  			title="click to move next page group!!" ${tooltip }
  			><i class="fa fa-angle-right"></i></a>
  	</c:if>
  	<c:if test="${pageObject.endPage == pageObject.totalPage }">
  		<a class="page-link" href="" onclick="return false"
  			${noMove } ${tooltip }
  			><i class="fa fa-angle-right" style="color: ${noLinkColor}"></i></a>
  	</c:if>
  </li>
  <%-- 마지막 페이지로 이동하는 링크 --%>
  <li data-page="${pageObject.totalPage }"
  <%-- 	${(pageObject.page == pageObject.totalPage)
  		?"class=\"page-item disabled\"":"class=\"page-item\"" } --%>
  		class="page-item">
  	<c:if test="${pageObject.page < pageObject.totalPage }">
  		<a class="page-link"
  			href="${listURI }?page=${pageObject.totalPage}&${pageObject.notPageQuery}"
  			title="click to move last page!!" ${tooltip }
  			><i class="fa fa-angle-double-right"></i></a>
  	</c:if>
  	<c:if test="${pageObject.page == pageObject.totalPage }">
  		<a class="page-link" href="" onclick="return false"
  			${noMove } ${tooltip }
  			><i class="fa fa-angle-double-right" style="color: ${noLinkColor}"></i></a>
  	</c:if>
  </li>
</ul>

<script>
$(function(){
	$("[data-toggle='tooltip']").tooltip();
});
</script>





