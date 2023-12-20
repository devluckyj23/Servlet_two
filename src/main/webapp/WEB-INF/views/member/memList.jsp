<%@page import="java.util.List"%>
<%@page import="com.exam.myapp.member.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- <!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>회원관리</title>
</head>
<body> -->

<%-- <jsp:include page="/WEB-INF/views/Menu.jsp"></jsp:include> --%>

<hr>

<h1>회원목록</h1>

<%-- <a href= '<%=request.getContextPath()%>/member/add.do'>회원추가</a>
<a href= '${pageContext.request.contextPath}/member/add.do'>회원추가</a>
<a href='<c:url value="/member/add.do"/>'>회원추가</a> --%>

<c:forEach var="vo" items="${memberList}">
	<!--  System.out.println( memId + ", " + memPass + ", " + memName + ", " + memPoint );-->
		<p>
		<c:url value="/member/edit.do" var="editUrl">
			<c:param name="memId" value="${vo.memId}"></c:param>
		</c:url>
		<a href='${editUrl}'><c:out value="${vo.memId}" /></a>
		<c:out value="${vo.memName}" />
		${vo.memPoint}
		<!-- memPoint는 int타입이므로 안 써줘도 됨(태그형태가 안 들어갈 수 있기 때문) -->
		
	<a href='${pageContext.request.contextPath}/member/del.do?memId=${vo.memId}'><button>삭제</button></a>
	<!-- 만약 주소에서도 이 vo.memId를 썼다면 c:out를 쓰는게 더 안전하지만 만약 c:url로 했다면 c:out을 안 해줘도 됨 -->
	
	<!-- JSTL 태그의 scope와 var속성을 사용하면 JSTL 태그 실행 결과를 현재 위치에 출력하지 않고 
	지정한 scope에 지정한 이름(var)의 속성을 저장한 후 EL에서 읽어서 사용 가능 -->
	
	<c:url value="/member/del.do" var="delUrl">
		<c:param name="memId" value="${vo.memId}"></c:param>
	</c:url>
	<a href='${delUrl}'><button>삭제</button></a>
	</p>
</c:forEach>


<!-- </body>
</html> -->
