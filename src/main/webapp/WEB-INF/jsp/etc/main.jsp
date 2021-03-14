<%@ include file="../include/nav.jsp" %>
<script>
if('${param.msg}' != ""){
	 
	const Toast = Swal.mixin({
	  toast: true,
	  position: 'top-end',
	  showConfirmButton: false,
	  timer: 5000
	})

	Toast.fire({
	  type: 'success',
	  title: 'Signed in successfully'
	})
}
$('#nav > ul > li').removeClass('current');
$('#nav > ul > li:nth-child(1)').toggleClass('current');
</script>
<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<section>
					<div class="row">
						<div class="col-6 col-12-small" style="width:100%;text-align:center">
							<section>
								<div class="ism-slider" data-play_type="once-rewind" data-interval="3000" id="my-slider">
								  <ol>
								   <li>
								      <img src="/resources/ism/image/slides/main_img.PNG">
								      <div class="ism-caption ism-caption-0"></div>
								    </li>
								    <li>
								      <img src="/resources/ism/image/slides/main_banner1.png">
								      <div class="ism-caption ism-caption-0"></div>
								    </li>
								    <li>
								      <img src="/resources/ism/image/slides/main_banner2.png">
								      <div class="ism-caption ism-caption-0"></div>
								    </li>
								    <li>
								      <img src="/resources/ism/image/slides/main_banner3.png">
								      <div class="ism-caption ism-caption-0"></div>
								    </li>
								    <li>
								      <img src="/resources/ism/image/slides/city-690332_1280.jpg">
								      <div class="ism-caption ism-caption-0"></div>
								    </li>
								  </ol>
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


		