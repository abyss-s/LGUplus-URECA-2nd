<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.net.*"%>
<%@ taglib  prefix="c"	uri="http://java.sun.com/jsp/jstl/core"%>   
<c:set var="root" value="${pageContext.request.contextPath}"/>	
<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
</head>
<body>
	<a href="${root}/XSS.html">1.XSS 방어 Test</a>	<br/>
	<a href="${root}/XSSDefenseForm">2.XSS 방어 Test</a>	<br/>
	<a href="${root}/CsrfAttackForm">3.CSRF 방어가 안된 경우 Test</a>	<br/>
	<a href="${root}/CsrfDefenseForm">4.CSRF 방어가 된 경우 Test</a><br/>
</body>
</html>
