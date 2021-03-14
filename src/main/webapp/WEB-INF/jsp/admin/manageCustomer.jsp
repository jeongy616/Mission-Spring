<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
body{
	background: #f0f5f5;
}
#main{
	border-collapse: collapse;
}
#main tr td{
	border:1px solid gray;
	padding:10px;
}
th{
	background:#008485;
	color:white;
}
.tLeft{
	text-align:left;
}
input[type=button]{
	background: #008485;
	color:white;
	margin-top:10px;
	padding:0.5em 1em 0.5em 1em;
	border-radius: 5px;
	border:1px solid #008485;
	font-size:1em;
}
@media screen and (max-width: 736px) {
			table {
				width: 300px;
			}
}
a{text-decoration: none;}
a:active
a:linked,
a:visited {color:black;}
select,option{
	padding:10px;
	font-size:1em;
}
.keyword{
	width:200px;
	line-height: 35px;
	vertical-align: top;
}
input[type=submit]{
	height: 41px;
    padding: 5px;
    vertical-align: top;
}
</style>
</head>
<body>
<div style="text-align: center;">
	<div style="display:inline-block;width:900px;">
		<div >
			<form action="/searchCustomer/1" method="post" name="searchForm">
				<select name="type">
					<option value="customer_no">번호</option>
					<option value="name">이름</option>
				</select>
				<input type="text" name="keyword" id="keyword"  class="keyword">
				<input type="submit" value="검색">
			</form>
		</div>
		<br>
		<table style="border:1px solid gray;width:100%;" id="main" >
			<colgroup>
				<col width="3%">
				<col width="10%">
				<col width="20%">
				<col width="15%">
				<col width="15%">
				<col width="35%">
			</colgroup>
			<tr>
				<th></th>
				<th>no</th>
				<th>고객번호</th>
				<th>이름</th>
				<th>아이디</th>
				<th>주소</th>
			</tr>	
		<c:forEach items="${customerList}" var="list" varStatus="status">
			<tr style = "cursor:pointer;" onClick="location.href='/manageOneCustomer/${list.CUSTOMER_NO}' ">
				<td><input type="checkbox"  id="${list.CUSTOMER_NO}" name="${list.CUSTOMER_NO}" value="${list.CUSTOMER_NO}"></td>
				<td>${status.count}</td>
				<td>${list.CUSTOMER_NO}</td>
				<td>${list.NAME}</td>
				<td>${list.ID}</td>
				<td class="tLeft">${list.ADDRESS}</td>
			</tr>
		</c:forEach>
		</table>
	</div>
</div>
<%-- 페이징 테이블 --%>
<div style="text-align: center;">
	<table style="border:0px solid black;width:100%;">
		<tr>
			<td valign="middle">
			<%-- 처음 페이지 설정 --%>
			<c:choose>
				<c:when test="${page.pageNo == 1}" >
					<img alt="처음으로" src="/resources/images/btn_first.gif" align="middle">
				</c:when>
				<c:otherwise>
					<a href="/manageCustomer/1">
						<img alt="처음으로" src="/resources/images/btn_first.gif" align="middle">
					</a>
				</c:otherwise>
			</c:choose>
			
			<%-- 이전 페이지 설정 --%>
			<c:choose>
				<c:when test="${page.beginPage == 1}" >
					<img alt="이전" src="/resources/images/btn_pre.gif" align="middle">
				</c:when>
				<c:otherwise>
					<a href="/manageCustomer/${page.beginPage - 1}">
						<img alt="이전" src="/resources/images/btn_pre.gif" align="middle">
					</a>
				</c:otherwise>
			</c:choose>
			
			<%-- 페이지 번호 설정 --%>			
			<c:forEach var="i" begin="${page.beginPage}" end="${page.endPage}">
				<c:if test="${i eq page.pageNo}">
					<strong>[${i}]</strong>
				</c:if>
				<c:if test="${i ne page.pageNo}">
					<a href="/manageCustomer/${i}">[${i}]</a>
				</c:if>
			</c:forEach>
			
			<%-- 다음 페이지 설정 --%>
			<c:choose>
				<c:when test="${page.endPage == page.lastPage}" >
					<img alt="다음" src="/resources/images/btn_next.gif" align="middle">
				</c:when>
				<c:otherwise>
					<a href="/manageCustomer/${page.endPage + 1}">
						<img alt="다음" src="/resources/images/btn_next.gif" align="middle">
					</a>
				</c:otherwise>
			</c:choose>
			
			<%-- 마지막 페이지 설정 --%>
			<c:choose>
				<c:when test="${page.pageNo == page.lastPage}" >
					<img alt="마지막" src="/resources/images/btn_last.gif" align="middle">
				</c:when>
				<c:otherwise>
					<a href="/manageCustomer/${page.lastPage}">
						<img alt="마지막" src="/resources/images/btn_last.gif" align="middle">
					</a>
				</c:otherwise>
			</c:choose>
			</td>
		</tr>	
	</table>
</div>
</body>
</html>