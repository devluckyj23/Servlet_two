<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--스프링이 제공하는 form 태그라이브러리 사용  -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
		  <%-- <!DOCTYPE html>
		  <html>
		  <head>
		  <meta charset='UTF-8'>
		  <title>회원관리</title>
		  </head>
		  <body>
		  
		  <jsp:include page="/WEB-INF/views/Menu.jsp"></jsp:include> --%>
		  
		  <hr>
		  
		  <h1> 회원 가입 </h1>
		<%--   <form action='${pageContext.request.contextPath}/member/add.do' method='post'>
			  아이디 : <input type='text'  name='memId' value='${memberVO.memId}'/><br>
			  비밀번호 : <input type='password'  name='memPass' value='${memberVO.memPass}'/><br>
			  이름 : <input type='text'  name='memName' value='${memberVO.memName}'/><br>
			  포인트 : <input type='number' name='memPoint' value='${memberVO.memPoint}'/><br>
		  <input type='submit'/> 
		  </form> --%>
		  <!-- form 내부에서 사용할 모델의 이름을 modelAttribute 속성값으로 지정 -->
		  <form:form modelAttribute="memberVO" action='${pageContext.request.contextPath}/member/add.do' method='post'>
		  	  아이디 : <form:input path="memId"/> <form:errors path="memId"/><br>
			  비밀번호 : <form:password path="memPass"/> <form:errors path="memPass"/><br>
			  이름 : <form:input path="memName"/> <form:errors path="memName"/><br>
			  포인트 : <form:input path="memPoint"/> <form:errors path="memPoint"/><br>
		  <input type='submit'/> 
		  </form:form>
		  
		  
		  
		  <!-- </body>
		  </html> -->
		 