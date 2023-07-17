package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserModel;
import model.UserBean;

@WebServlet("/Register")
public class Register extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UserModel model = new UserModel();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String redir = "";
		
		if(action.equalsIgnoreCase("checkEmail")) {
			
	        try {
				if (model.checkEmail(request.getParameter("email"))) {
				    response.getWriter().write("exists");
				} else {
				    response.getWriter().write("not_exists");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
		
		else if(action.equalsIgnoreCase("register1")) {
			String email = request.getParameter("email");
			UserBean bean = new UserBean();
			
			try {
				bean = model.doRetrieveByKey(email);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (bean != null) {
				redir="/userNotNew.html";
			}
			else {
				request.getSession().removeAttribute("email");
				request.getSession().setAttribute("email", email);
				redir="/registerComplete.jsp";
			}
			response.sendRedirect(request.getContextPath() + redir);
		

		}else if(action.equalsIgnoreCase("register2")) {
			
			String nome = request.getParameter("nome");
			String cognome = request.getParameter("cognome");
			String email = (String) request.getSession().getAttribute("email");
			String password = request.getParameter("pwd");
			int tipo = Integer.parseInt(request.getParameter("tipo"));
			String numero = request.getParameter("numero");
			String indirizzo = request.getParameter("indirizzo");
			
	   

			UserBean bean = new UserBean();
			bean.setNome(nome);
			bean.setCognome(cognome);
			bean.setIndirizzo(indirizzo);
			bean.setNumero(numero);
			bean.setTipo(tipo);
			bean.setEmail(email);
			bean.setPwd(password);
			
			try {
				model.doSave(bean);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.getSession().removeAttribute("email");
			request.getSession().setAttribute("user", bean);
			
			redir = "/HomeView.jsp";
			response.sendRedirect(request.getContextPath() + redir);
		}
		
		

		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
