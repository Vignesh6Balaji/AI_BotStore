package ai_bots.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ai_bots.connection.Db_Con;
import ai_bots.dao.OrderDao;
import ai_bots.model.Cart;
import ai_bots.model.Order;
import ai_bots.model.Users;

public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			request.getSession().getAttribute("auth");
			Users auth = (Users) request.getSession().getAttribute("auth");
			if (auth != null) {
				String pId = request.getParameter("id");
				int q = Integer.parseInt(request.getParameter("quantity"));
				if (q <= 0) {
					q = 1;
				}
				Order order = new Order();
				order.setId(Integer.parseInt(pId));
				order.setuId(auth.getId());
				order.setQ(q);
				order.setDate(formatter.format(date));
				OrderDao odao = new OrderDao(Db_Con.getCon());
				boolean result = odao.insertOrder(order);
				if (result) {
					ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
					if (cart_list!=null) {
						for (Cart c : cart_list) {
							if (c.getId() == Integer.parseInt(pId)) {
								cart_list.remove(cart_list.indexOf(c));
								break;
							}
						}
					}
					response.sendRedirect("Orders.jsp");
				} else {
					out.print("Order Failed");
				}
			} else {
				response.sendRedirect("Login.jsp");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
