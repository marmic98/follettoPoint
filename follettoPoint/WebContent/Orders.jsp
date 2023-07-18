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
<html lang="it">
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.*"%>
	<head>
		<meta charset="ISO-8859-1">
		<link href="css/orders.css" rel="stylesheet" type="text/css">
		<title>Ordini | <%=utente%></title>
	</head>
	<body>
		<%@ include file="header.jsp"%>
		<% 
		if(user != null && user.getTipo() != 0){
	  	%>
		<p class="titleCat">ORDINI</p>
		<%}else{ %>
		<p class="titleCat">I MIEI ORDINI</p>
		<%} %>
		<h2 style="text-align: center;">Benvenuto <%=utente%></h2>
		<a class="logout" href="Logout">Logout</a>
		<% 
		if(user != null && user.getTipo() != 0){
	  	%>
	    <a class="adminFunc" href="InsertView.jsp">Inserisci prodotto</a>	
	    <%} 
	    %>
	    <!-- descrizione rovina lo stile -->
	    
		<table class="orders">
		<tr>
			
			<th><a href="orders?sort=email">EMAIL</a></th>
			<th>Stato</th>
			<th><a href="orders?sort=data">DATA</a></th>
			<th>Totale<a href="orders?sort=importo"></a></th>
			<th>Metodo di pagamento</th>
			<th>Arrivo Previsto il</th>
			<th>Indirizzo</th>
			<th></th>
		</tr>
		<%
			if (orders != null && orders.size() != 0) {
				Iterator<?> it = orders.iterator();
				while (it.hasNext()) {
					OrderBean bean = (OrderBean) it.next();
		%>
		
			<tr>
				
				<td><%=bean.getEmail()%></td>
				<%if(bean.getStato() == 0){ %>
					<td>In preparazione</td>
				<%}else if(bean.getStato() == 1){ %>
					<td>In arrivo</td>
				<%}else if(bean.getStato() == 2){ %>
					<td>Spedito</td>
				<%} %>
				<td><%=bean.getData()%></td>
				<td><%=bean.getImporto()%></td>
				<td><%=bean.getCarta()%></td>
				<td><%=bean.getDataSpedizione()%></td>
				<td><%=bean.getAddress()%></td>
				<td><a href="orderdetail?id=<%=bean.getId()%>">Vedi Fattura</a></td>	
				
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