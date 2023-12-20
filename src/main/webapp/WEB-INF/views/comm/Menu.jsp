<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--  로그인이 된 경우, 로그인한 사용자 이름과 로그아웃 링크를 출력/로그인이 되지 않은 경우, 로그인과 회원가입(추가) 링크를 출력 --> 
<c:choose>
	<c:when test="${loginUser!=null}">
		<c:out value="${loginUser.memName}"></c:out>
		<a href='<c:url value="/member/logout.do"/>'>로그아웃</a> ||
		<a href='${pageContext.request.contextPath}/member/list.do'>회원관리</a> ||
		<a href='${pageContext.request.contextPath}/bbs/list.do'>게시판</a>
	</c:when>
	<c:otherwise>
		<a href='<c:url value="/member/login.do"/>'>로그인</a>
		<a href='<c:url value="/member/add.do"/>'>회원추가</a>
	</c:otherwise>
</c:choose>