<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/board.css">
<link rel="stylesheet" href="./css/menu.css">
<link rel="stylesheet" href="./css/write.css">
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<script type="text/javascript">
	function check() {
		let title = document.getElementById("title");
		let content = document.getElementById("summernote");

		if (title.value.length == 0) {
			alert("제목을 입력해")
			title.focus();
			return false;
		}

		if (content.value.length < 10) {
			alert("내용을 입력해")
			content.focus();
			return false;
		}
	}
</script>
</head>
<body>
<%@ include file="menu.jsp" %>
	<div class="write-div" id="menuName">
		<form action="./write" method="post" onsubmit="return check()">
			<input type="text" name="title" id="title">
			<textarea id="summernote" name ="content" ></textarea>
			<button type="submit" class="btn">글쓰기</button>
			<button class="btn" onclick="location.href='./board'">취소</button>
			<!-- <button class="btn100 btn btn-primary" onclick="location.href='./board'">취소</button> -->
		</form>
	</div>


	<script type="text/javascript">
	// JQuery 문법 : 문서가 모두 로딩되었다면 익명함수를 실행
	// textarea에 서버노트를 실행
	$(document).ready(function() {
			$('#summernote').summernote();
			height:400;
			focus: true;
		});

	</script>



</body>
</html>