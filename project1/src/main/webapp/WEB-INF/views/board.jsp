<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/board.css">
<link rel="stylesheet" href="./css/menu.css">
</head>
<body>
<%@ include file="menu.jsp" %>
<div class="whole">
	<div class="whole2">
	<div class="board-color" id="menuName">게시판</div>
	<br>
	<table>
		<c:forEach items="${list}" var="i">
			<!-- onclick 자바스크립트 페이지 이동, URL?파라미터=값 -->
			
			<tr onclick="location.href='./detail?bno=${i.bno }'">
				<td class="td3">${i.blike }</td>
				<td class="td1" id="hidden">${i.bno }</td>
				<td class="td4" id="solid">${i.btitle }</td>
				<td class="td1" id="solid">${i.bwrite }</td>
				<td class="td1" >${i.bdate }</td>
			</tr>

		</c:forEach>
	</table>
	<br>
	<button onclick="location.href='write'">글쓰기</button>
	</div>
</div>
</body>
</html>