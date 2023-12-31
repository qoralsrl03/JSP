<%@page import="kr.or.nextit.exception.DaoException"%>
<%@page import="kr.or.nextit.member.service.MemberServiceImpl"%>
<%@page import="kr.or.nextit.member.service.IMemberService"%>
<%@page import="kr.or.nextit.exception.BizNotEffectedException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NextIT</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath }/images/nextit_log.jpg" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/header.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/memberView.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/footer.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>


<style type="text/css">
/* modal css */
#modal_div1{
	width: 100%;
	height: 100%;
	position: fixed; 	
	top: 0; right: 0; bottom: 0; left: 0;
	background-color: rgba(0,0,0,0.4);	
	z-index: 1;
	display: none;
}
#modal_div2{
	width: 400px;
	height: 200px;
	background-color: lightgrey;
	text-align: center;
	position: fixed;
	left: calc(50% - 200px);
	top: calc(50% - 200px);
}
#modal_div2 > p {
	margin-top: 50px;
}
#modal_div2 > a {
	margin-left: 300px;
}
form[name="deleteForm"]{
	width: 350px;
    height: 350px;
    /* background-color: tomato; */
    margin-top: 20px;
}
.int-area{
    width: 300px;
    height: 150px;
    /* background-color: lightblue; */ 
}
.int-area:first-child{
	padding-top: 40px;
}
.int-area input{
    width: 80%;
    padding: 30px 10px 10px;
    background-color: transparent;
    border: none;
    border-bottom: 1px solid #999;
    font-size: 18px;
    color: #fff;
}
.btn-area > button{
    width: 40%;
    height: 40px;
    color: #fff;
    font-size: 20px;
    border: none;
    border-radius: 15px;
    background-color: lightpink;
    position: relative;
    top: -50px
}
.btn-area > button:active{
	color: gray;
}
</style>


<script>

function fn_memberEdit(memId){
	console.log("memId:"+ memId);
	location.href = "${pageContext.request.contextPath}/member/memberEdit.jsp?memId="+memId; 
}

function fn_memberDelete(){
	console.log("fn_memberDelete");
	$("#modal_div1").fadeIn();
}

function fn_memberDeleteSubmit(){
	console.log("fn_memberDeleteSubmit");
	let ret = confirm("탈퇴를 진행하시겠습니까?");
	if(ret){
		let f = document.deleteForm;
		console.log("f : ", f);
		f.submit();
	}else{
		$("#modal_div1").fadeOut();	
	}
}

function fn_cancel(){
	$("#modal_div1").fadeOut();
}

</script>
</head>
<body>

<%
	String memId = request.getParameter("memId");
	IMemberService memberService = new MemberServiceImpl();
				
	try{
		MemberVO member = memberService.getMember(memId);
		member.setMemPass("");  
		request.setAttribute("member", member);
	}catch(BizNotEffectedException bne){
		request.setAttribute("bne", bne);
		bne.printStackTrace();
	}catch(DaoException de){
		request.setAttribute("de", de);
		de.printStackTrace();
	}
%>

<div id="wrap">
	 <div class="header">
         <div class="top_nav">
             <!-- header 영역 -->
             <%@ include file="/header/header.jsp" %>
             
         </div>
     </div>
     <!-- header e -->

     <div class="intro_bg">
         <div class="intro_text">
             <h1>NextIT</h1>
             <h4>넥스트아이티</h4>
         </div>
     </div>
     <!-- intro_bg e -->

     <!-- 전체 영역잡기 -->
     <div class="contents">
         <!-- 사용할 영역잡기 -->
         <div class="content01">
             <div class="content01_h1">
                 <h1>회원 정보 상세</h1>
             </div>
             <!-- 회원 정보 테이블 -->
             <div id="div_table">
             	<c:choose>
             		<c:when test="${bne ne null or de ne null}">
             			<h3>회원 정보 조회 실패</h3>
						<div class="alert alert-success">
							<p>회원 정보 조회 실패 하였습니다. 전산실에 문의 부탁드립니다. 042-719-8850</p>
							<div class="btn-area">
								<button type="button" onclick="history.back();">뒤로가기</button>
							</div>
						</div>
             		</c:when>
             		<c:when test="${bne eq null and de eq null }">
             			<table >
							<tbody>
								<tr>
									<td class="td_left">아이디</td>
									<td class="td_right"><c:out value="${member.memId }"/></td>
								</tr>
								<tr>
									<td class="td_left">회원명</td>
									<td class="td_right"><c:out value="${member.memName }"/></td>
								</tr>
								<tr>
									<td class="td_left">우편번호</td>
									<td class="td_right"><c:out value="${member.memZip }"/></td>
								</tr>
								<tr>
									<td class="td_left">주소</td>
									<td class="td_right"><c:out value="${member.memAdd1 } ${member.memAdd2 }"/></td>
								</tr>
								<tr>
									<td class="td_left">생일 </td>
									<td class="td_right"><input type="date" name="memBir"  value="${fn:substring(member.memBir ,0,10) }" readonly="readonly"></td> 
								</tr>
								<tr>
									<td class="td_left">핸드폰</td>
									<td class="td_right"><c:out value="${member.memHp }"/></td>
								</tr>
								<tr>
									<td class="td_left">직업</td>
									<td class="td_right"><c:out value="${member.memJob }"/></td>
								</tr>
								<tr>
									<td class="td_left">취미</td>
									<td class="td_right"><c:out value="${member.memHobby }"/></td>
								</tr>
								<tr>
									<td class="td_left">마일리지</td>
									<td class="td_right"><c:out value="${member.memMileage}"/></td>
								</tr>
								<%-- <tr>
									<td class="td_left">탈퇴여부</td>
									<td class="td_right">${member.memDelYn }</td>
								</tr> --%>
							</tbody>
						</table>
						<div class="div_button">
		                     <input type="button" onclick="location.href='${pageContext.request.contextPath}/home/home.jsp'" value="HOME">
		                     <%-- ${member.memId }는 '' 필요  --%>
		                     <input type="button" onclick="fn_memberEdit('${member.memId }')" value="수정">
		                     <%-- <input type="button" onclick="location.href='${pageContext.request.contextPath}/member/memberDelete.jsp?memId=${member.memId }'" value="탈퇴"> --%>
		                     <input type="button" onclick="fn_memberDelete()" value="탈퇴">
		                 </div>
             		</c:when>
             	</c:choose>
             </div>
         </div>
     </div>

	<!-- 회원탈퇴 모달 -->
	<div id="modal_div1" >
		<div id="modal_div2" >
			<form name="deleteForm" action="${pageContext.request.contextPath}/member/memberDelete.jsp" method="post">
				<input type="hidden" id="memId" name="memId" value="${member.memId }"/>
	            <div class="int-area">
	                <input type="password" id="memPass" name="memPass" value="" placeholder="PASSWORD" autocomplete="off" required/>
	            </div>
	            <div class="btn-area">
	                <button type="button" onclick="fn_memberDeleteSubmit()">탈퇴</button>
	                <button type="button" onclick="fn_cancel()">취소</button>
	            </div>
	        </form>
			
		</div>
	</div>
		
     <!-- footer -->
     <footer id="page_footer">
         <%@ include file="/footer/footer.jsp" %>
     </footer>
</div>    
</body>
</html>