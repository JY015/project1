<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./css/register.css">
<script type="text/javascript">
	
	function check() {
		let id = document.getElementById("userId");
		let pw = document.getElementById("userPw");
		let name = document.getElementById("userName");
		let addr = document.getElementById("userAddr");
		
		let reg= /[^0-9a-zA-Z]/g;
		let reg2= /[0-9a-zA-Z`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/ ]/g;
		let reg3 = /[^ㄱ-ㅎㅏ-ㅣ가-힣]/g;
		
		if(reg.test(id.value)){
			alert("아이디에 한글/특수문자는 사용할 수 없습니다.");
			id.focus();
			return false;
		}

		if (id.value.length < 4) {
			alert("아이디를 4글자 이상 입력하세요.")
			id.focus();
			return false;
		}

		if (pw.value.length < 4) {
			alert("비밀번호를 4글자 이상  입력하세요.")
			pw.focus();
			return false;
		}
		
		if (name.value.length < 2) {
			alert("이름을 2글자 이상  입력하세요.")
			name.focus();
			return false;
		}
		
		if(reg2.test(name.value)){
			alert("이름은 한글만 사용할 수 있습니다.");
			name.focus();
			return false;
		}
		
		if (addr.value.length < 4) {
			alert("주소를 4글자 이상 입력하세요.")
			addr.focus();
			return false;
		}
		
		if(reg3.test(addr.value)){
			alert("주소는 한글만 사용할 수 있습니다.");
			addr.focus();
			return false;
		}
	}
</script>
</head>
<body>
<%@ include file="menu.jsp" %>
	<div class="nav_container">
		<div class="content_join" id="div_content">
			<div class="reg_text">회원가입</div>
			<div class="board_head"></div>
			<h3 class="form_title">
				기본정보입력<span class="title_tip">(모두 필수 입력 항목입니다.)</span>
			</h3>
			<form id="infForm" action="./register" method="post" onsubmit="return check()">
				<div class="form topline">
					<table>
						<tbody>
							<tr>
								<th class="id">아이디</th>
								<td><input type="text" autocapitalize="off" name="userId" id="userId" maxlength="15"> 
								<span class="tip">
								영문, 숫자만 입력 가능/최소 4자 이상 입력하세요.
								</span></td>
							</tr>
							<tr>
								<th class="id">비밀번호</th>
								<td><input type="password" name="userPw" id="userPw" maxlength="15"> 
								<span class="tip">
								영문, 숫자, 특수무기호 혼합 사용 / 최소 4자 이상 입력하세요.
								</span></td>
							</tr>
							<tr>
								<th class="id">이름</th>
								<td><input type="text" name="userName"  id="userName" maxlength="4"> 
								<span class="tip mypage"> 
								이름은 최대 한글4자 입력 / 특수문자 혼합사용 금지<br>
								영문, 숫자, 특수무기호 혼합 사용 금지 / 최소 2자 이상 입력하세요.
								</span></td>
							</tr>
							<tr>
								<th class="id">주소</th>
								<td><input type="text" name="userAddr"  id="userAddr" maxlength="15"> 
								<span class="tip"> 
								주소는 최소 4자/ 최대 15자 입력<br>
								</span></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="form_button">
					<button type="submit" class="button-agree">회원가입</button>
					<button type="button" class="button-cancel" onclick="">취소</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>