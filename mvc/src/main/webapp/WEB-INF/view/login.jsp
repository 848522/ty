<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link type="text/css" rel="stylesheet" href="./style/style.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
<script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
<style>
table, th, td {
	padding: 5px;
}

table {
	border-spacing: 15px;
}
</style>
<title>Login</title>
</head>

<body>
	<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
		<header class="mdl-layout__header">
			<div class="mdl-layout__header-row">
				<span class="mdl-layout-title">truYum</span> <img
					class="mdl-layout-icon logo" src="./images/truyum-logo-light.png"></img>
				<div class="mdl-layout-spacer"></div>
			</div>
		</header>
		<main class="mdl-layout__content">
			<div class="page-content">
				<div class="mdl-grid">
					<div class="mdl-cell mdl-cell--5-col"></div>
					<div>
						<caption align="top">
							<h5>${logOutMessage}</h5>
							<h5 style="color: red;">${CredentialsError}</h5>
							<h5 style="color: red;">${SessionExpired}</h5>
							<h3>Login</h3>
						</caption>
						<form model="credential" name="loginForm" method="post"
							action="login">
							<table>
								<tr height="50px">
									<td><label for="username" path="username">User ID</label></td>
									<td><input type="text" class="form-control" id="username"
										name="username" required="required"></td>
								</tr>
								<tr>
									<td><label for="password" path="password">Password</label></td>
									<td><input type="password" class="form-control"
										id="password" name="password" required="required"></td>
								</tr>
							</table>
							<br> <br>
							<button
								class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored mdl-js-ripple-effect"
								type="submit" id="login">Login</button>

						</form>
					</div>
					<div class="mdl-cell mdl-cell--3-col"></div>
				</div>
			</div>
		</main>
		<footer class="mdl-mini-footer">
			<div class="mdl-mini-footer__left-section">
				<div class="mdl-logo">Copyright &copy; 2020</div>
			</div>
		</footer>
	</div>
</body>
</html>