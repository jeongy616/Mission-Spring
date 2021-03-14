<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/nav.jsp" %>
<script>
$('#nav > ul > li').removeClass('current');
$('#nav > ul > li:nth-child(5)').toggleClass('current');

if("${param.msg}" != ""){
	alert("${param.msg}");
}
</script>
<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-12">
					<section>
						<header class="major">
							<h2>정보 수정하기</h2>
						</header>
						<div class="row">
							<div class="col-6 col-12-small" style="width:100%">
								<section class="box">
									<header>
										<h3>기본 정보</h3>
									</header>
										<form action="/updateMyInfo" method="post" name="fm">
										<table>
											<colgroup>
												<col width="15%">
												<col width="30%">
												<col width="15%">
												<col width="30%">
											</colgroup>
											<tr>
												<td>이름</td>
												<td>${sessionScope.loginInfo.name}</td>
												<td>고객번호 </td>
												<td>${sessionScope.loginInfo.customerNo}</td>
											</tr>
											<tr>
												<td>생년월일</td>
												<td>${sessionScope.loginInfo.birth}</td>
												<td>이메일</td>
												<td><input type="text" value="${sessionScope.loginInfo.email}" name="email"></td>
											</tr>
											<tr>
												<td>주소</td>
												<td colspan="3"><input type="text" value="${sessionScope.loginInfo.address}" name="address"></td>
											</tr>
											<tr>
												<td>휴대폰번호</td>
												<td colspan="3"><input type="text" value="${sessionScope.loginInfo.phoneNumber}" name="phoneNo"></td>
											</tr>
											<tr>
												<td>소득형태</td>
												<td>
													<select name="incomeType1"  id="incomeType1">
														<option value="0" selected></option>
														<option value="소득없음">소득없음</option>	
														<option value="근로소득자">근로소득자</option>	
														<option value="사업소득">사업소득</option>	
														<option value="연금수급자">연금수급자</option>	
													</select>
												</td>
												<td>
													<select name="incomeType2"  id="incomeType2"  style="display:none;">
														<option value="0" selected></option>
														<option value="지역보험가입자">지역보험가입자</option>
														<option value="신용카드사용자">신용카드사용자</option>
													</select>
													<select name="incomeType3"  id="incomeType3"  style="display:none;">
														<option value="0"  selected></option>
														<option value="중소기업">중소기업</option>
														<option value="대기업">대기업</option>
														<option value="공무원">공무원</option>
														<option value="전문직">전문직</option>
													</select>
												</td>
											</tr>
											<tr>
												<td colspan="3">
													<div style="display:none;" id="incomeType4">
													<input type="radio"  name="pro"  value="의료계">의료계
													<input type="radio"  name="pro"  value="교육계">교육계
													<input type="radio"  name="pro"  value="법조계">법조계
													<input type="radio"  name="pro"  value="기타" checked>기타 <input type="text" name="pro_etc" >
													</div>
												</td>
												<td></td>
											</tr>
											<tr>
												<td colspan="1">
													<div id="defaultIncome" class="annualIncome">전년도 소득 </div>
			 										<div id="insurance" class="annualIncome" style="display:none;">3개월 평균 보험료 납부액</div>
													<div id="creditCard" class="annualIncome" style="display:none;">전년도 신용카드 사용액</div>
												</td>
												<td colspan="3"><input type="text" name="incomeMoney"  id="incomeMoney"></td>
											</tr>
											<tr>
												<td colspan="4">
													<div class="desc" id="descEmployee" style="display:none;">* 근로소득자일 경우 원천징수영수증, 1년 미만 재직시 갑종근로소득영수증</div>
													<div class="desc"  id="descBusiness" style="display:none;">*사업소득자일 경우 소득금액증명원을 기준으로 연소득 산정</div>
													<div class="desc"  id="descInsurance" style="display:none;">*국민건강보험공단 납부확인서 기준(단, 요양보험료 제외)</div>
													<div class="desc"  id="descCreditCard" style="display:none;">*카드사 이용내역 문의, 조회 액수 기준</div>
													<div  class="desc" id="descPension" style="display:none;">*전년도 연금수급 합계액</div>
												</td>
											</tr>
										</table>
										<script>
												$("#incomeType1").val("${memberInfo.INCOMETYPE1}").prop("selected",true);
												$("#incomeType2").val("${memberInfo.INCOMETYPE2}").prop("selected",true);
												$("#incomeType3").val("${memberInfo.INCOMETYPE3}").prop("selected",true);
												$("#incomeType4").val("${memberInfo.INCOMETYPE4}").prop("selected",true);
												$("#imcomeMoney").val("${memberInfo.INCOMEMONEY}");
												
												$(".guaranteeType option").not(":selected").attr("disabled", "disabled");
												$(".accountType option").not(":selected").attr("disabled", "disabled");
										</script>
										<input type="button" value="수정하기" onClick="javascript:fm.submit();">
										<input type="button" value="취소하기" onClick="javascript:location.href='/mypage';">
										</form>
							</section>
							</div>
						</div>
					</section>
			</div>
		</div>
	</div>
</section>
<%@ include file="../include/footer.jsp" %>