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
	border-top:2px solid black;
	border-bottom:2px solid black;
	border-collapse: collapse;
	text-align:center;
	margin:0 auto;
}
table tr td{
	padding:10px;
}
tr td{
	border-top: 1px solid black;
	border-bottom: 1px solid black;
}
.tRight{
	border-right:1px solid black;
}
caption{
	font-size:1.1em;
	font-weight: 700;
}
th{
	background:#008485;
	color:white;
	border-right:1px solid black;
	padding:10px;
}
.tLeft{
	text-align:left;
}
input[type=text]{
	width:80px;
	line-height: 30px;
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
	width:200px;
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
//cofix 추가하기
function selectCofix(){
	var allData = {cofix1:$('#cofixDate').val()};
	alert($('#cofixDate').val());
	$.ajax({
		url:"/manageData/cofix/select",
		method:"put",
		data: JSON.stringify(allData),
		dataType:"json",
		contentType: "application/json; charset=utf-8",
		success: function(data){
			console.log(data.data);
			$('#1').val(data.data.COFIX_NEW_RATE6);
			$('#2').val(data.data.COFIX_NEW_RATE12);
			$('#3').val(data.data.COFIX_BAL_RATE6);
			$('#4').val(data.data.COFIX_BAL_RATE12);
			$('#5').val(data.data.NEWCOFIX_RATE6);
			$('#6').val(data.data.NEWCOFIX_RATE12);
			$('#7').val(data.data.SHORT_COFIX_RATE6);
		}
	});
}
//상품 추가하기 버튼클릭시
function showAddItemTable(){
	if($('#addItemTable').css("display") == "none"){
		$('#addItemTable').show();
	}else{
		alert("추가하시겟습니까?");
		var allData = {title:$('#itemTitile').val()
							,intro:$('#itemIntro').val()
							,link:$('#itemLink').val()
							,character:$('#character').val()
							,job:$('#job').val()
							,context:$('itemContext').val()};
		$.ajax({
			url:"/manageData/item/add",
			method:"put",
			data: JSON.stringify(allData),
			dataType:"json",
			success: function(data){
				console.log(data);
			}
	});
	}
}
// function selectCofix(){
// 	var allData = {itemTitile:$('#itemTitile').val()};
// 	alert($('#cofixDate').val());
// 	$.ajax({
// 		url:"/manageData/cofix/select",
// 		method:"put",
// 		data: JSON.stringify(allData),
// 		dataType:"json",
// 		contentType: "application/json; charset=utf-8",
// 		success: function(data){
// 			console.log(data.data);
// 			$('#1').val(data.data.COFIX_NEW_RATE6);
// 			$('#2').val(data.data.COFIX_NEW_RATE12);
// 			$('#3').val(data.data.COFIX_BAL_RATE6);
// 			$('#4').val(data.data.COFIX_BAL_RATE12);
// 			$('#5').val(data.data.NEWCOFIX_RATE6);
// 			$('#6').val(data.data.NEWCOFIX_RATE12);
// 			$('#7').val(data.data.SHORT_COFIX_RATE6);
// 		}
// 	});
// }
//관리자 데이터 페이지 : cofix 추가
function addCofix(){
	var allData = {cofix1:$('#1').val() 
			  ,cofix2:$('#2').val()
			  ,cofix3:$('#3').val()
			  ,cofix4:$('#4').val()
			,cofix5:$('#5').val()
			,cofix6:$('#6').val()
			,cofix7:$('#7').val()};
	$.ajax({
		url:"/manageData/cofix/add",
		method:"put",
		data: JSON.stringify(allData),
		dataType:"json",
		contentType: "application/json; charset=utf-8",
		success: function(data){
			alert("성공적으로 추가되었습니다.");
		}
	});
}
</script>
</head>
<body>
<div style="text-align: center;">
	<div style="display: inline-block;">	
		조회기준일 : 
		<select name="cofixDate" id="cofixDate">
				<option value="190801">190801</option>
				<option value="190808">190808</option>
				<option value="190816">190816</option>
				<option value="190817">190817</option>
				<option value="190822">190822</option>
				<option value="190823">190823</option>
		</select>
		<input type="button" value="조회" onclick="selectCofix()">
	</div>	
	<br><br>
	<form action="/manageData/" method="post">
		<table>
			<tr>
				<th colspan="4" >COFIX</th>
				<th colspan="2" >신잔액기준COFIX</th>
				<th colspan="2" style="border:0px solid black;">단기COFIX</th>
			</tr>
			<tr>
				<td colspan="2">신규취급액기준</td>
				<td colspan="2" class="tRight">잔액기준</td>
				<td colspan="2" class="tRight">-</td>
				<td colspan="2" >-</td>
			</tr>
			<tr>
				<td>6개월</td>
				<td class="tRight">1년</td>
				<td>6개월</td>
				<td class="tRight">1년</td>
				<td>6개월</td>
				<td class="tRight">1년</td>
				<td>3개월</td>
			</tr>
			<tr>
				<td><input type="text" value="<c:out value="${cofix.COFIX_NEW_RATE6}"/>"  id="1"></td>
				<td class="tRight"><input type="text" value="<c:out value="${cofix.COFIX_NEW_RATE12}"/>"  id="2"></td>
				<td><input type="text" value="<c:out value="${cofix.COFIX_BAL_RATE6}"/>"  id="3"></td>
				<td class="tRight"><input type="text" value="<c:out value="${cofix.COFIX_BAL_RATE12}"/>"  id="4"></td>
				<td><input type="text" value="<c:out value="${cofix.NEWCOFIX_RATE6}"/>"  id="5"></td>
				<td class="tRight"><input type="text" value="<c:out value="${cofix.NEWCOFIX_RATE12}"/>" id="6"></td>
				<td><input type="text" value="<c:out value="${cofix.SHORT_COFIX_RATE6}"/>" id="7"></td>
			</tr>
		</table>
	</form>
	<div style="text-align: center;">
		<div style="display: inline-block;">
			<input type="button" value="추가하기"  onclick="addCofix();">
		</div>
<!-- 	</div> -->
<!-- 	<br><br><br> -->
<!-- 	<form action="/manageData/" method="post"> -->
<!-- 		<table> -->
<%-- 			<caption>상품 조회</caption> --%>
<!-- 			<tr> -->
<!-- 				<td>상품번호</td> -->
<!-- 				<td>상품명</td>				 -->
<!-- 				<td>상품소개</td>				 -->
<!-- 				<td>상품디테일</td> -->
<!-- 				<td>특징</td> -->
<!-- 				<td>직업</td> -->
<!-- 			</tr> -->
<!-- 		</table> -->
<!-- 		<br> -->
<!-- 		<table id="addItemTable" style="display:none;"> -->
<%-- 			<caption>상품 추가</caption> --%>
<!-- 			<tr> -->
<!-- 				<td>상품명</td>				 -->
<!-- 				<td>상품소개</td>				 -->
<!-- 				<td>링크</td>				 -->
<!-- 				<td>특징</td> -->
<!-- 				<td>직업</td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td><input type="text" name="itemTitile" size="100" style="width:150px;"></td>				 -->
<!-- 				<td><input type="text" name="itemIntro" size="150" style="width:300px;"></td>				 -->
<!-- 				<td><input type="text" name="itemLink" size="150" style="width:300px;"></td>				 -->
<!-- 				<td> -->
<!-- 					<select name="character" id="character"> -->
<!-- 						<option value="소득없음">소득없음</option> -->
<!-- 						<option value="신용높음">신용높음</option> -->
<!-- 						<option value="개인사업자">개인사업자</option> -->
<!-- 						<option value="캐피탈">캐피탈</option> -->
<!-- 						<option value="차할부">차할부</option> -->
<!-- 					</select> -->
<!-- 				</td> -->
<!-- 				<td> -->
<!-- 					<select name="job" id="job"> -->
<!-- 						<option value="공무원">공무원</option> -->
<!-- 						<option value="군인">군인</option> -->
<!-- 						<option value="제1금융권 은행원">제1금융권 은행원</option> -->
<!-- 						<option value="정교사 및 국공립유치원">정교사 및 국공립유치원</option> -->
<!-- 						<option value="의사">의사</option> -->
<!-- 						<option value="판사">판사</option> -->
<!-- 						<option value="교수">교수</option> -->
<!-- 						<option value="수의사">수의사</option> -->
<!-- 					</select> -->
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 					<td colspan="4">상품디테일</td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 					<td colspan="4"><textarea  name="itemContext" rows="5" cols="70"> </textarea></td> -->
<!-- 			</tr> -->
<!-- 		</table> -->
<!-- 	</form> -->
<!-- 	<div style="text-align: center;"> -->
<!-- 		<div style="display: inline-block;"> -->
<!-- 			<input type="button" value="추가하기"  onclick="showAddItemTable();"> -->
<!-- 		</div> -->
<!-- 	</div>							 -->
</div>
</body>
</html>