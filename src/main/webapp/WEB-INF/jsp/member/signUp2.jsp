<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/nav.jsp" %>
<script>
if('${msg}' != ""){
	Swal.fire({
		  type: 'error',
		  title: 'Oops...',
		  text:'${msg}',
		})
}
function checkNumber(){
	if($('#verificationNumber').val() == $('#verificationInput').val()){
		fm.submit();
	}else{
		Swal.fire({
			  type: 'error',
			  title: 'error',
			  text:'인증번호를 확인해주세요',
		});
		$('#verificationInput').val('');
	}
}
function sendMessage(){
	var phoneNum = $("#phoneNum").val();
	var verification = Math.floor(Math.random() * 1000000)+100000;
	if(verification>1000000){
		verification = verification - 100000;
	}
	$('#verificationNumber').val(verification);
	var myData = {
			phoneNum: phoneNum,
			verification: verification
	}
	$.ajax({
        url: '${ pageContext.request.contextPath }/sendMessage?phoneNum=' + phoneNum + '&verification=' + verification,
        type: "get",
        data: JSON.stringify(myData),
        contentType: "application/json; charset=utf-8",
        success: function(result){
        },
        error: function(){
        }
     });
}
</script>
<script>
$('#nav > ul > li').removeClass('current');
$('#nav > ul > li:nth-child(6)').toggleClass('current');
</script>
<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<section>
					<header class="major">
					<h2>회원가입</h2>
					</header>
					<form name="fm" action="/register3" method="post">
					<div class="row">
						<div class="col-6 col-12-small" style="width:100%;text-align:center">
							<section class="box">
									<ul class="actions" style="margin-bottom: 30px;">
											<li><a class="button icon solid fa-check signup" style="background:gray">약관동의</a></li>
											<li><a class="button icon solid fa-angle-double-right signup"  style="background:white;color:gray!important"></a></li>
											<li><a class="button icon solid fa-id-card signup"    >회원인증</a></li>
											<li><a class="button icon solid fa-angle-double-right signup"  style="background:white;color:gray!important"></a></li>
											<li><a class="button icon solid fa-pen signup"   style="background:gray">회원가입</a></li>
									</ul>
								<table class="signUp2Tbl">
									<colgroup>
										<col width="30%">
										<col width="60%">
									</colgroup>
									<tr>
										<td class="tRight"><b>계좌번호<span style="color:red;">*</span></b></td>
										<td class="tLeft">
										<input type="text" name="account_number" size="30" id="account_number" maxlength="14"
											style="margin: 5px; width: 30%;">
											<span>(14자리 입력)</span>
										</td>
									</tr>
									<tr>
										<td class="tRight"><b>휴대폰인증 <span style="color:red;">*</span></b></td>
										<td  class="tLeft"><input type="text" name="phoneNum"  id="phoneNum" size="10"
										style="margin: 5px; width: 30%;">
										<input type="button" value="인증번호 받기"  onClick="sendMessage()">
										</td>
									</tr>
									<tr>
										<td class="tRight"><b>인증번호 <span style="color:red;">*</span></b></td>
										<td  class="tLeft">
											<input type="text" name="verficationInput"  id="verificationInput" size="10" maxlength="6" style="margin: 5px; width: 30%;display:inline-block">
											<input type="hidden" name="verificationNumber"  id="verificationNumber">
											<span>(6자리 입력)</span>
										</td>
									</tr>
								</table>					
							</section>
						</div>
					</div>
					<footer style="text-align: center">
						<ul class="actions">
							<li><a onclick="checkNumber();" class="button large">다음단계</a></li>
						</ul>
					</footer>
					</form>
				</section>
			</div>
		</div>
	</div>
</section>
<%@ include file="../include/footer.jsp" %>