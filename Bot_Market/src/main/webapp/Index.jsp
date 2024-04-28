<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="ai_bots.dao.ProductDao"%>
<%@page import="ai_bots.connection.Db_Con"%>
<%@page import="ai_bots.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
ProductDao pd = new ProductDao(Db_Con.getCon());
List<Products> prod = pd.getAllProducts();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<%@include file="Includes/Head.jsp"%>
</head>
<body>
	<%@include file="Includes/Navbar.jsp"%>
	<div class="container">
		<div class="card-header my-3">All Products</div>
		<div class="row">
			<%
			if (!prod.isEmpty()) {
				for (Products pr : prod) {
			%>
			<div class="col-md-3 my-3">
				<div class="card w-100" style="width: 18rem;">
					<img src="Product_Images/<%=pr.getImage()%>" class="card-img-top"
						alt="<%=pr.getImage()%>">
					<div class="card-body">
						<h5 class="card-title"><%=pr.getName()%></h5>
						<h6 class="price">
							Price :<%=pr.getPrice()%>$
						</h6>
						<h6 class="category">
							Category :<%=pr.getCategory()%></h6>
						<div class="mt-3 d-flex justify-content-between"></div>
						<a href="add-to-cart?id=<%=pr.getId()%>" class="btn btn-dark">Add
							to Cart</a> <a href="buynow?quantity=1&id=<%=pr.getId()%>" class="btn btn-primary">Buy Now</a>
					</div>
				</div>
			</div>
			<%
			}
			}
			%>
		</div>
	</div>
	<%@include file="Includes/Footer.jsp"%>
</body>
</html>