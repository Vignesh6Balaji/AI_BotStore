package ai_bots.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import ai_bots.connection.Db_Con;
import ai_bots.dao.UserDao;
import ai_bots.model.Users;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("Login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out=response.getWriter()) {
			String email = request.getParameter("Login-email");
			String password = request.getParameter("Login-password");
			
			try {
				UserDao udao=new UserDao(Db_Con.getCon());
				Users users=udao.userLogin(email, password);
				if(users!=null) {
					request.getSession().setAttribute("auth", users);
					response.sendRedirect("Login.jsp");
				}
				else {
					out.print("User Login Failed");
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		} 
	}

}
