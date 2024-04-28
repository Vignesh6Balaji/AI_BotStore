<%@page import="ai_bots.dao.ProductDao"%>
<%@page import="java.util.*"%>
<%@page import="ai_bots.connection.Db_Con"%>
<%@page import="ai_bots.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
<%@include file="Includes/Head.jsp"%>
<style type="text/css">
.table tbody td {
	vertical-align: middle;
}

.btn-incre, .btn-decre {
	box-shadow: none;
	font-size: 25px;
}
</style>
</head>
<body>
	<%@include file="Includes/Navbar.jsp"%>
	<div class="container">
		<div class="d-flex py-3">
			<h3>Total Price : $ ${ (total>0)?dcf.format(total):0 }</h3>
			<a class="mx-3 btn btn-primary" href="checkout">Check Out</a>
		</div>
		<table class="table table-loght">
			<thead>
				<tr>
					<th scope="col">Name :</th>
					<th scope="col">Category :</th>
					<th scope="col">Price :</th>
					<th scope="col">Buy Now</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (cart_list != null) {
					for (Cart c : cartProduct) {
				%>
				<tr>
					<td><%=c.getName()%></td>
					<td><%=c.getCategory()%></td>
					<td><%=dcf.format(c.getPrice())%>$</td>
					<td>
						<form action="buynow" method="post" class="form-inline">
							<input type="hidden" name="id" value="<%=c.getId()%>"
								class="form-input">
							<div class="form-group d-flex justify-content-between">
								<a class="btn btn-sm btn-decre"
									href="inc-dec?action=dec&id=<%=c.getId()%>"><i
									class="fas fa-minus-square"></i></a> <input type="text"
									name="quantity" class="form-control"
									value="<%=c.getQuantity()%>" readonly> <a
									class="btn btn-sm btn-incre"
									href="inc-dec?action=inc&id=<%=c.getId()%>"><i
									class="fas fa-plus-square"></i></a>
								<button type="submit" class="btn btn-primary btn-sm w-25">Buy</button>
							</div>
						</form>
					</td>
					<td><a class="btn btn-sm btn-danger"
						href="remove?id=<%=c.getId()%>">Remove</a></td>
				</tr>
				<%
				}
				}
				%>
			</tbody>
		</table>
	</div>
	<%@include file="Includes/Footer.jsp"%>
</body>
</html>