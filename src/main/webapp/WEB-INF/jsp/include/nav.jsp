<%@page import="org.springframework.web.context.annotation.SessionScope"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/ism/css/my-slider.css"/>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/assets/css/main.css"  charset="utf-8"/>
<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
<script src="${ pageContext.request.contextPath }/resources/ism/js/ism-2.2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@8" ></script>
<!-- <script src="sweetalert2.all.min.js" ></script> -->
<script src="https://cdn.jsdelivr.net/npm/promise-polyfill"></script>
	</head>
	<body class="homepage is-preload">
		<div id="page-wrapper">
				<section id="header">
						<h1><a href="/index"><img src="${ pageContext.request.contextPath }/resources/images/main_logo.png" style="width:41px;vertical-align: top;">
						KEB Hana Bank</a></h1>
						<nav id="nav">
							<ul>
								<li><a href="/index">Home</a></li>
								<li><a href="/news/190817">최신동향</a></li>
								<li><a href="/loan">대출진단</a></li>
								<li><a href="/calculator">대출계산기</a></li>
								<c:if test="${not empty loginInfo }">
									<c:if test="${'S' eq loginInfo.userType || 'B' eq loginInfo.userType}">
										<li><a href='/admin'>관리자페이지</a></li>
									</c:if>
									<c:if test="${loginInfo.userType eq 'U'}">
										<li><a href='/mypage'>My page</a>
									</c:if>
									<li><a href='logout'>Logout</a><li>
								</c:if>
								<c:if test="${empty loginInfo }">
									<li><a href="/login" style="font-style: italic;">Sign In</a></li>
									<li><a href="/register" style="font-style: italic;">Sign Up</a></li>
								</c:if>
							</ul>
						</nav>
				</section>
</div>
</body>
</html>
