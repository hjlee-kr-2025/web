<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- /WEB-INF/views/member/checkId.jsp --%>
<%-- 데이터베이스에서 id값이 넘어오지 않으면 가입된 id가 아니라는 의미 --%>
<c:if test="${empty id }">
	사용가능한 아이디 입니다.
</c:if>
<%-- 데이터베이스에서 id값이 있으면 가입된 id 이니 다른 id로 가입하도록 안내 --%>
<c:if test="${!empty id }">
	중복된 아이디 입니다. 다른 아이디를 입력하세요.
</c:if>


