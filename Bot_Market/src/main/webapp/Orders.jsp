<%@page import="com.mysql.cj.x.protobuf.MysqlxSession.AuthenticateContinue"%>
<%@page import="ai_bots.dao.OrderDao"%>
<%@page import="ai_bots.model.*"%>
<%@page import="ai_bots.connection.Db_Con"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Orders</title>
<%@include file="Includes/Head.jsp"%>
</head>
<body>
	<%@include file="Includes/Navbar.jsp"%>
	<div class="container">
		<div class="card-header my-3">All Orders</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Date</th>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Quantity</th>
					<th scope="col">Price</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
			<%
			Users a = (Users) request.getSession().getAttribute("auth");
			List<Order> order=null;
			if (a != null) {
				request.setAttribute("auth", a);
				OrderDao odao=new OrderDao(Db_Con.getCon());
				order=odao.userOrder(a.getId());
			}
			ArrayList<Cart> cartlist = (ArrayList<Cart>) session.getAttribute("cart-list");
			if(cartlist!=null){
				request.setAttribute("cart-list", cartlist);
			}
			if(order!=null){
				for(Order o:order){%>
					<tr>
					<td><%=o.getDate() %></td>
					<td><%=o.getName() %></td>
					<td><%=o.getCategory() %></td>
					<td><%=o.getQ() %></td>
					<td><%=o.getPrice() %></td>
					<td><a class="btn btn-sm btn-danger"
						href="cancelorder?id=<%=o.getOrderId()%>">Cancel</a></td>
					</tr>
				<%}
			}
			%>
			</tbody>
		</table>
	</div>
	<%@include file="Includes/Footer.jsp"%>
</body>
</html>