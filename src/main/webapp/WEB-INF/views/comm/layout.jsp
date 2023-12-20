<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- <title><tiles:getAsString name="title"/></title> --%>
<title><tiles:insertAttribute name="title"/></title>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.0.js"></script>
</head>
<body>
 <!-- tiles:insertAttribute 사용하여 -->
 <!-- name 속성에 지정한 이름으로 타일즈를 통해 채워넣을 ""공간""을 배치  -->
 <tiles:insertAttribute name="menu" />
 
 
 <tiles:insertAttribute name="body" />
 
 
 <tiles:insertAttribute name="footer" />
</body>
</html>