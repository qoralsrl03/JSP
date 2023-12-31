<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NextIT</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath }/images/nextit_log.jpg" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/freeBoard.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>

<div class="container">

		<h3>게시글 삭제 성공</h3>
		<div class="alert alert-success">
			<p>정상적으로 게시글이 등록되었습니다. 확인을 클릭하시면 목록페이지로 이동합니다.</p>
			<div class="btn-area">
				<button type="button" onclick="">확인</button>
			</div>
		</div>
	
		<h3>게시글 삭제 실패</h3>
		<div class="alert alert-warning">
			<p> 입력하신 비밀번호가 맞지 않습니다. 다시 확인해 주세요</p>
			<div class="btn-area">
				<button type="button" onclick="">뒤로가기</button>
				<button type="button" onclick="">홈</button>	
			</div>
		</div>

		<h3>게시글 삭제 실패</h3>
		<div class="alert alert-warning">
			<p> 게시글 삭제에 실패하였습니다. 전산실에 문의 부탁드립니다. 042-719-8850</p>
			<div class="btn-area">
				<button type="button" onclick="">뒤로가기</button>
				<button type="button" onclick="">홈</button>	
			</div>
		</div>

</div>

</body>
</html>