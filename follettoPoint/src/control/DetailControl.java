package control;

import java.io.IOException; 
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;
/**
 * Servlet implementation class ProductControl
 */
public class DetailControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	CartModel model = new CartModel();
	
	public DetailControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CartBean cart = (CartBean)request.getSession().getAttribute("cart");
		if(cart == null) {
			cart = new CartBean();
			request.getSession().setAttribute("cart", cart);
		}
		
		
		String action = request.getParameter("action");
		
		int id = Integer.parseInt(request.getParameter("id"));
		ProductBean p = new ProductBean();
		try {
			p = model.doRetrieveByKey(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (action != null) {
			if (action.equalsIgnoreCase("addC")) {
				cart.addProduct(p);
			}
			else if (action.equalsIgnoreCase("read")) {						
				request.setAttribute("product", p);
			}
		}

		request.getSession().setAttribute("cart", cart);
		request.setAttribute("cart", cart);
		
		request.getSession().setAttribute("product", p);
		request.setAttribute("product", p);
		

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/DetailView.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}