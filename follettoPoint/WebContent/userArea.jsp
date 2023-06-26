<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
    
<%    
// Check user credentials
	UserBean usr = (UserBean) request.getSession().getAttribute("user");
	String utente = "";
	if (usr == null){
		 response.sendRedirect(request.getContextPath()+"/unauthorized.html");
	}else
		utente = usr.getNome() +" " + usr.getCognome();
%>

<%
	Collection<?> orders = (Collection<?>) request.getAttribute("orders");
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.*"%>
	<head>
		<meta charset="ISO-8859-1">
		<link href="ProductStyle.css" rel="stylesheet" type="text/css">
		<title>Ordini | <%=utente%></title>
	</head>
	<body>
	<%@ include file="header.jsp"%>
		<div class="lateral-menu">
		</div>	
	<%@ include file="footer.jsp"%>
	</body>
</html>