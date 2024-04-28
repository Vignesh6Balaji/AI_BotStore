<%@page import="java.text.DecimalFormat"%>
<%@page import="ai_bots.dao.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="ai_bots.connection.Db_Con"%>
<%@page import="ai_bots.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
DecimalFormat dcf=new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
List<Cart> cartProduct = null;
if (cart_list != null) {
	ProductDao pao = new ProductDao(Db_Con.getCon());
	cartProduct = pao.getCartProducts(cart_list);
	request.setAttribute("cart_list", cart_list);
	double total=pao.getTotalPrice(cart_list);
	request.setAttribute("total", total);
}
%>
<%
Users auth = (Users) request.getSession().getAttribute("auth");
List<Order> orders;
if (auth != null) {
	request.setAttribute("auth", auth);
	orders=new OrderDao(Db_Con.getCon()).userOrder(auth.getId());
}

List list = (List) request.getAttribute("cart_list");
int size = 0;
if (list != null) {
	size = list.size();
}
%>
<div class="container">
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="Index.jsp">Welcome To The Future</a>
			<div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
				<ul class="navbar navbar-nav" >
					<li class="nav-item" style="padding-left: 30px; text-align: center;"><i class="fa-solid fa-house fa-2x"></i><a class="nav-link active"
						aria-current="page" href="Index.jsp">Home</a></li>
					<li
						class="btn btn-primary position-relative bg-light text-dark border-0 nav-item" style="padding-left: 30px"><i class="fa-solid fa-cart-shopping fa-2x"></i><a
						class="nav-link" href="Cart.jsp">Cart<span
							class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger"><%=size%></span>
							</a></li>
					<%
					if (auth != null) {
					%>
					<li class="nav-item" style="padding-left: 30px; text-align: center;"><i class="fa-solid fa-truck fa-2x"></i><a class="nav-link" href="Orders.jsp">Orders</a></li>
					<li class="nav-item" style="padding-left: 30px; text-align: center;"><i class="fa-solid fa-right-from-bracket fa-2x"></i><a class="nav-link" href="Logout">Logout</a></li>
					<%
					} else {
					%>
					<li class="nav-item" style="padding-left: 30px; text-align: center;"><i class="fa-solid fa-arrow-right-to-bracket fa-2x"></i><a class="nav-link" href="Login.jsp">Login</a></li>
					<li class="nav-item" style="padding-left: 30px; text-align: center;"><i class="fa-solid fa-id-badge fa-2x"></i><a class="nav-link" href="Registration.jsp">Registration</a></li>
					<%
					}
					%>
				</ul>
			</div>
		</div>
	</nav>
</div>