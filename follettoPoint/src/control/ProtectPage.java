package control;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Base64;

//import sun.misc.BASE64Decoder;


@WebServlet(name = "/ProtectPage", urlPatterns = { "/protect" }, initParams = {
		@WebInitParam(name = "passwordFile", value = "passwords.properties") })
public class ProtectPage extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Properties passwords;
	private String passwordFile;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			passwordFile = config.getInitParameter("passwordFile");

			generatePasswords();

			passwords = new Properties();
			passwords.load(new FileInputStream(passwordFile));
		} catch (IOException ioe) {
		}
	}

	public ProtectPage() {
		super();
	}	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				System.out.println("isSecure: " + request.isSecure());
		System.out.println("getLocalPort: " + request.getLocalPort());
		System.out.println("getServerPort: " + request.getServerPort());
		System.out.println("getRequestURL: "+ request.getRequestURL());
		System.out.println("getRequestURI: "+ request.getRequestURI());
		System.out.println("getQueryString: "+ request.getQueryString());
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String authorization = request.getHeader("Authorization");
		if (authorization == null) {
			askForPassword(response);
		} else {
			String userInfo = authorization.substring(6).trim();
			String nameAndPassword = new String(Base64.getDecoder().decode(userInfo));
			int index = nameAndPassword.indexOf(":");
			String user = nameAndPassword.substring(0, index);
			String password = nameAndPassword.substring(index + 1);
			String realPassword = passwords.getProperty(user);
			if ((realPassword != null) && (realPassword.equals(password))) {
				out.println("<html><body>"
				+ "<h1>Welcome to the Protected Page</h1>"
				+ "Congratulations. You have accessed a protected document.\n"
				+ "</body></html>");
			} else {
				askForPassword(response);
			}
		}	}

	// If no Authorization header was supplied in the request.
	private void askForPassword(HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setHeader("WWW-Authenticate", "BASIC realm=\"privileged-few\"");
	}

	private void generatePasswords() {
		Properties passwords = new Properties();
		passwords.put("root", "admin");
		passwords.put("tiger", "tiger");
		try {
			FileOutputStream out = new FileOutputStream(passwordFile);
			passwords.store(out, "Passwords");
		} catch (FileNotFoundException e) {
		} catch (IOException ie) {
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}	
}
