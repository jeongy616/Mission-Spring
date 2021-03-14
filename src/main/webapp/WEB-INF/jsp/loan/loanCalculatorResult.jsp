<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../include/nav.jsp" %>
<script>
$('#nav > ul > li').removeClass('current');
$('#nav > ul > li:nth-child(4)').toggleClass('current');
</script>
<section id="main">
<div class="container">
<div class="row">
	<div class="col-12">
		<section>
			<header class="major">
				<h2>대출 계산기 결과</h2>
			</header>
			<div class="row">
				<div class="col-6 col-12-small" style="width:100%;text-align:center;">
					<section class="box">
						<div style="display:inline-block;">
							<table class="small-tbl" style="text-align: right">
								<colgroup>
									 <col width=25%>
									 <col width=25%>
									 <col width=25%>
									 <col width=25%>
								</colgroup>
								<tr>
									<td >대출금</td>
									<td>대출기간</td>
									<td>대출금리</td>
									<td>거치기간</td>
								</tr>
								<tr>
									<td>${ loan1.loanPrice }원</td>
									<td>${loan1.loanPeriod }개월</td>
									<td>연 ${ loan1.loanRate}% </td>
									<td>${loan1.holdingPeriod }개월 </td>
								</tr>
								 <tr>
								  	<td colspan="2">평균이자납부액</td>
								  	<td></td>
								  	<td></td>
								  	<td></td>
								 </tr>
							</table>
								<footer>
									<ul class="actions">
										<li><a href="javascript:location.href='/loan'" class="button icon solid fa-calculator" id="calBtn">다시계산하기</a></li>
										<li><a class="button icon solid fa-clipboard"  id="detailBtn" >자세히보기</a></li>
									</ul>
								</footer>
								<div style="display:none;" id="detailedLoanResult">
									<c:forEach items="${loan}" var="loanlist">
										<table>
											<tr>
												<td>no</td>
												<td>상환금</td>
												<td>납입원금</td>
												<td>이자</td>
												<td>납입원금계</td>
												<td>잔금</td>
											</tr>
											<c:forEach var="i" begin="1" end="${loanlist.loanPeriod}">
												<tr>
													<td><c:out value ="${i}"/></td>
													<td>
															<fmt:formatNumber value="${loanlist.monthlyPayment[i]}" pattern="#.##"/>
															<c:out value ="${loanlist.monthlyPayment[i]}" />
													</td>
													<td><c:out value ="${loanlist.payment[i]}" /></td>
													<td><c:out value ="${loanlist.interest[i]}" /></td>
													<td><c:out value ="${loanlist.accumulatePayment[i]}" /></td>
													<td><c:out value ="${loanlist.theBalance[i]}" /></td>
												</tr>
											</c:forEach>
										</table>
									</c:forEach>
								</div>
							</div>
						</section>
					</div>
				</div>
			</section>
	</div>
</div>
</div>

</section>

<%@ include file="../include/footer.jsp" %>
