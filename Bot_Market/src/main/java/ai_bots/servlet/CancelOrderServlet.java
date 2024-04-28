package ai_bots.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import ai_bots.connection.Db_Con;
import ai_bots.dao.OrderDao;

@WebServlet("/cancelorder")
public class CancelOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()) {
			String id = request.getParameter("id");
			if (id != null) {
				OrderDao odao = new OrderDao(Db_Con.getCon());
				odao.cancelOrder(Integer.parseInt(id));
			}
			response.sendRedirect("Orders.jsp");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	
	  protected void doPost(HttpServletRequest request, HttpServletResponse
	  response) throws ServletException, IOException { doGet(request, response); }
	 
}