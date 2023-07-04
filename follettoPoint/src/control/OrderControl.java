package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;


public class OrderControl extends HttpServlet {
	private static final long serialVersionUID = 1L;


	static OrderModel model = new OrderModel();
	

	
	public OrderControl() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		
		UserBean utente = ((UserBean) request.getSession().getAttribute("user"));
		
		
		if(utente != null) {
			String action = request.getParameter("action");
			try {
				if (action != null) {
					if (action.equalsIgnoreCase("delete")) {
						int id = Integer.parseInt(request.getParameter("id"));
						model.doDelete(id);
					}
					else if (action.equalsIgnoreCase("insert")) {
						
						CartBean cart = (CartBean) request.getSession().getAttribute("cart");
						
						
						String email = (utente.getEmail());
						double importo = Double.parseDouble(request.getParameter("totale"));
						String carta= "test";
						
				   
	
						OrderBean bean = new OrderBean();
						bean.setEmail(email);
						bean.setImporto(importo);
						bean.setCarta(carta);
						bean.setStato(0);
												
						
						
						int idOrder = model.doSave(bean);
						
						
						 
						List <ProductCartBean> prods = cart.getProducts();
						
						model.doSaveContiene(prods, idOrder);
						
						
						request.getSession().removeAttribute("cart");
						
						
					}
					
				}
			} catch (SQLException e) {
				System.out.println("Error:" + e.getMessage());
			}
		
			
			String sort = request.getParameter("sort");
			
			if(sort != null) {
				try {
					request.removeAttribute("orders");
					request.setAttribute("orders", model.doRetrieveAll(sort, utente.getEmail()));
				} catch (SQLException e) {
					System.out.println("Error:" + e.getMessage());
				}
			}else {
				try {
					request.removeAttribute("orders");
					request.setAttribute("orders", model.doRetrieveAll("id", utente.getEmail()));
				} catch (SQLException e) {
					System.out.println("Error:" + e.getMessage());
				}
			}
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Orders.jsp");
		dispatcher.forward(request, response);
		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}