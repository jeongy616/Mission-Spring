<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/nav.jsp" %>
<script>
function selectCofix(){
	var allData = {cofix1:$('#cofixDate').val()};
// 	alert($('#cofixDate').val());
	$.ajax({
		url:"/news/cofix/select",
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
</script>
<!-- Main -->
<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<!-- Blog -->
					<section>
						<header class="major">
							<h2>최신 동향</h2>
						</header>
						<div class="row">
							<div class="col-6 col-12-small">
								<section class="box">
									<header>
										<h3>COFIX 기준금리</h3>
									</header>
										<div style="text-align: center;">
											<div style="display: inline-block;">	
												조회기준일 : 
												<select name="cofixDate" id="cofixDate">
														<option value="190801">190801</option>
														<option value="190808">190808</option>
														<option value="190816">190816</option>
														<option value="190817">190817</option>
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
										</div>
								</section>
							</div>
							<div class="col-6 col-12-small">
								<section class="box">
									<header>
										<h3>최신 소식</h3>
										<p></p>
									</header>
									<table>
										<tr>
											<td>
												<a href="http://www.ebn.co.kr/news/view/967060">
												<img src="/resources/images/news1.PNG">
												</a>
											</td>
										</tr>
										<tr>
											<td>
												<a href="http://news.mtn.co.kr/newscenter/news_viewer.mtn?gidx=2019081617002180248">
												<img src="/resources/images/news2.PNG">
												</a>
											</td>
										</tr>
										
									</table>
								</section>
							</div>
						</div>
					</section>

			</div>
		</div>
	</div>
</section>
<%@ include file="../include/footer.jsp" %>