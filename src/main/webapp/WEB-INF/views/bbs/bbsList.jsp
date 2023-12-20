<%@page import="java.util.List"%>
<%@page import="com.exam.myapp.member.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>게시판</title>
</head>
<body>

<%-- <jsp:include page="/WEB-INF/views/Menu.jsp"></jsp:include> --%>

<hr>

<h1>게시글 목록</h1>
<a href='${pageContext.request.contextPath}/bbs/add.do'><button>글쓰기</button></a>

<table>
	<thead>
		<tr><th> 글번호 </th><th> 제목 </th><th> 작성자 </th><th> 등록일자 </th></tr>
	</thead>
	<tbody>
	<c:forEach var="vo" items="${bbsList}">
		<tr>
			<td>${vo.bbsNo}</td>
			<%--여기서 악성코드가 들어갈 수 있는 내용은 bbsTitle과 bbsWriter.. 왜냐면 String이니까 
			그러므로 c:out을 써서 출력하거나 ${fn:escapeXml()}를 적어서 안전하게 출력해줘야 함--%>  
			<td>
				<%-- <c:out value="${vo.bbsTitle}" /> --%>
				<c:url value="/bbs/edit.do" var="editUrl">
					<c:param name="bbsNo" value="${vo.bbsNo}"></c:param>
				</c:url>
				<a href='${editUrl}'><c:out value="${vo.bbsTitle}" /></a>
			</td>
			<td><c:out value="${vo.bbsWriter}" /></td>
			<td><fmt:formatDate value="${vo.bbsRegDate}" pattern="yyyy/MM/dd HH:mm:ss"/></td>
		<tr>
	</c:forEach>
	</tbody>
</table>

<form id="searchForm" action="${pageContext.request.contextPath}/bbs/list.do">
	<select name="searchType">
<%-- 		<option value="title" ${searchInfo.searchType == 'title'?selected:''}>제목</option>
		<option value="content" ${searchInfo.searchType == 'content'?selected:''}>내용</option>
		<option value="total" ${searchInfo.searchType == 'total'?selected:''}>제목+내용</option> --%>
		<option value="title">제목</option>
		<option value="content">내용</option>
		<option value="total">제목+내용</option>
		
	</select>
	<script type="text/javascript">
		document.querySelector('[name="searchType"]').value='${searchInfo.searchType}';
		
		//$('[name="searchType"]').val('value','${searchInfo.searchType}');
	</script>
	
	<input type="text" name="searchWord" value="${searchInfo.searchWord}"/>
	<input type="hidden" name="currentPageNo" value="1" />
	<input type="submit" name="검색" />
</form>

${searchInfo.pageHtml}
<script>
	function goPage(n) {
		document.querySelector('[name="currentPageNo"]').value= n;
		document.querySelector('#searchForm').submit();		
	}

</script>


<!-- </body>
</html> -->
