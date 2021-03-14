<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
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
						<div class="row">
							<div class="col-6 col-12-small" >
								<section class="box">
									<form action="loan/result" method="post" name=fm>
									  <table class="small-tbl">
									    <colgroup>
									      <col width=20%>
									      <col width=70%>
									    </colgroup>
									    <tr>
									      <td class="table1_title">대출금</td>
									      <td class="button_td">
									        <input type="text" id="loanPrice" name="loanPrice" class="calcInput"/> <b>원</b> <br>
									        <input type="button" value="+100만원" class="price" id="1000000">
									        <input type="button" value="+500만원" class="price" id="5000000">
									        <input type="button" value="+1000만원" class="price" id="10000000">
									        <input type="button" value="+5000만원" class="price" id="50000000">
									      </td>
									    </tr>
									    <tr>
									      <td class="table1_title">대출금리</td>
									      <td class="button_td">
									        <input type="text" id="loanRate" name="loanRate" class="calcInput"/> <b>%</b> <br>
									        <input type="button" value="+0.1%" class="rate" id="0.1">
									        <input type="button" value="+0.5%" class="rate" id="0.5">
									        <input type="button" value="+1%" class="rate" id="1">
									        <input type="button" value="+3%" class="rate" id="3">
									        <input type="button" value="+5%" class="rate" id="5">
									        <input type="button" value="+10%" class="rate" id="10">
									      </td>
									    </tr>
									    <tr>
									      <td class="table1_title">대출기간</td>
									      <td class="button_td">
									        <input type="text" id="loanPeriod" name="loanPeriod"  class="calcInput"/> <b>개월</b> <br>
									        <input type="button" value="+1개월" class="period" id="1">
									        <input type="button" value="+1년" class="period" id="12">
									        <input type="button" value="+2년" class="period" id="24">
									        <input type="button" value="+3년" class="period" id="36">
									        <input type="button" value="+5년" class="period" id="60">
									      </td>
									    </tr>
									    <tr style="display:none;">
									      <td class="table1_title">거치기간</td>
									      <td class="button_td">
									        <input type="text" id="holdingPeriod" name="holdingPeriod"  class="calcInput" value="0"/> <b>개월</b><br>
									        <input type="button" value="+0개월" class="holding" id="0">
									        <input type="button" value="+1개월" class="holding" id="1">
									        <input type="button" value="+6개월" class="holding" id="6">
									        <input type="button" value="+1년" class="holding" id="12">
									        <input type="button" value="+2년" class="holding" id="24">
									      </td>
									    </tr>
									    <tr>
									    </tr>
									  </table>
										<footer>
											<ul class="actions">
												<li><a class="button icon solid fa-calculator" id="calBtn">계산하기</a></li>
												<li><a href="#" class="button alt icon solid fa-arrow-left" id="resetBtn">다시작성</a></li>
											</ul>
										</footer>
									</form>
								</section>
							</div>
							<div class="col-6 col-12-small">
								<section class="box">
										<table class="small-tbl" style="text-align: right;width:90%;">
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
												<td id="loanPriceInput">0원</td>
												<td id="loanPeriodInput">0개월</td>
												<td id="loanRateInput">0%</td>
												<td id="holdingPeriodInput">0개월</td>
											</tr>
										</table>
										<img src="/resources/images/loanGraph.png" width="90%">
										<table id="loangraphBottom">
											<tr>
												
											</tr>
										</table>
											<footer>
												<ul class="actions">
													<li><a class="button icon solid fa-clipboard"  id="myBtn" >자세히보기</a></li>
												</ul>
											</footer>
												<!-- Trigger/Open The Modal -->
			<!-- 									    <input type="button" id="myBtn" value="open"> -->
												    <!-- The Modal -->
										    <div id="myModal" class="modal">
										 
										      <!-- Modal content -->
										      <div class="modal-content" style="text-align: right;'">
										        <div class="closeModal" style="cursor:pointer;display:inline-block;">
										        	<h2>x</h2>
										        </div>      
										                                                                 
										        <div style="text-align: center;">
										        <input type="radio" id="loanMethodBtn1"  class="loanMethodBtn" name="loanMethodBtn" checked/>원리금균등상환
										        <input type="radio" id="loanMethodBtn2"  class="loanMethodBtn" name="loanMethodBtn"/>원금균등상환
										        <input type="radio" id="loanMethodBtn3"  class="loanMethodBtn" name="loanMethodBtn"/>만기일시상환
										        </div>
													<table id="loanMethod2" >
														<tr >
															<td>no</td>
															<td>상환금</td>
															<td>납입원금</td>
															<td>이자</td>
															<td>납입원금계</td>
															<td>잔금</td>
														</tr>
													</table>
													<table id="loanMethod1"  style="display:none;">
														<tr>
															<td>no</td>
															<td>상환금</td>
															<td>납입원금</td>
															<td>이자</td>
															<td>납입원금계</td>
															<td>잔금</td>
														</tr>
													</table>
													<table id="loanMethod0"  style="display:none;">
														<tr>
															<td>no</td>
															<td>상환금</td>
															<td>납입원금</td>
															<td>이자</td>
															<td>납입원금계</td>
															<td>잔금</td>
														</tr>
													</table>
										      </div>
										    </div>
										</div>
									</section>
								</div>
							</div>
						</section>
				</div>
			</div>
		</section>
<%@ include file="../include/footer.jsp" %>
