package ai_bots.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import ai_bots.connection.Db_Con;
import ai_bots.dao.UserDao;
import ai_bots.model.Users;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("Reg-name");
		String email=request.getParameter("Reg-email");
		String password=request.getParameter("Reg-password");
		
		Users u=new Users();
		u.setName(name);
		u.setEmail(email);
		u.setPassword(password);
		
		UserDao udao;
		try {
			udao = new UserDao(Db_Con.getCon());
			boolean f=udao.userReg(u);
			if(f) {
				HttpSession session=request.getSession();
				session.setAttribute("reg-mes", "Registration Successfull");
				response.sendRedirect("Registration.jsp");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
