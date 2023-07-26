<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/login.css">
<link rel="stylesheet" href="./css/menu.css">
<link rel="stylesheet" href="./css/board.css">
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<script type="text/javascript">

// 호이스팅이 뭐에요? let vs var? json? const /
let text="<p> 올바른 아이디를 입력하세요.</p>"; // 전역변수 
	function checkID(){
	let msg = document.getElementById("msg");
		msg.innerHTML += "<p>" +document.getElementById("id").value +"아이디를 변경했습니다.</p>"
	}
	
	function check() {
		let msg = document.getElementById("msg");
		let id = document.getElementById("id");
		let pw = document.getElementById("pw");

		if (id.value.length == 0) {
			alert("아이디 입력해")
			msg.innerHTML = text;
			id.focus();
			return false;
		}

		if (pw.value.length == 0) {
			alert("비밀번호도 입력해")
			pw.focus();
			return false;
		}
	}
</script>
</head>
<body>
	<%@ include file="menu.jsp"%>
	<div class="nav_container">
		<div class="content_signin">
			<div class="signin_selection_group">
				<div class="login_form">
					<div class="signin_swiper" id="otp">
						<div class="signin-section">
							<div>
								<h2 class="signin_title">게시판 로그인</h2>
								<div class="signin-section">
								<form action="./login" method="post" onsubmit="return check()">
									<input class="signin_id" type="text" name="id" id="id" placeholder="아이디" maxlength="10" onchange="checkID()">
									<br> 
									<input class="signin_pw" type="password"  name="pw" id="pw" placeholder="비밀번호"  maxlength="15">
									<br>
									<!-- onclick="return check()" -->
									<button class="button_login">로그인</button>
									<span id="msg"></span>
									<!--<button class="button_login" type="button" onclick="location.href='index'">로그인</button> -->
								</form>
								</div>
							</div>
							<a href="./register" class="button_join">회원가입하기</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>