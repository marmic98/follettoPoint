package control;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.MultipartConfigElement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.Part;

import model.*;

@WebServlet("/orders")
public class OrderControl extends HttpServlet {
	private static final long serialVersionUID = 1L;


	static OrderModel model = new OrderModel();
	

	
	public OrderControl() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		UserBean utente = ((UserBean) request.getSession().getAttribute("user"));
		System.out.println(utente);

		try {
			if (action != null) {
				if (action.equalsIgnoreCase("delete")) {
					int id = Integer.parseInt(request.getParameter("id"));
					model.doDelete(id);
				}
				else if (action.equalsIgnoreCase("insert")) {
					String email = (utente.getEmail());
					double importo = Double.parseDouble(request.getParameter("importo"));
					String carta= request.getParameter("carta");
					
			   

					OrderBean bean = new OrderBean();
					bean.setEmail(email);
					bean.setImporto(importo);
					bean.setCarta(carta);
					bean.setStato(0);
					
					/*bean.setData(0);
					 * bean.setDataSpedizione(0);
					 */
					request.getSession().removeAttribute("cart");
					int id = model.doSave(bean);
					
				}
			}			
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		
		
		
		
		String sort = request.getParameter("sort");

		try {
			request.removeAttribute("orders");
			
			request.setAttribute("orders", model.doRetrieveAll(sort, utente.getEmail()));
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
		
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/userArea.jsp");
		dispatcher.forward(request, response);
		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}