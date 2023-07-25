<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/menu.css">
<link rel="stylesheet" href="./css/board.css">
<link rel="stylesheet" href="./css/detail.css">
<script type="text/javascript">
	function del(){
		let chk = confirm("삭제 하시겠습니까?");
		/* alert(chk);*/
		if(chk){
			location.href="./delete?bno=${dto.bno }";
		}
	}
	
	function update(){
		if(confirm("수정 하시겠습니까?")){
			location.href="./update?bno=${dto.bno }";
		}
	}
</script>
</head>
<%@ include file="menu.jsp" %>
<body>
	<!-- <img alt="사진" src="./img/a.PNG">-->
	<div class="detail-title" id="bTitle">${dto.btitle } ${dto.blike }</div>
	<%-- 값 : ${dto }<br><br> --%>
	<div class="detail-content">
			<div class="name" id="detail-name">${dto.m_name }님</div>
		<div class="name-bar">
			<div class="date"  id="detail-date">${dto.bdate }</div>
			<div class="date"  id="detail-date">${dto.bip }</div>
		</div>
		<div class="content">${dto.bcontent }</div>
		<!-- <img alt="삭제" src="/img/delete.png">
		<img alt="수정" src="/img/update.png"> -->
		<c:if test="${sessionScope.mid ne null && sessionScope.mid eq dto.m_id }" >
		<%-- <c:if test="${sessionScope.mname eq dto.m_name }" > --%>
		<button onclick="del()">삭제</button>
		<button onclick="update()">수정</button>
		</c:if> 
	</div>
</body>

</html>