<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/nav.jsp" %>
<script>
$('#nav > ul > li').removeClass('current');
$('#nav > ul > li:nth-child(5)').toggleClass('current');

function test1(num){
	if(num == 1){
		$('#menu1').css("background",'#008485');
		$('#menu2').css("background",'gray');
		$('#menu3').css("background",'gray');
	}else if(num = 2){
		$('#menu2').css("background",'#008485');
		$('#menu1').css("background",'gray');
		$('#menu3').css("background",'gray');
	}else{
		$('#menu3').css("background",'#008485');
		$('#menu1').css("background",'gray');
		$('#menu2').css("background",'gray');
	}
}
</script>
<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<section>
					<div class="row">
						<div class="col-6 col-12-small" style="width:100%;text-align:center">
							<section>
								<ul class="actions" style="margin-bottom: 0;">
										<li onclick="test1(1)"><a class="button icon solid fa-id-card admin"  id="menu1"  target="admin"  href="/manageCustomer/1">회원관리</a></li>
										<li onclick="test1(2)"><a class="button icon solid fa-check admin"   id="menu2"   style="background:gray" target="admin" href="/manageData/190801">데이터관리</a></li>
<!-- 										<li onclick="test1(2)"><a class="button icon solid fa-check admin"   id="menu2"   style="background:gray" target="admin" href="/manageFile/1">파일관리</a></li> -->
<!-- 										<li onclick="test1(3)"><a class="button icon solid fa-pen admin"  id="menu3"  style="background:gray"  target="admin" href="/main">통계자료</a></li> -->
								</ul>
								<iframe src="/manageCustomer/1" width="100%"  height="700px;" style="border:2px solid gray;margin:20px;box-shadow: 5px 5px 10px;border-radius:10px;" name="admin">
								</iframe>
							</section>
						</div>
					</div>
				</section>
			</div>
		</div>
	</div>
</section>
<%@ include file="../include/footer.jsp" %>


		