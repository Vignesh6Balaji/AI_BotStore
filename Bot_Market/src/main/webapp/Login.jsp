<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="ai_bots.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<%@include file="Includes/Head.jsp"%>
<style>
.toaster {
	position: fixed;
	top: 10px;
	right: 10px;
	z-index: 9999;
}
</style>
</head>
<body>
	<%@include file="Includes/Navbar.jsp"%>
	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">
			<i class="fa-solid fa-arrow-right-to-bracket fa-2x"></i>
			<h5>Login</h5>
			</div>
			<div class="card-body">
				<form action="login" method="post">

					<div class="form-group">
						<label>Email Address : </label> <input
							placeholder="Enter your Email Id" class="form-control"
							name="Login-email" type="email" required>
					</div> <br>

					<div class="form-group">
						<label>Password : </label> <input placeholder="********"
							class="form-control" name="Login-password" type="password"
							required>
					</div><br>

					<div class="text-center">
						<button type="submit" class="btn btn-primary" onclick="showToaster()">Login</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@include file="Includes/Footer.jsp"%>
</body>
</html>