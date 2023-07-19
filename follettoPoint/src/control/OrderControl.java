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
		
		request.getSession().setAttribute("source", "cart"); 
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
						
						
				   
	
						OrderBean bean = new OrderBean();
						bean.setEmail(email);
						bean.setImporto(importo);
						bean.setCarta(utente.getMetodo());
						bean.setStato(0);
						bean.setAddress(utente.getIndirizzo());
						
												
						
						System.out.println(utente.getMetodo());
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
			String dataInizio = request.getParameter("dataInizio");
	        String dataFine = request.getParameter("dataFine");
			
			if(utente != null && utente.getTipo() == 1){
			if(sort == null) {
				try {
					request.removeAttribute("orders");
					request.setAttribute("orders", model.doRetrieveAllSU("id"));
				} catch (SQLException e) {
					System.out.println("Error:" + e.getMessage());
				}
			}else if(sort.equals("data")){
				request.removeAttribute("orders");
				try {
					request.setAttribute("orders", model.doRetrieveAllSUData(dataInizio,dataFine));
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
			else {
				try {
					request.removeAttribute("orders");
					request.setAttribute("orders", model.doRetrieveAllSU(sort));
				} catch (SQLException e) {
					System.out.println("Error:" + e.getMessage());
				}
			}
		}
			else {
				if(sort == null) {
					try {
						request.removeAttribute("orders");
						request.setAttribute("orders", model.doRetrieveAll("id", utente.getEmail()));
					} catch (SQLException e) {
						System.out.println("Error:" + e.getMessage());
					}
				}else if(sort.equals("data")){
					try {
						request.removeAttribute("orders");
						request.setAttribute("orders", model.doRetrieveAllData(dataInizio,dataFine, utente.getEmail()));
					} catch (SQLException e) {
						System.out.println("Error:" + e.getMessage());
					}
					
					
				}
				else {
					try {
						request.removeAttribute("orders");
						request.setAttribute("orders", model.doRetrieveAll(sort, utente.getEmail()));
					} catch (SQLException e) {
						System.out.println("Error:" + e.getMessage());
					}
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