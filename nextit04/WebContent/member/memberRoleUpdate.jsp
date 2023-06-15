<%@page import="java.util.Arrays"%>
<%@page import="kr.or.nextit.exception.BizNotFoundException"%>
<%@page import="kr.or.nextit.exception.DaoException"%>
<%@page import="kr.or.nextit.member.service.MemberServiceImpl"%>
<%@page import="kr.or.nextit.member.service.IMemberService"%>
<%@page import="kr.or.nextit.exception.BizNotEffectedException"%>
<%@page import="kr.or.nextit.exception.BizPasswordNotMatchedException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
	request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NextIT</title>
<link rel="icon" type="image/x-icon"
	href="${pageContext.request.contextPath }/images/nextit_log.jpg" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/member.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<%
		String memId = request.getParameter("memId");
		System.out.println("memId : " + memId);

		String[] roles = request.getParameterValues("userRole");
		System.out.println("roles : " + Arrays.toString(roles));

		IMemberService memberService = new MemberServiceImpl();
		boolean result = memberService.updateMember(memId, roles);
		request.setAttribute("result", result);
		System.out.println("-------result : " + result);
	%>

	<div class="container">


		<c:if test="${result eq true}">
			<h3>회원권한 수정 성공</h3>
			<div>
				<p style="color:white">---result : ${result }--</p>
				<p>정상적으로 회원권한이 수정 되었습니다. 확인을 클릭하시면 회원목록 게시판으로 이동합니다.</p>
				<div class="btn-area">
					<button type="button"
						onclick="location.href='${pageContext.request.contextPath}/member/memberList.jsp'">확인</button>
				</div>
			</div>

		</c:if>

		<c:if test="${result eq false }">
			<h3>회원권한 수정 실패</h3>
			<div>
				<p>회원권한 수정에 실패하였습니다. 전산실에 문의 부탁드립니다. 042-719-8850</p>
				<div class="btn-area">
					<c:out value="${result }"></c:out>
					<button type="button" onclick="history.back()">뒤로가기</button>
				</div>
			</div>

		</c:if>
	</div>
</body>
</html>