<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="menu.jsp"%>

	<h1>회원 리스트</h1>

	<c:choose>
		<c:when test="${m.gender == 0}">
			남자
		</c:when>
		<c:otherwise>
			여자
		</c:otherwise>
	</c:choose>
		<table class="board_table">
			<tr>
				<th>글번호</th>
				<th>아이디</th>
				<th>이름</th>
				<th>주소</th>
				<th>생일</th>
				<th>mbti</th>
				<th>성별</th>
			</tr>
			<c:forEach items="${list }" var="m">
				<!-- onclick 자바스크립트 페이지 이동, URL?파라미터=값 -->
				<tr>
					<td class="td1" id="solid">${m.no }</td>
					<td class="td1" id="solid">${m.id }</td>
					<td class="td1" id="solid">${m.name }</td>
					<td class="td1" id="solid">${m.addr }</td>
					<td class="td1" id="solid">${m.birth }</td>
					<td class="td1" id="solid">${m.mbti }</td>
					<td class="td1">${m.gender }</td>
				</tr>
			</c:forEach>
		</table>

</body>
</html>