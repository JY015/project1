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
</head>
<body>
<%@ include file="menu.jsp" %>
		<div class="write-div" id="menuName">
		<form action="./update" method="post">
			<input type="text" name="title" value="${dto.btitle }">
			<textarea id="summernote" name ="content" >${dto.bcontent }</textarea>
			<button type="submit" class="btn">수정하기</button>
			<input type="hidden" name="bno" value="${dto.bno }">
		</form>
			<button class="btn" onclick="location.href='./detail?bno=${dto.bno }'">취소</button>
	</div>


	<script type="text/javascript">
	// JQuery 문법 : 문서가 모두 로딩되었다면 익명함수를 실행
	// textarea에 서버노트를 실행
	$(document).ready(function() {
			$('#summernote').summernote();
			height:400
		});
	</script>



</body>
</html>