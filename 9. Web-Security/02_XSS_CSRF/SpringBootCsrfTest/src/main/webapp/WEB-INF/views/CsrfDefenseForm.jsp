<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib  prefix="c"	uri="http://java.sun.com/jsp/jstl/core"%>    	
<c:set var="root" value="${pageContext.request.contextPath}"/>	
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <title>CSRF 테스트 </title>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <style>
      body {
        font-family: sans-serif;
        padding: 20px;
      }
      button {
        margin-bottom: 20px;
        padding: 10px 15px;
        background-color: #007bff;
        color: white;
        border: none;
        cursor: pointer;
      }
      button:hover {
        background-color: #0056b3;
      }
      .book {
        border: 1px solid #ccc;
        padding: 15px;
        margin-bottom: 10px;
        border-radius: 8px;
        background-color: #f9f9f9;
      }
    </style>
	
  </head>
  <body>
    <h1>도서 목록 보기</h1>
    <button id="myBtn">csrfToken 가져오기</button>
	<form method="post" action="${root}/csrf">
	  <input type="hidden" name="_csrf"  id="csrf"/>	
      <input type="text" name="query" placeholder="검색어 입력" />
      <button type="submit">검색</button>
    </form>

	<script>
	   const API_BASE_URL = "http://localhost:9000/eureka/CsrfDefenseForm";
		document.getElementById('myBtn').addEventListener('click', function() {
		   fetchData()
		 });
	   	async function fetchData() {
	     try {
	      const response = await axios.get(API_BASE_URL);
		  const csrfToken = response.headers['x-csrf-token'];
		  console.log("csrfToken.....................",csrfToken)
		  document.getElementById('csrf').value =csrfToken;
	     } catch (error) {
	       console.error(error);
	       resultEl.textContent = "오류 발생: " + error;
	     }
	   }
	</script>
 
  </body>
</html>

