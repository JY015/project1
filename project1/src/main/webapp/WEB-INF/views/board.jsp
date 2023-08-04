<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./css/board.css">
<link rel="stylesheet" type="text/css"  href="./css/menu.css">
<link rel="icon" href="data:;base64,iVBORw0KGgo=">

<script type="text/javascript">
function linkPage(pageNo){
	location.href = "./board?pageNo="+pageNo;
}	
</script>

</head>
<body>
	<%@ include file="menu.jsp"%>
	<div class="whole">
		<div class="whole2">
			<div class="board-color" id="menuName">게시판</div>
			<%-- 			길이 검사 : ${fn:length(list) } --%>
			<br>
			<c:choose>
				<c:when test="${fn:length(list) gt 0}">
					<table class="board_table">
						<c:forEach items="${list}" var="i">
							<!-- onclick 자바스크립트 페이지 이동, URL?파라미터=값 -->
							<tr onclick="location.href='./detail?bno=${i.bno }'">
								<td class="td2" id="solid" hidden="hidden">${i.bno }</td>
								<td class="td4" id="solid">${i.btitle }
								<small>
								<c:if test="${i.commentcount ne 0}"> (${i.commentcount })</c:if>
								</small>
								</td>
								<td class="td1" id="solid">${i.m_name }</td>
								<td class="td3" id="solid">${i.blike }</td>
								<td class="td1">${i.bdate }</td>
							</tr>
						</c:forEach>
					</table>
					<div class="page_wrap">
								<div class ="page_nation" >
								<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"/>
							</div>
					</div>
				</c:when>
				<c:otherwise>
				<br>
					<h2>게시물이 없습니다.</h2><br>
					<h2>게시물을 작성해주세요</h2>
					
				</c:otherwise>	
			</c:choose>
			<br>
			<c:if test="${sessionScope.mname ne null}" >
			<button class="write_button" onclick="location.href='./write'">글쓰기</button>
			</c:if>

		</div>
	</div>
</body>
</html>