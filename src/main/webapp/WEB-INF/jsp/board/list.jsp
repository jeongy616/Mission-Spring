<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/nav.jsp" %>
<div align="center">
	<hr>
	<h2>전체 게시물 조회</h2>
	<hr>
	<br>
	
	<table border="1" style="width:100%;">
		<tr>
			<th width="7%">번호</th>
			<th>제목</th>
			<th width="16%">글쓴이</th>
			<th width="20%">등록일</th>
		</tr>
		<c:forEach items="${boardList}" var="board">
			<tr>
				<td>${board.no }</td>
				<td>${board.title }</td>
				<td>${board.writer }</td>
				<td>${board.regDate }</td>			
			</tr>
		</c:forEach>
	</table>
</div>
<%@ include file="../include/footer.jsp" %>
