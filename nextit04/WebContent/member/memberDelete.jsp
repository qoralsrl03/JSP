<%@page import="kr.or.nextit.exception.DaoException"%>
<%@page import="kr.or.nextit.exception.BizNotFoundException"%>
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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/member.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

</head>
<body>

<jsp:useBean id="member" class="kr.or.nextit.member.vo.MemberVO"></jsp:useBean>
<jsp:setProperty property="*" name="member"/>

<%
	IMemberService memberService = new MemberServiceImpl();
	try{
		memberService.removeMember(member);
		session.removeAttribute("memberVO");
	}catch(BizNotFoundException bnf){
		request.setAttribute("bnf", bnf);
		bnf.printStackTrace();
	}catch(BizPasswordNotMatchedException bpn){
		request.setAttribute("bpn", bpn);
		bpn.printStackTrace();
	}catch(BizNotEffectedException bne){
		request.setAttribute("bne", bne);
		bne.printStackTrace();
	}catch(DaoException de){
		request.setAttribute("de", de);
		de.printStackTrace();
	}
%>



<div class="container">

	<c:if test="${ bnf eq null and bpn eq null and bne eq null and de eq null }">
		<h3>회원탈퇴 성공</h3>
		<div>
			<p>회원 탈퇴가 처리 되었습니다. 확인을 누르시면 로그인 페이지로 이동합니다.</p>
			<div class="btn-area">
				<button type="button" onclick="location.href='${pageContext.request.contextPath}/login/login.jsp'">확인</button>
			</div>
		</div>
	</c:if>
	
	<c:if test="${ bpn ne null}">
		<h3>회원탈퇴 실패</h3>
		<div>
			<p>입력하신 패스워드가 올바르지 않습니다. 다시 입력 부탁드립니다.</p>
			<div class="btn-area">
				<button type="button" onclick="history.back();">뒤로가기</button>
			</div>
		</div>
	</c:if>
	
	<c:if test="${ bnf ne null or bne ne null or de ne null  }">
		<h3>회원탈퇴 실패</h3>
		<div>
			<p> 회원 탈퇴에 실패하였습니다. 전산실에 문의 부탁드립니다. 042-719-8850</p>
			<div class="btn-area">
				<button type="button" onclick="history.back();">뒤로가기</button>
			</div>
		</div>
	</c:if>
</div>
</body>
</html>