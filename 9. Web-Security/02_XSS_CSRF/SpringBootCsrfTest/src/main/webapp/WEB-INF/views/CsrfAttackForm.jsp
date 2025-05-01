<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
</head>
<body>
	<%
		//쿠키 생성하기 
		Cookie cookie1 = new Cookie("name", "ureka");
		Cookie cookie2 = new Cookie("age", "2");
		Cookie cookie3 = new Cookie("password", "11111");
		//HttpOnly : 브라우져에서 보호하는 쿠키로 js로 접근 할 수 없다. 
		cookie3.setHttpOnly(true);
		//https 환경에서만 전송
		//cookie3.setSecure(true)
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		response.addCookie(cookie3);
		
		String query = request.getParameter("query");
	%>
<h1>
	Hello world!  
</h1>


<h1>검색 페이지</h1>

    <form method="get" action="/eureka/CsrfAttackForm">
      <input type="text" name="query" placeholder="검색어 입력" />
      <button type="submit">검색</button>
    </form>

    <h2>검색 결과</h2>
    <div id="result"><%=query%></div>

</body>
</html>
