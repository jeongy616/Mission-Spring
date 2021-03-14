<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/nav.jsp" %>
<script>
$('#nav > ul > li').removeClass('current');
$('#nav > ul > li:nth-child(5)').toggleClass('current');
</script>
<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-12">
					<section>
						<header class="major">
							<h2>마이 페이지</h2>
						</header>
						<div class="row">
							<div class="col-6 col-12-small" style="width:100%">
								<section class="box">
									<header>
										<h3>기본 정보</h3>
									</header>
										<table>
											<colgroup>
												<col width="15%">
												<col width="30%">
												<col width="15%">
												<col width="30%">
											</colgroup>
											<tr >
												<td style="text-align:center">이름</td>
												<td>${sessionScope.loginInfo.name}</td>
												<td style="text-align:center">고객번호 </td>
												<td>${sessionScope.loginInfo.customerNo}</td>
											</tr>
											<tr>
												<td style="text-align:center">생년월일</td>
												<td>${memberInfo.BIRTH}</td>
												<td style="text-align:center"s>이메일</td>
												<td>${memberInfo.EMAIL}</td>
											</tr>
											<tr>
												<td style="text-align:center">주소</td>
												<td colspan="3">${memberInfo.ADDRESS}</td>
											</tr>
											<tr>
												<td style="text-align:center">휴대폰번호</td>
												<td>${memberInfo.PHONENUMBER}</td>
												<td style="text-align:center">소득형태</td>
												<td>${memberInfo.INCOMETYPE1}
													<c:if test="${memberInfo.INCOMETYPE1 eq '소득없음'}">
														/ ${memberInfo.INCOMETYPE2}
													</c:if>
													<c:if test="${memberInfo.INCOMETYPE3 ne '0'}">
														/ ${memberInfo.INCOMETYPE3}
													</c:if>
													<c:if test="${memberInfo.INCOMETYPE3 eq '전문직'}">
														/ ${memberInfo.INCOMETYPE4}
													</c:if>
												</td>
											</tr>
											<tr>
												<td style="text-align:center">
													<div id="defaultIncome" class="annualIncome" >전년도 소득 </div>
