<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/board.css">
</head>
<body>
	<h1>Board Page</h1>
	<img alt="사진" src="./img/images.PNG" >
	<br>
	<a href="./">index로 가기</a>
	<br>
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>글쓴이</th>
			<th >날짜</th>
			<th>조회수</th>
		</tr>
		<c:forEach items="${list}" var="i">
			<!-- onclick 자바스크립트 페이지 이동, URL?파라미터=값 -->
			<tr onclick="location.href='./detail?bno=${i.bno }'">
				<td class="td1">${i.bno }</td>
				<td class="td1">${i.btitle }</td>
				<td class="td1">${i.bwrite }</td>
				<td class="td1">${i.bdate }</td>
				<td class="td1">${i.blike }</td>
			</tr>

		</c:forEach>
	</table>
</body>
</html>