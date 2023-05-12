package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserBean;
import model.UserModel;

@WebServlet("/Login")
public class Login extends HttpServlet {
	
	UserModel model = new UserModel();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			String redirectedPage;
			try {
				UserBean user = checkLogin(username, password);
				request.getSession().setAttribute("user", user);
				if (user.getTipo() == 1)
					request.getSession().setAttribute("adminRoles", new Boolean(true));
					redirectedPage = "/ProductView.jsp";
			} catch (Exception e) {
				request.getSession().setAttribute("adminRoles", new Boolean(false));
				redirectedPage = "/loginerror.html";
			}
			response.sendRedirect(request.getContextPath() + redirectedPage);
		}
	}

	private UserBean checkLogin(String email, String password) throws Exception {
		UserBean usr = model.doRetrieveByKey(email);
		
		if(usr != null) {
		
			if (usr.getEmail().equals(email) && usr.getPwd().equals(password)) {
				
			} else
				throw new Exception("Inserire di nuovo email e password");
			
		}else {
			throw new Exception("Utente inesistente");
			
		}
		return usr;
	}
	
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	

}
