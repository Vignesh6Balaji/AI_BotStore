package ai_bots.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import ai_bots.model.Cart;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out=response.getWriter()){
			ArrayList<Cart> cartlist=new ArrayList<Cart>();
			int id=Integer.parseInt(request.getParameter("id"));
			Cart cm=new Cart();
			cm.setId(id);
			cm.setQuantity(1);
			
			HttpSession session=request.getSession();
			ArrayList<Cart> cart_list= (ArrayList<Cart>) session.getAttribute("cart-list");
			
			if(cart_list==null) {
				cartlist.add(cm);
				session.setAttribute("cart-list",cartlist);
				response.sendRedirect("Index.jsp");
			}
			else {
				cartlist = cart_list;
				boolean exist=false;
				for(Cart c:cart_list) {
					if(c.getId()==id) {
						exist=true;
						response.sendRedirect("Cart.jsp");
					}
				}
				if(!exist) {
					cartlist.add(cm);
					response.sendRedirect("Index.jsp");
				}
			}
		}
	}
}
