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
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<script src="./js/jquery-3.7.0.min.js"></script>
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
	
	$(function(){
		$(".commentBox").hide();
		$("#openComment").click(function(){
			$(".commentBox").show('slow');
			$("#openComment").remove();
		});
	})
</script>
</head>
<%@ include file="menu.jsp" %>
<body>
	<!-- <img alt="사진" src="./img/a.PNG">-->
	<div class="detail-title" id="bTitle">${dto.btitle }</div>
	<%-- 값 : ${dto }<br><br> --%>
	<div class="detail-content">
		<div class="name" id="detail-name">
			<div class="view">${dto.blike }</div>
			<div class="name-color">${dto.m_name }님</div>
		</div>
		<div class="name-bar">
			<div class="date" id="detail-date">${dto.bdate }
				<div class="view">${dto.bip }</div>
			</div>
		</div>
		<div class="content">${dto.bcontent }</div>
		<div class="commentsList">
		<c:choose>
				<c:when test="${fn:length(commentsList) gt 0}"><div class="comment_head">댓글[${dto.commentcount }]</div>
				<div class="comments">

					<c:forEach items="${commentsList }" var="c">
					<div class="name" id="comment_main">
					<div class="view"> ${c.c_date}</div>
					<div class="name-color"> ${c.m_name }</div>
					</div>					
						<div class="comment_comment">${c.c_comment }</div>
						<br><br>
					</c:forEach>
				</div>
				</c:when>
				<c:otherwise>
					<div class="comment_head">댓글이 없습니다.</div>
				</c:otherwise>
		</c:choose>
		</div>
		
		<c:if test="${sessionScope.mid ne null && sessionScope.mid eq dto.m_id }" >
		<%-- <c:if test="${sessionScope.mname eq dto.m_name }" > --%>
		<button class="detail_button1" onclick="del()">삭제</button>
		<button class="detail_button2" onclick="update()">수정</button>
		</c:if> 
		  <c:if test="${sessionScope.mid ne null}"> 
		 <button type="button" id="openComment"> 댓글창 열기</button> 
			<div class="commentBox">
				<form action="./comment" method="post">
					<textarea id="commenttextarea" name="comment" placeholder="댓글을 입력하세요"></textarea>
					<button type="submit" id="comment">댓글쓰기</button>
					<input type="hidden" name="bno" value="${dto.bno }">
				</form>
			</div>
		</c:if> 
	</div>
</body>

</html>