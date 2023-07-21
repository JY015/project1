<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="./css/board.css">
<link rel="stylesheet" href="./css/menu.css">
	<nav>
		<ul>
			<li onclick="link('')" 	  	 class="menu_title">메인</li>
			<li onclick="link('board')"  class="menu_title">게시판</li>
			<li onclick="link('board2')" class="menu_title">게시판2</li>
			<li onclick="link('mooni')"  class="menu_title">문의사항</li>
			<li onclick="link('notice')" class="menu_title">공지</li>
			<li onclick="link('login')" class="menu_title">로그인</li>
		</ul>
	</nav>
	<!-- <div style="height : 50px; widh:100%;"></div> -->
<script>
	function link(url){
		location.href="./"+url;
	}
</script>