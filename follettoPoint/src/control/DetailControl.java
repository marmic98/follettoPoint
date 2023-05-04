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

	
	ProductModelDS model = new ProductModelDS();
	
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
		
		
		if (action != null) {
			if (action.equalsIgnoreCase("addC")) {
				int q = Integer.parseInt(request.getParameter("q"));
				int id = Integer.parseInt(request.getParameter("id"));
				ProductBean product = new ProductBean();
				try {
					
					product = model.doRetrieveByKey(id);
					cart.addProduct(product, q);
					request.removeAttribute("product");
					request.setAttribute("product", product);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			else if (action.equalsIgnoreCase("read")) {						
				
				int id = Integer.parseInt(request.getParameter("id"));
				ProductBean product = new ProductBean();
				try {
					
					product = model.doRetrieveByKey(id);
					
					request.removeAttribute("product");
					request.setAttribute("product", product);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}

		

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/DetailView.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}