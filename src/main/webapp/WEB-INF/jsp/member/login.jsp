<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE HTML>
<html>
	<head>
		<title>대출진단웹사이트</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<style>
		body { background-color: #fff; color: #000; padding: 0; margin: 0; }
		.container { width: 900px; margin: auto; padding-top: 1em; }
		.container .ism-slider { margin-left: auto; margin-right: auto; }
		</style>
		<script src="https://cdn.jsdelivr.net/npm/promise-polyfill"></script>
		<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
		<script src="${ pageContext.request.contextPath }/resources/ism/js/ism-2.2.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/sweetalert2@8" ></script>
<script src="sweetalert2.all.min.js" ></script>
<!--===============================================================================================-->
			<link rel="icon" type="image/png" href="${ pageContext.request.contextPath }/resources/images/icons/favicon.ico"/>
			<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/resources/vendor/bootstrap/css/bootstrap.min.css">
			<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/resources/fonts/iconic/css/material-design-iconic-font.min.css">
			<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/resources/vendor/animate/animate.css">
			<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/resources/vendor/css-hamburgers/hamburgers.min.css">
			<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/resources/vendor/animsition/css/animsition.min.css">
			<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/resources/vendor/select2/select2.min.css">
			<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/resources/vendor/daterangepicker/daterangepicker.css">
			<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/resources/css/util.css">
			<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/resources/css/main.css">
<!--===============================================================================================-->
			<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/assets/css/main.css" />
			<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/ism/css/my-slider.css"/>
			<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
			<script>
			if("${param.msg}" != ""){
				alert("${param.msg}");
			}
			</script>
	</head>
	<body class="homepage is-preload">
		<div id="page-wrapper">
				<section id="header">
						<h1><a href="/index">
						<img src="${ pageContext.request.contextPath }/resources/images/main_logo.png" style="width:41px;vertical-align: top;">
						KEB Hana Bank</a></h1>
						<nav id="nav">
							<ul>
								<li><a href="/index">Home</a></li>
								<li><a href="/news">최신동향</a></li>
								<li><a href="/loan">대출진단</a></li>
								<li><a href="/calculator">대출계산기</a></li>
								<li class="current"><a href="/login" style="font-style: italic;">Sign In</a></li>
								<li><a href="/register" style="font-style: italic;">Sign Up</a></li>
							</ul>
						</nav>
						</section>
				</div>
						<div class="container-login100" style="background:#f7f7f7;">
							<div class="wrap-login100 p-l-55 p-r-55 p-t-80 p-b-30">
							
								<form class="login100-form validate-form" method="post" action="/login/checkLogin" name="fm">
									<span class="login100-form-title p-b-37">
										LOGIN
									</span>
										<div class="wrap-input100 validate-input m-b-20" data-validate="Enter username or email">
											<input class="input100" type="text" name="id" placeholder="your id"  style="width:100%">
											<span class="focus-input100"></span>
										</div>
						
										<div class="wrap-input100 validate-input m-b-25" data-validate = "Enter password">
											<input class="input100" type="password" name="password" placeholder="password" style="width:100%">
											<span class="focus-input100"></span>
										</div>
						
										<div class="container-login100-form-btn">
											<input type="submit" class="login100-form-btn"  value="Sign In">
										</div>
					
									<div class="text-center p-t-57 p-b-20">
										<span class="txt1">
											Or login with
										</span>
									</div>
					
									<div class="flex-c p-b-112">
										<a href="#" class="login100-social-item">
											<i class="fa fa-facebook-f"></i>
										</a>
					
										<a href="#" class="login100-social-item">
											<img src="${ pageContext.request.contextPath }/resources/images/icons/icon-google.png" alt="GOOGLE">
										</a>
									</div>
					
									<div class="text-center">
										<a href="/register" class="txt2 hov1">
											Sign Up
										</a>
									</div>
								</form>
					
								
							</div>
						</div>
				
						
						
<!--===============================================================================================-->
	<script src="${ pageContext.request.contextPath }/resources/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="${ pageContext.request.contextPath }/resources/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="${ pageContext.request.contextPath }/resources/vendor/bootstrap/js/popper.js"></script>
	<script src="${ pageContext.request.contextPath }/resources/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="${ pageContext.request.contextPath }/resources/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="${ pageContext.request.contextPath }/resources/vendor/daterangepicker/moment.min.js"></script>
	<script src="${ pageContext.request.contextPath }/resources/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="${ pageContext.request.contextPath }/resources/vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="${ pageContext.request.contextPath }/resources/js/main.js"></script>

<%@ include file="../include/footer.jsp" %>