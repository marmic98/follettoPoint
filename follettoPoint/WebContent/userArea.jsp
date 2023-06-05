<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
    
<%    
// Check user credentials
	UserBean usr = (UserBean) request.getSession().getAttribute("user");
	String utente = "";
	if ( usr == null){
		 response.sendRedirect("unauthorized.html");
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
		<table border="1">
		<tr>
			<th>id <a href="orders?sort=id">Sort</a></th>
			<th>email <a href="orders?sort=nome">Sort</a></th>
			<th>stato <a href="orders?sort=descrizione">Sort</a></th>
			<th>data</th>
			<th>importo</th>
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
			<td><%=bean.getId()%></td>
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