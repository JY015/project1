<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/login.css">
<link rel="stylesheet" href="./css/menu.css">
<link rel="stylesheet" href="./css/board.css">
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
								<form action="./login" method="post">
									<input class="signin_id" type="text" name="id" id="id" placeholder="아이디" required="required" maxlength="10">
									<br> 
									<input class="signin_pw" type="password"  name="pw" id="pw" placeholder="비밀번호" required="required" maxlength="15">
									<br>
									<button class="button_login" >로그인</button>
									<!--<button class="button_login" type="button" onclick="location.href='index'">로그인</button> -->
								</form>
								</div>
							</div>
							<a href="" class="button_join">회원가입하기</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>