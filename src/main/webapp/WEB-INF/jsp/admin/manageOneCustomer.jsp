<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
<style>
body{
	background: #f0f5f5;
}
table{
	border-collapse: collapse;
}
table tr td{
	padding:10px;
}
tr td{
	border-top: 1px solid black;
	border-bottom: 1px solid black;
}
caption{
	font-size:1.1em;
	padding:20px;
	font-weight: 700;
}
th{
	background:#008485;
	color:white;
}
.tLeft{
	text-align:left;
}
input[type=button],
input[type=submit]{
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
<script>
$(document).ready(function(){
	$("#chk_all").click(function(){
		if($("#chk_all").is(":checked")){
			$(".chk").prop("checked",true);
		}else{
			$(".chk").prop("checked",false);
		}
	});
	$('#checkFiledata').click(function(){
		
	})
});
</script>
</head>
<body>
<div style="text-align: center">
	<div style="display: inline-block;width:900px;">
		<table style="width:80%; border-left:none; border-right:none; border-top:2px solid black; border-bottom:2px solid black; margin:0px auto;">
			<caption>
				고객 기본 정보
			</caption>
			<colgroup>
				<col width="20%">
				<col width="80%">
			</colgroup>
			<tr>
				<td><b>이름</b></td>
				<td>${oneInfo.get("info").NAME}</td>
			</tr>
			<tr>
				<td><b>이메일</b></td>
				<td>${oneInfo.get("info").EMAIL}</td>
			</tr>
			<tr>
				<td><b>주소</b></td>
				<td>${oneInfo.get("info").ADDRESS}</td>
			</tr>
			<tr>
				<td><b>연락처</b></td>
				<td >${oneInfo.get("info").PHONENUMBER}</td>
			</tr>
			<tr>
				<td><b>소득형태</b></td>
				<td>${oneInfo.get("info").INCOMETYPE1}  
					   <c:if test="${'소득없음' eq oneInfo.get('info').INCOMETYPE1}">
					   	/ ${oneInfo.get("info").INCOMETYPE2}
					   </c:if>
					   <c:if test="${'근로소득자' eq oneInfo.get('info').INCOMETYPE1}">
					   	/ ${oneInfo.get("info").INCOMETYPE3}
					   		<c:if test="${'전문직' eq oneInfo.get('info').INCOMETYPE4}">
							   	/ ${oneInfo.get("info").INCOMETYPE4}
							   	<c:if test="${'기타' eq oneInfo.get('info').PROETC}">
							   		/ ${oneInfo.get("info").PROETC}
							   	</c:if>
					   		</c:if>
					   </c:if>
				</td>
			</tr>
			<tr>
				<td><b>전년도 소득</b></td>
				<td>${oneInfo.get("info").INCOMEMONEY} 원</td>
			</tr>
		</table>
		<form action="/manageData/file/check" method="post">
			<table style="border:1px solid gray;width:100%" id="main">
				<caption>서류제출 미완료</caption>
				<colgroup>
					<col width="6%">
					<col width="20%">
					<col width="20%">
					<col width="20%">
					<col width="20%">
					<col width="12%">
				</colgroup>
				<tr>
					<th></th>
					<th>날짜</th>
					<th>파일명1</th>
					<th>파일명2</th>
					<th>파일명3</th>
					<th>상담여부</th>
				</tr>	
			<c:forEach items="${oneInfo.get('file')}" var="list" varStatus="status">
				<tr>
					<td><input type="hidden" name="customer_no" value="${list.customerNo}"><input type="checkbox"  id="${list.no}"  name="checkName" value="${list.no}" class="chk"></td>
					<td>${list.regDate}</td>
					<td class="tLeft">
					<a href="#" 
					onclick="window.open('/upload/${list.saveFileName1}','${oneInfo.get('info').NAME}님의 서류',)">${list.originFileName1}</a>
					</td>
					<td class="tLeft">
					<a href="#" 
					onclick="window.open('/upload/${list.saveFileName2}','${oneInfo.get('info').NAME}님의 서류',)">${list.originFileName2}</a>
					</td>
					<td class="tLeft">
					<a href="#" 
					onclick="window.open('/upload/${list.saveFileName3}','${oneInfo.get('info').NAME}님의 서류',)">${list.originFileName3}</a>
					</td>
					<td>미확인	</td>
				</tr>
			</c:forEach>
			</table>
		<input type="submit" value="서류제출완료"  id="checkFiledata">
		</form>
		<br>
	</div>
</div>

</body>
</html>