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

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import model.*;
/**
 * Servlet implementation class ProductControl
 */
@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 100, // 1 MB
		  maxFileSize = 1024 * 1024 * 100,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
		)
public class HomeControl extends HttpServlet {
	private static final long serialVersionUID = 1L;


	static HomeModel model = new HomeModel();
	

	
	public HomeControl() {
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
				if (action.equalsIgnoreCase("addC")) {
					int id = Integer.parseInt(request.getParameter("id"));
					cart.addProduct(model.doRetrieveByKey(id), 1);
				} else if (action.equalsIgnoreCase("deleteC")) {
					int id = Integer.parseInt(request.getParameter("id"));
					cart.deleteProduct(model.doRetrieveByKey(id));
				}
				else if (action.equalsIgnoreCase("read")) {
					int id = Integer.parseInt(request.getParameter("id"));
					request.removeAttribute("product");
					request.setAttribute("product", model.doRetrieveByKey(id));
				} else if (action.equalsIgnoreCase("delete")) {
					int id = Integer.parseInt(request.getParameter("id"));
					model.doDelete(id);
				} else if (action.equalsIgnoreCase("insert")) {
					String name = request.getParameter("name");
					String description = request.getParameter("description");
					int price = Integer.parseInt(request.getParameter("price"));
					int quantity = Integer.parseInt(request.getParameter("quantity"));
					int categoria = Integer.parseInt(request.getParameter("categoria"));
					
			   

					ProductBean bean = new ProductBean();
					bean.setName(name);
					bean.setDescription(description);
					bean.setPrice(price);
					bean.setQuantity(quantity);
					bean.setCategoria(categoria);
					int id = model.doSave(bean);
					
					
					try {
						
					    Part filePart = request.getPart("img");
					    System.out.println(System.getProperty("user.dir"));
					    String path = System.getProperty("user.dir") + "/follettoPoint/WebContent/imgs" + id +".png";
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
		
		
		String sort = request.getParameter("sort");
		try {
			if(sort != null && sort.equals("nome")) {
				request.removeAttribute("products");
				request.setAttribute("products", model.doRetrieveAll("nome"));
				request.removeAttribute("products2");
				request.setAttribute("products2", model.doRetrieveAll("sconto"));
			}
			else if(sort != null && sort.equals("prezzo")) {
				request.removeAttribute("products");
				request.setAttribute("products", model.doRetrieveAll("id"));
				request.removeAttribute("products2");
				request.setAttribute("products2", model.doRetrieveAll("prezzo"));
			}
			else {
			request.removeAttribute("products2");
			request.setAttribute("products2", model.doRetrieveAll("sconto"));
			request.removeAttribute("products");
			request.setAttribute("products", model.doRetrieveAll("id"));
			}
			
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
		
		
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/HomeView.jsp");
		dispatcher.forward(request, response);
		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}