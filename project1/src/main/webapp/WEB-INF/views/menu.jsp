<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="./css/board.css">
<link rel="stylesheet" href="./css/menu.css">
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
	<nav>
		<ul>
			<li onclick="link('')" 	  	  class="menu_title">메인</li>
			<li onclick="link('board')"   class="menu_title">게시판</li>
			<li onclick="link('board2')"  class="menu_title">게시판2</li>
			<li onclick="link('mooni')"   class="menu_title">문의사항</li>
			<li onclick="link('notice')"  class="menu_title">공지</li>
			<li onclick="link('members')"  class="menu_title">회원리스트</li>
			<c:choose>
				<c:when test="${sessionScope.mname eq null}">
					<li onclick="link('login')" class="menu_login">로그인</li>			
				</c:when>
				<c:otherwise>
					<li onclick="link('myInfo')" class="menu_login" id="login_id">${sessionScope.mname }(${sessionScope.mid})님</li>
					<li onclick="logout()" class="menu_login">로그아웃</li>
				</c:otherwise>
			</c:choose>
		</ul>
	</nav>
	<!-- <div style="height : 50px; widh:100%;"></div> -->
<script>
	function link(url){
		location.href="./"+url;
	}
	
	function logout(){
		let name = "<c:out value='${sessionScope.mname}'/>";
		let id = "<c:out value='${sessionScope.mid}'/>";
		let chk = confirm(name+"("+id+")"+"님 로그아웃 하시겠습니까?");
		if(chk){
			location.href="./logout";
		}
	}
</script>