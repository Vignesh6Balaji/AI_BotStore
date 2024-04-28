package ai_bots.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ai_bots.connection.Db_Con;
import ai_bots.dao.OrderDao;
import ai_bots.model.Cart;
import ai_bots.model.Order;
import ai_bots.model.Users;

public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out=response.getWriter()){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			request.getSession().getAttribute("auth");
			Users auth = (Users) request.getSession().getAttribute("auth");
			if(!cart_list.isEmpty() && auth!=null) {
				for(Cart c : cart_list) {
					Order order = new Order();
					order.setId(c.getId());
					order.setuId(auth.getId());
					order.setQ(c.getQuantity());
					order.setDate(formatter.format(date));
					OrderDao odao = new OrderDao(Db_Con.getCon());
					boolean result = odao.insertOrder(order);
					if(!result)break;
				}
				cart_list.clear();
				response.sendRedirect("Orders.jsp");
			}
			else {
				if(auth==null)response.sendRedirect("Login.jsp");
				response.sendRedirect("Cart.jsp");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
