<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../include/nav.jsp" %>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
if('${msg}' != ""){
	alert('${msg}');
}
$('#nav > ul > li').removeClass('current');
$('#nav > ul > li:nth-child(6)').toggleClass('current');

</script>
<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<section>
					<form:form method="post" name="fm" commandName="memberVO"  action="${ pageContext.request.contextPath }/register/info" >
					<div class="row">
						<div class="col-6 col-12-small" style="width:100%;text-align:center">
							<section class="box" >
								<header>
									<h2>홈페이지 가입고객</h2>
								</header>
									<ul class="actions" style="margin-bottom: 30px;">
											<li><a class="button icon solid fa-check signup" style="background:gray">약관동의</a></li>
											<li><a class="button icon solid fa-angle-double-right signup"  style="background:white;color:gray!important"></a></li>
											<li><a class="button icon solid fa-id-card signup"    style="background:gray">회원인증</a></li>
											<li><a class="button icon solid fa-angle-double-right signup"  style="background:white;color:gray!important"></a></li>
											<li><a class="button icon solid fa-pen signup"   >회원가입</a></li>
									</ul>
									<div style="display:inline-block;text-align: center;width:100%;max-width:700px;">
									<table class="signUp3Tbl">
										<colgroup>
											<col width="20%">
											<col width="40%">
											<col width="40%">
										</colgroup>
										<tr>
											<td class="tLeft">계좌번호</td>
											<td><input type="text" name="account_number"  value="${account.ACCOUNTNO}"  /></td>
											<td></td>
										</tr>
										<tr>
											<td class="tLeft">고객번호</td>
											<td><form:input path="customerNo" value="${account.CUSTOMERNO}" /></td>
											<td></td>
										</tr>
										<tr>
											<td class="tLeft">아이디</td>
											<td><form:input path="id" id="signUp3Id"/></td><td><form:errors path="id" style="color:red"/></td>
										</tr>
										<tr>
											<td class="tLeft">비밀번호</td>
											<td><form:input type="password"  path="password" id="signUp3Pw"/></td><td><form:errors path="password" style="color:red"/></td>
										</tr>
										<tr>
											<td class="tLeft">비밀번호확인</td>
											<td><form:input type="password" path="passwordCheck"  id="signUp3Pwch"/></td><td><form:errors path="passwordCheck" style="color:red"/></td>
										</tr>
										<tr>
											<td class="tLeft">이름</td>
											<td><form:input path="name" id="signUp3Name"/></td><td><form:errors path="name"/></td>
										</tr>
										<tr>
											<td class="tLeft">생년월일</td>
											<td><form:input path="birth" id="signUp3Birth" /></td><td><form:errors path="birth"/></td>
										</tr>
										<tr>
											<td class="tLeft">주소</td>
											<td><form:input path="address"  id="signUp3Addr"  type="text"/>
											</td>
											<td><form:errors path="address" /></td>
										</tr>
										<tr>
											<td class="tLeft">이메일</td>
											<td><form:input path="email" id="signUp3Email"/></td><td><form:errors path="email"/></td>
										</tr>
										<tr>
											<td class="tLeft">휴대폰번호</td>
											<td><form:input path="phoneNumber" id="signUp3Phone"/></td><td><form:errors path="phoneNumber"/></td>
										</tr>
									</table>		
									</div>
									<footer>
										<ul class="actions">
											<li><a onclick="javascript:fm.submit();" class="button large" id="signUp3Btn">다음단계</a></li>
										</ul>
									</footer>
							</section>
						</div>
					</div>
					
					</form:form>
				</section>
			</div>
		</div>
	</div>
</section>
<%@ include file="../include/footer.jsp" %>




	