<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/menu.css">
<link rel="stylesheet" href="./css/board.css">
<link rel="stylesheet" href="./css/detail.css">
</head>
<%@ include file="menu.jsp" %>
<body>
	<!-- <img alt="사진" src="./img/a.PNG">-->
	<div class="detail-title" id="bTitle">${dto.btitle } ${dto.blike }</div>
	<%-- 값 : ${dto }<br><br> --%>
	<div class="detail-content">
			<div class="name" id="detail-name">${dto.bwrite }님</div>
		<div class="name-bar">
			<div class="date"  id="detail-date">${dto.bdate }</div>
		</div>
		<div class="content">${dto.bcontent }</div>
	</div>
</body>
</html>