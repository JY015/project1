<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./css/register.css">
<script src="./js/jquery-3.7.0.min.js"></script>
<script type="text/javascript">	
$(function(){
	$("#idCheck").click(function(){
		let id = $("#id").val();
		
		console.log(id);
		
		if(id == "" || id.length<4){
			$("#id").focus();
			$("#resultMSG").text("영문, 숫자만 입력 가능/최소 4자 이상 입력하세요.");
			/* $("#resultMSG").css("color","red");
			$("#resultMSG").css("font-weight","bold");
			$("#resultMSG").css("font-size","15pt"); */
		} else{
			$.ajax({
				url:"./checkID", // 
				type:"post",
				data:{"id" : id},
				dataType : "json", //{result : 0}
				success:function(data){//위에 데이터가 아닌 서버에서 날라오는 data
					//alert(data.result);
					if(data.result == 1){
						$("#id").css("background-color","red").focus();
						$("#resultMSG").text("이미 등록된 아이디 입니다.")	
					} else{
						$("#id").css("background-color","white");
						$("#resultMSG").text("사용할 수 있는 아이디 입니다.")							
					}
					/* $("#resultMSG").text("성공시 :" +data); */
				},
				error : function(request, status, error){
					$("#resultMSG").text("오류가 발생했습니다. 가입할 수 없습니다.");
				}
			});
		}
		return false; // 멈추기
	});	
});

$(function(){
	$("#pwCheck").click(function(){
		let pw1=$("#pw1").val();
		let pw2=$("#pw2").val();
		
		console.log(pw1);
		console.log(pw2);
	
		if(pw1 == pw2){
			$("#pwMSG").text("비밀번호가 일치합니다.");
		} else{
			$("#pwMSG").text("비밀번호가 일치하지 않습니다.");
		}
		return false;
	});	
});
</script>

<!-- <script type="text/javascript">

	function check() {
		let id = document.getElementById("id");
		let pw1 = document.getElementById("pw1");
		let pw2 = document.getElementById("pw2");
		let name = document.getElementById("name");
		let addr = document.getElementById("addr");

		let reg = /[^0-9a-zA-Z]/g;
		let reg2 = /[0-9a-zA-Z`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/ ]/g;
		let reg3 = /[^ㄱ-ㅎㅏ-ㅣ가-힣]/g;

		if (reg.test(id.value)) {
			alert("아이디에 한글/특수문자는 사용할 수 없습니다.");
			id.focus();
			return false;
		}

		if (id.value.length < 4) {
			alert("아이디를 4글자 이상 입력하세요.")
			id.focus();
			return false;
		}

		if (pw1.value.length < 4) {
			alert("비밀번호를 4글자 이상  입력하세요.")
			pw1.focus();
			return false;
		}

		if (pw2.value.length < 4) {
			alert("비밀번호를 4글자 이상  입력하세요.")
			pw2.focus();
			return false;
		}

		if (name.value.length < 2) {
			alert("이름을 2글자 이상  입력하세요.")
			name.focus();
			return false;
		}

		if (reg2.test(name.value)) {
			alert("이름은 한글만 사용할 수 있습니다.");
			name.focus();
			return false;
		}

		if (addr.value.length < 4) {
			alert("주소를 4글자 이상 입력하세요.")
			addr.focus();
			return false;
		}

		if (reg3.test(addr.value)) {
			alert("주소는 한글만 사용할 수 있습니다.");
			addr.focus();
			return false;
		}

		if (mbti.value == 'none') {
			alert('mbti를 선택하세요');
			return false;
		}

		if (birth.value == 0) {
			alert("생년월일을 선택하세요.");
			return false;
		}

	}
</script>
 -->
</head>
<body>
	<%@ include file="menu.jsp"%>
	<div class="nav_container">
		<div class="content_join" id="div_content">
			<div class="reg_text">회원가입</div>
			<div class="board_head"></div>
			<h3 class="form_title">
				기본정보입력<span class="title_tip">(모두 필수 입력 항목입니다.)</span>
			</h3>
			<form action="./register" method="post" >
				<div class="form topline">
					<table>
						<tbody>
							<tr>
								<th class="id">아이디</th>
									<td>
<!-- 										<input type="text" autocapitalize="off" name="id" id="id" maxlength="15" oninput="sameIdCheck()"> -->
										<input type="text" autocapitalize="off" name="id" id="id" maxlength="15" >
										<button id="idCheck" class="idCheck"> 중복검사</button>
										<br>
										<span id="resultMSG" class="tip"></span>
										<!-- <span class="tip">
										영문, 숫자만 입력 가능/최소 4자 이상 입력하세요.
										</span> -->
									</td>
							</tr>
							<tr>
								<th class="id">비밀번호</th>
									<td>
										<input type="password" name="pw1" id="pw1" maxlength="15" autoComplete="off">
									</td>
							</tr>
							<tr>
								<th class="id">비밀번호 확인</th>
								<td>
									<input type="password" name="pw2" id="pw2" maxlength="15" autoComplete="off"> 
									<span id="pwCheck" class="tip">
									영문, 숫자, 특수기호 혼합 사용가능/ 최소 4자 이상 입력하세요. 
									</span>
									<span id="pwMSG" class="tip"></span>
								</td>
							</tr>
							<tr>
								<th class="id">이름</th>
									<td>
										<input type="text" name="name" id="name" maxlength="4"> 
										<span class="tip mypage"> 
										이름은 최대 한글4자 입력 / 특수문자 혼합사용 금지<br> 
										영문, 숫자, 특수기호 혼합 사용 금지 / 최소 2자 이상 입력하세요.
										</span>
									</td>
							</tr>
							<tr>
								<th class="id">주소</th>
									<td>
										<input type="text" name="addr" id="addr" maxlength="15"> 
										<span class="tip"> 
										주소는 최소 4자/ 최대 15자 입력<br>
										</span>
									</td>
							</tr>
							<tr>
								<th>mbti</th>
								<td>
									<select class="mbti" name="mbti" id="mbti">
									    <option value="none">선택하세요</option>
									    <optgroup label="내향형">
										    <option value="1">ISTJ</option>
										    <option value="2">ISTP</option>
										    <option value="3">ISFJ</option>
										    <option value="4">ISFP</option>
										    <option value="5">INTJ</option>
										    <option value="6">INTP</option>
										    <option value="7">INFJ</option>
										    <option value="8">INFP</option>
									    </optgroup>
									    <optgroup label="외향형">
										    <option value="9">ESTJ</option>
										    <option value="10">ESTP</option>
										    <option value="11">ESFJ</option>
										    <option value="12">ESFP</option>
										    <option value="13">ENTJ</option>
										    <option value="14">ENTP</option>
										    <option value="15">ENFJ</option>
										    <option value="16">ENFP</option>
									    </optgroup>
									</select>
								</td>
							</tr>
							<tr>
								<th>생년월일</th>
									<td>
										<input class="birth" type="date" name="birth" id="birth" maxlength="15"> 
										<br>
									</td>
							</tr>
							<tr>
								<th>성별</th>
									<td>
										<input type="radio" name="gender" id="m" value="1" checked>
										<label for="m">남자</label><br>
										<input type="radio" name="gender" id="f" value="0">
										<label for="f">여자</label>
									</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="form_button">
					<button type="submit" class="button-agree">회원가입</button>
					<button type="button" class="register_cancel_button" onclick="location.href='./index'">취소</button>
				</div>
			</form>
		</div>
	</div>

</body>
</html>