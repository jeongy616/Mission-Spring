<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../include/nav.jsp" %>
<script>
$('#nav > ul > li').removeClass('current');
$('#nav > ul > li:nth-child(3)').toggleClass('current');
</script>
<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-12">
					<section>
						<header class="major">
							<h2>셀프 대출 진단 하기</h2>
						</header>
						<div class="row">
							<div class="col-6 col-12-small" style="width:100%">
								<section class="box">
									<header>
										<h3>기본 정보 확인</h3>
									</header>
										<table>
											<colgroup>
												<col width="15%">
												<col width="40%">
												<col width="40%">
											</colgroup>
											<tr>
												<td>이름</td>
												<td><input type="text" name="customerName" value="${sessionScope.loginInfo.name}" style="border:none;" readonly></td>
												<td>고객번호 <input type="text" name="customerNumber" value="${sessionScope.loginInfo.customerNo}"  style="border:none;" readonly></td>
											</tr>
											<tr>
												<td>생년월일</td>
												<td colspan="2"><input type="text" name="birthday"  value="${sessionScope.loginInfo.birth}"   style="border:none;" readonly></td>
											</tr>
											<tr>
												<td>소득형태</td>
												<td>${income.INCOME_TYPE1}
													<c:if test="${income.INCOME_TYPE1 eq '소득없음'}">
														/ ${income.INCOME_TYPE2}
													</c:if>
													<c:if test="${income.INCOME_TYPE3 ne '0'}">
														/ ${income.INCOME_TYPE3}
													</c:if>
													<c:if test="${income.INCOME_TYPE3 eq '전문직'}">
														/ ${income.INCOME_TYPE4}
													</c:if>
												</td>
											</tr>
											<tr>
												<td>
													<c:if test="${'지역보험가입자' eq income.INCOME_TYPE2 && '소득없음' eq income.INCOME_TYPE1}">3개월 평균 보험료 납부액</c:if>
													<c:if test="${'신용카드사용자' eq income.INCOME_TYPE2 && '소득없음' eq income.INCOME_TYPE1}">전년도 신용카드 사용액</c:if>
													<c:if test="${'지역보험가입자' ne income.INCOME_TYPE2 || '신용카드사용자' ne income.INCOME_TYPE2}">전년도 소득</c:if>
												</td>
												<td>${income.INCOME_MONEY}원</td>
												<td></td>
											</tr>
										</table>
							</section>
							</div>
							
							<div class="col-6 col-12-small" style="width:100%">
								<form method="get" action="/myStatus" >
								<section class="box">
									<header>
										<h3>대출 정보</h3>
									</header>
										<table style="width:100%"> 
											<caption class="tLeft">당행대출</caption>
											<tr>
												<th style="text-align: left">가계/기업</th>
												<th style="text-align: center">담보구분</th>
												<th style="text-align: center">계정구분</th>
												<th style="text-align: center">여신받은기관명</th>
												<th style="text-align: right;padding-right:50px;">여신금액</th>
											</tr>
											<c:forEach items="${accounts}" var="list" varStatus="status">
											<tr>
												<td>가계</td>
												<td>
													<select id="guaranteeType${status.index}" class="guaranteeType" style="border:none;">
														<option value="1">카드(카드론,현금서비스,할부)</option>
															<option value="2">신용대출 및 비주택 담보대출</option>
															<option value="3">유가증권담보대출</option>
															<option value="4">전세보증금담보대출</option>
															<option value="5">전세자금대출,이주비 중도대출</option>
															<option value="6">개별 주택담보대출</option>
													</select>
												</td>
												<td>
													<c:if test="${list.accountType eq '1'}">
														<c:out value="은행,카드계정" />
													</c:if>
													<c:if test="${list.accountType eq '2'}">
														<c:out value="캐피탈" />
													</c:if>
													<c:if test="${list.accountType eq '3'}">
														<c:out value="기타" />
													</c:if>
													<select id="accountType${status.index}" class="accountType" style="border:none;">
														<option value="1" >은행,카드계정</option>
														<option value="2">캐피탈</option>
														<option value="3">기타</option>
													</select>
												<script>
												$("#guaranteeType${status.index}").val("${list.guaranteeType}").prop("selected",true);
												$("#accountType${status.index}").val("${list.accountType}").prop("selected",true);
												</script>
												</td>
												<td><input type="text"  value="${list.agencyLocation}" style="border:none;text-align:center;"readonly></td>
												<td><input type="text"  value="${list.accountMoney}원"  style="border:none;text-align:right" readonly></td>
											</tr>
											</c:forEach>
										</table>
										<br>
										<table style="width:100%" > 
											<caption class="tLeft">타행대출</caption>
											<colgroup>
												<col width="10%">
												<col width="30%">
												<col width="20%">
												<col width="15%">
												<col width="10%">
												<col width="10%">
											</colgroup>
											<thead>
												<tr>
													<th style="text-align: center">가계/기업</th>
													<th>담보구분</th>
													<th>계정구분</th>
													<th style="text-align: right">대출금액</th>
													<th style="text-align: right">대출기간</th>
													<th style="text-align: right">대출이율</th>
												</tr>
											</thead>
											<tbody id="addLoanInfo">
												<c:forEach items="${otherAccounts}" var="list" varStatus="status">
													<tr>
														<td>가계</td>
														<td>
															<select id="oguaranteeType${status.index}" class="guaranteeType" style="border:none;">
																<option value="1">카드(카드론,현금서비스,할부)</option>
															<option value="2">신용대출 및 비주택 담보대출</option>
															<option value="3">유가증권담보대출</option>
															<option value="4">전세보증금담보대출</option>
															<option value="5">전세자금대출,이주비 중도대출</option>
															<option value="6">개별 주택담보대출</option>
															</select>
														</td>
														<td>
															<select id="oaccountType${status.index}" class="accountType" style="border:none;">
																<option value="1">은행,카드계정</option>
																<option value="2">캐피탈</option>
																<option value="3">기타</option>
															</select>
														<script>
														$("#oguaranteeType${status.index}").val("${list.guaranteeType}").prop("selected",true);
														$("#oaccountType${status.index}").val("${list.accountType}").prop("selected",true);
														</script>
														</td>
														<td><input type="text"  value="${list.accountMoney}" style="border:none;text-align:right;"readonly>원</td>
														<td><input type="text"  value="${list.accountPeriod}"  style="border:none;text-align:right;width:60%" readonly>개월</td>
														<td><input type="text"  value="${list.accountRate}"  style="border:none;text-align:right;;width:60%" readonly>%</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</section>
									<div style="text-align:right">
										<input type="button"  id="checkLoanMyselfBtn" value="대출진단하기">
										<input type="button" onclick="javascript:location.href='/mypage'" value="정보수정하기">
									</div>
								</form>
							</div>
						</div>
					</section>
			</div>
		</div>
	</div>
</section>
<%@ include file="../include/footer.jsp" %>