<%@page import="kr.or.nextit.exception.DaoException"%>
<%@page import="kr.or.nextit.exception.BizNotEffectedException"%>
<%@page import="kr.or.nextit.free.vo.FreeBoardVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.nextit.free.service.FreeBoardServiceImpl"%>
<%@page import="kr.or.nextit.free.service.IFreeBoardService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NextIT</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath }/images/nextit_log.jpg" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/header.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/freeBoardList.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/footer.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
<%
IFreeBoardService freeBoardService = new FreeBoardServiceImpl();
try{
	List<FreeBoardVO> freeBoardList = freeBoardService.getBoardList();
	request.setAttribute("freeBoardList", freeBoardList);
}catch(BizNotEffectedException bne){
	bne.printStackTrace();
	request.setAttribute("bne", bne);
}catch(DaoException de){
	de.printStackTrace();
	request.setAttribute("de", de);
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
                <h1>자유게시판</h1>
            </div>
            
            <c:if test="${bne ne null or de ne null }">
            		<div class="alert alert-warning">
            			목록을 불러오지 못하였습니다. 전산실에 문의 부탁드립니다. 042-719-8850
            		</div>
            		<div class="div_button">
            			<input type="button" onclick="history.back();" 
            				value="뒤로가기">
            		</div>
            </c:if>
 
 			  <c:if test="${bne eq null and de eq null }">
		  		   <!-- 리스트 -->
	            <div id="div_table">
	                <table>
	                    <colgroup>
	                        <col width="100">
	                        <col width="150">
	                        <col >
	                        <col width="150">
	                        <col width="150">
	                        <col width="100">
	                    </colgroup>
	                    <thead>
	                        <tr>
	                            <th>글번호</th>
								<th>분류</th>
								<th>제목</th>
								<th>작성자</th>
								<th>등록일</th>
								<th>조회수</th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                        <c:forEach items="${freeBoardList }" 
	                        	var="freeBoard">
	                        		<tr>
	                        			<td><c:out value="${freeBoard.boNo}"></c:out></td>
	                        			<td><c:out value="${freeBoard.boCategoryNm}"></c:out></td>
	                        			<td><c:out value="${freeBoard.boTitle}"></c:out></td>
	                        			<td><c:out value="${freeBoard.boWriter}"></c:out></td>
	                        			<td><c:out value="${freeBoard.boRegDate}"></c:out></td>
	                        			<td><c:out value="${freeBoard.boHit}"></c:out></td>
	                        		</tr>
	                        </c:forEach>
	                    </tbody>
	                </table>
	            </div>
	
	            <!-- paging -->
	            <div class="div_paging">
	                <ul class="pagination">
	                    <li><a href="#">&laquo;</a></li>  	 
	                    <li><a href="#">&lt;</a></li>		 
	                    <li><a href="#">1</a></li>
	                    <li><a href="#">2</a></li>
	                    <li><a href="#">3</a></li>
	                  	<li><a href="#">4</a></li>
	                    <li><a href="#">5</a></li>
	                    <li><a href="#">6</a></li>
	                    <li><a href="#">7</a></li>
	                    <li><a href="#">8</a></li>
	                    <li><a href="#">9</a></li>
	                    <li><a href="#">10</a></li>
	                    <li><a href="#">&gt;</a></li>		 
	                    <li><a href="#">&raquo;</a></li>   
	                </ul>
	                <div class="div_board_write">
	                    <input type="button" 
	                    	onclick="location.href='${pageContext.request.contextPath}/free/freeForm.jsp'" value="글쓰기">
	                </div>
	            </div>
 			  </c:if>	
            
            
        </div>
    </div>

    <!-- footer -->
    <footer id="page_footer">
		<!-- footer영역 -->
		<%@ include file="/footer/footer.jsp" %>
    </footer>

</div>  


</body>
</html>