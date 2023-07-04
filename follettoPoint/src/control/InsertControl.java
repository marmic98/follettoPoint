package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.Part;

import model.*;
/**
 * Servlet implementation class ProductControl
 */
@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 100, // 1 MB
		  maxFileSize = 1024 * 1024 * 100,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
		)
public class InsertControl extends HttpServlet {
	private static final long serialVersionUID = 1L;


	
	static ProductModel model = new ProductModel();
	
	
	
	public InsertControl() {
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

		try {
			if (action != null) {
				if (action.equalsIgnoreCase("delete")) {
					int id = Integer.parseInt(request.getParameter("id"));
					model.doDelete(id);
				} else if (action.equalsIgnoreCase("insert")) {
					String name = request.getParameter("name");
					String description = request.getParameter("description");
					double price = Double.parseDouble(request.getParameter("price"));
					int quantity = Integer.parseInt(request.getParameter("quantity"));
					int categoria = Integer.parseInt(request.getParameter("categoria"));
					double sconto = Double.parseDouble(request.getParameter("sconto"));
					
			   

					ProductBean bean = new ProductBean();
					bean.setName(name);
					bean.setDescription(description);
					bean.setSconto(sconto);
					bean.setPrice(price);
					bean.setQuantity(quantity);
					bean.setCategoria(categoria);
					int id = model.doSave(bean);
					
					
					try {
						Part filePart = request.getPart("img");
					    
					    String path = "C:\\Users\\miche\\git\\follettoPoint\\follettoPoint\\WebContent\\imgs\\" + id +".png";
					    filePart.write(path);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}			
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		request.getSession().setAttribute("cart", cart);
		request.setAttribute("cart", cart);
		
		try {
			request.setAttribute("products", model.doRetrieveAll("id"));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		String sort = request.getParameter("sort");

		try {
			request.removeAttribute("products");
			request.setAttribute("products", model.doRetrieveAll(sort));
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
		
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProductView.jsp");
		dispatcher.forward(request, response);
		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}