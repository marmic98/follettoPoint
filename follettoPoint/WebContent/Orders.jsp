<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
    
<%    
// Check user credentials
	
	UserBean usr = (UserBean) request.getSession().getAttribute("user");
	String utente = "";
	if (usr == null){
		response.sendRedirect("login-form.jsp");
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
		<link href="css/product.css" rel="stylesheet" type="text/css">
		<title>Ordini | <%=utente%></title>
	</head>
	<body>
		<%@ include file="header.jsp"%>
		<h2>Benvenuto <%=utente%></h2>
		<a href="Logout">Logout</a> 
		<% 
		if(user != null && user.getTipo() != 0){
	  	%>
	    <a href="InsertView.jsp">Inserisci prodotto</a>	
	    <%} 
	    %>
		<table border="1">
		<tr>
			<th>id <a href="orders?sort=id">Sort</a></th>
			<th>email <a href="orders?sort=email">Sort</a></th>
			<th>stato</th>
			<th>data<a href="orders?sort=data"></a></th>
			<th>importo<a href="orders?sort=importo"></a></th>
			<th>carta</th>
			<th>dataSpedizione</th>
		</tr>
		<%
			if (orders != null && orders.size() != 0) {
				Iterator<?> it = orders.iterator();
				while (it.hasNext()) {
					OrderBean bean = (OrderBean) it.next();
		%>
		<tr>
			
			<td><a href="orderdetail?id=<%=bean.getId()%>"><%=bean.getId()%></a></td>
			<td><%=bean.getEmail()%></td>
			<td><%=bean.getStato()%></td>
			<td><%=bean.getData()%></td>
			<td><%=bean.getImporto()%></td>
			<td><%=bean.getCarta()%></td>
			<td><%=bean.getDataSpedizione()%></td>	
		</tr>
		<%
				}
			} else {
		%>
		<tr>
			<td colspan="7">Nessun Ordine effettuato</td>
		</tr>
		<%
			}
		%>
	</table>
	<%@ include file="footer.jsp"%>
	</body>
</html>