<!-- 			 										<div id="insurance" class="annualIncome" style="display:none;">3개월 평균 보험료 납부액</div> -->
<!-- 													<div id="creditCard" class="annualIncome" style="display:none;">전년도 신용카드 사용액</div> -->
												</td>
												<td colspan="3">${memberInfo.INCOMEMONEY}원</td>
											</tr>
										</table>
								<input type="button"  value="수정하기"  onclick="location.href='/mypage/update';">
							</section>
							</div>
							<div class="col-6 col-12-small" style="width:100%">
									<section class="box">
									<header>
										<h3>대출 정보</h3>
									</header>
										<table style="width:100%"> 
											<caption class="tLeft">당행대출</caption>
											<tr >
												<th style="text-align: left">가계/기업</th>
												<th style="text-align: center">담보구분</th>
												<th style="text-align: center">계정구분</th>
												<th style="text-align: center">여신받은기관명</th>
												<th style="text-align: rignt">여신금액</th>
											</tr>
											<c:forEach items="${list1}" var="list" varStatus="status">
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
													<select id="accountType${status.index}" class="accountType" style="border:none;">
														<option value="1">은행,카드계정</option>
														<option value="2">캐피탈</option>
														<option value="3">기타</option>
													</select>
												<script>
												$("#guaranteeType${status.index}").val('${list.guaranteeType}');
												$("#accountType${status.index}").val('${list.accountType}');
												</script>
												</td>
												<td><input type="text"  value="${list.agencyLocation}" style="border:none;text-align:center;"readonly></td>
												<td><input type="text"  value="${list.accountMoney}"  style="border:none;text-align:right" readonly></td>
											</tr>
											</c:forEach>
										</table>
										<br>
										<form method= "post" action="/addOtherLoan"  name=fm >
										<table style="width:100%" > 
											<caption class="tLeft">타행대출</caption>
											<colgroup>
												<col width="30%">
												<col width="20%">
												<col width="15%">
												<col width="10%">
												<col width="10%">
												<col width="10%">
											</colgroup>
											<thead>
												<tr>
													<th>담보구분</th>
													<th>계정구분</th>
													<th style="text-align: right">대출금액</th>
													<th style="text-align: right">대출기간</th>
													<th style="text-align: right">대출이율</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${list2}" var="list" varStatus="status">
													<tr>
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
																$("#oguaranteeType${status.index}").val('${list.guaranteeType}');
																$("#oaccountType${status.index}").val('${list.accountType}');
															</script>
														</td>
														<td><input type="text"  value="${list.accountMoney}" style="border:none;text-align:right;width:70%"readonly>원</td>
														<td><input type="text"  value="${list.accountPeriod}"  style="border:none;text-align:right;width:50%" readonly>개월</td>
														<td><input type="text"  value="${list.accountRate}"  style="border:none;text-align:right;width:50%" readonly>%</td>
														<td style="border:none;">
														<input type="button" class="myOtherBankLoanDelBtn"  id="${list.no}"   value="삭제" style="margin:2px"></td>
													</tr>
												</c:forEach>
													
														<tbody id="addLoanInfo">
														  <tr> 
															 <td>
																<select name="guaranteeType" id="guaranteeType">
																	<option value="1">카드(카드론,현금서비스,할부)</option>
																		<option value="2">신용대출 및 비주택 담보대출</option>
																		<option value="3">유가증권담보대출</option>
																		<option value="4">전세보증금담보대출</option>
																		<option value="5">전세자금대출,이주비 중도대출</option>
																		<option value="6">개별 주택담보대출</option>
																</select>
															</td>
															<td>
																<select name="accountType" id="accountType" >
																	<option value="1">은행,카드계정</option>
																	<option value="2">캐피탈</option>
																	<option value="3">기타</option>
																</select>
															</td>
															<td><input type="text"  name='accountMoney' id='accountMoney' style="text-align:center;"></td>
															<td><input type="text"   name='accountPeriod' id='accountPeriod' style="text-align:right" ></td>
															<td><input type='number' name='accountRate' id='accountRate'></td>
															<td><input type="submit" value="저장"></td>
														</tr>
													</tbody>
											</table>
											
										</form>
									</section>
									<div class="col-6 col-12-small" style="width:100%">
										<section class="box">
										<header>
										<h3>서류 제출</h3>
									</header>
											<c:if test="${fn:length(files) ne 0 }"> 
												<form action="/addFileUpload" method="post" enctype="multipart/form-data"  onsubmit="return checkExtension();">
													<table >
														<tr>
															<td>날짜</td>
															<td>재직증명서</td>
															<td>원천징수1년</td>
															<td>갑종근로소득영수증</td>
															<td>현재상황</td>
														</tr>
														<c:forEach items="${files}" var="list">
															<tr>
																<td>${list.regDate}</td>
																<td>
																	<c:if test="${list.originFileName1 eq null }">
																		<input type="file" name="originFileName1" id="originFileName1">																
																	</c:if>
																	<c:if test="${list.originFileName1 ne null }">
																		${list.originFileName1}
																	</c:if>
																</td>
																<td>
																	<c:if test="${list.originFileName2 eq null }">
																		<input type="file" name="originFileName2" id="originFileName2">																
																	</c:if>
																	<c:if test="${list.originFileName2 ne null }">
																		${list.originFileName2}
																	</c:if>
																</td>
																<td>
																	<c:if test="${list.originFileName3 eq null }">
																		<input type="file" name="originFileName3" id="originFileName3">																
																	</c:if>
																	<c:if test="${list.originFileName3 ne null }">
																		${list.originFileName3}
																	</c:if>
																</td>
																<td>
																	<c:if test="${list.status eq 0}">
																		미완료
																	</c:if>
																	<c:if test="${list.status eq 1}">
																		완료
																	</c:if>
																</td>
															</tr>
														</c:forEach>
													</table>
												<input type="submit" value="전송">
												</form>
											</c:if>
												<c:if test="${empty files}">
												<input type="button" value="대출진단하러가기" onclick="location.href='/loan'">
											</c:if>
										</section>
									</div>
							</div>
						</div>
					</section>
			</div>
		</div>
	</div>
</section>
<%@ include file="../include/footer.jsp" %>