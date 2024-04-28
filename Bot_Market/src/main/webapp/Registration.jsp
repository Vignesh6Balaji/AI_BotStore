<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
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
				<i class="fa-solid fa-id-badge fa-2x"></i>
				<h5>Registration</h5>
			</div>
			<%
			String reg = (String) session.getAttribute("reg-mes");
			%>
			<div class="toaster" id="toasterContainer"></div>

			<div class="card-body">
				<form action="registration" method="post">
					<div class="form-group">
						<label>Full Name : </label> <input
							placeholder="Enter your Full Name" class="form-control"
							name="Reg-name" type="text" required>
					</div>
					<br>

					<div class="form-group">
						<label>Email Address : </label> <input
							placeholder="Enter your Email Id" class="form-control"
							name="Reg-email" type="email" required>
					</div>
					<br>

					<div class="form-group">
						<label>Password : </label> <input placeholder="********"
							class="form-control" name="Reg-password" type="password" required>
					</div>
					<br>

					<div class="text-center">
						<button type="submit" class="btn btn-primary"
							onclick="showToaster()">Register</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@include file="Includes/Footer.jsp"%>
	<script>
  function showToaster() {
	  const toasterContainer = document.getElementById('toasterContainer');
      const toasterHTML = `
        <div class="alert alert-success alert-dismissible fade show" role="alert" data-bs-autohide="false">
          <%=reg%>
          <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
        </div>
      `;
      toasterContainer.innerHTML = toasterHTML;
      setTimeout(function() {
          toaster.style.display = 'none';
        }, 5000);
  }
</script>
</body>
</html>