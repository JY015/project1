<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/board.css">
</head>
<body>
<h1>Detail Page</h1>
	<img alt="사진" src="./img/images.PNG" >
<%-- 값 : ${dto }<br><br> --%>
<table>
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>내용</th>
		<th>글쓴이</th>
		<th>날짜</th>
		<th>좋아요</th>
	</tr>
	<tr>
		<td>${dto.bno }</td>
		<td>${dto.btitle }</td>
		<td>${dto.bcontent }</td>
		<td>${dto.bwrite }</td>
		<td>${dto.bdate }</td>
		<td>${dto.blike }</td>
	</tr>
</table>
</body>
</html>