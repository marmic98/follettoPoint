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
/**
 * Servlet implementation class ProductControl
 */
public class CartControl extends HttpServlet {
 private static final long serialVersionUID = 1L;

 
 ProductModel model = new ProductModel();
 
 public CartControl() {
  super();
 }

 protected void doGet(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {

  CartBean cart = (CartBean)request.getSession().getAttribute("cart");
  if(cart == null)
	  cart = new CartBean();
  List<ProductCartBean> products = cart.getProducts();
  
  String action = request.getParameter("action");
  String op = request.getParameter("op");
  
  int id = Integer.parseInt(request.getParameter("id"));

  try {
   if (action != null) {
    
    if (action.equalsIgnoreCase("deleteC")) {
     id = Integer.parseInt(request.getParameter("id"));
     cart.deleteProduct(model.doRetrieveByKey(id));
     
    }
    

   }  
   else if(op != null) {
	   System.out.println(op);
	   	if (op.compareTo("incr") == 0) {
	   		for(ProductCartBean p : products)
	   			if (p.getProduct().getCode() == id)
	   				if(p.getQuantityCart() < p.getProduct().getQuantity())
	   					p.setQuantityCart(p.getQuantityCart()+1);
	   	}
	   	else if (op.compareTo("-") == 0) { 
	   		for(ProductCartBean p : products)
	   			if (p.getProduct().getCode() == id)
	   				if(p.getQuantityCart() > 1)
	   					p.setQuantityCart(p.getQuantityCart()-1);
	   }
   }
  } catch (SQLException e) {
   System.out.println("Error:" + e.getMessage());
  }
  
  request.getSession().removeAttribute("cart");
  request.getSession().setAttribute("cart", cart);
  
  
  
  String sort = request.getParameter("sort");

  try {
   request.removeAttribute("products");
   request.setAttribute("products", model.doRetrieveAll(sort));
  } catch (SQLException e) {
   System.out.println("Error:" + e.getMessage());
  }

  RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/CartView.jsp");
  dispatcher.forward(request, response);
 }

 protected void doPost(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {
  doGet(request, response);
 }

}