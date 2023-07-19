<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%    
// Check user credentials
	
	UserBean user = (UserBean) request.getSession().getAttribute("user");
	String utente = "";
	
	if (user == null){
		response.sendRedirect("login-form.jsp");
	}else
		utente = user.getNome() +" " + user.getCognome();
%>

<%
	
	Collection<?> products = (Collection<?>) request.getAttribute("products");
if(products == null) {
	response.sendRedirect("./orderdetail");	
	return;
}
%>



<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.*"%>

<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="ProductStyle.css" rel="stylesheet" type="text/css">
	<title>FollettoPoint</title>
</head>

<body>
	
	<%  String importo = request.getParameter("importo");%>
	
	<h2>Prodotti Ordinati</h2>
	<table border="0">
		<tr>
			<th>Code </th>
			<th>Name </th>
			<th>Description </th>
			<th>Quantita</th>
			<th>Category</th>
			<th>Prezzo prodotti</th>
			<th>Iva</th>
			
			
		</tr>
		<%
			if (products != null && products.size() != 0) {
				Iterator<?> it = products.iterator();
				while (it.hasNext()) {
					ProductBean bean = (ProductBean) it.next();
					if(user == null || user.getTipo() != 1){
						
		%>
		<tr>
			<td><%=bean.getCode()%></td>
			<td><%=bean.getName()%></td>
			<td><%=bean.getDescription()%></td>
			<td><%=bean.getQuantity()%></td>
			<td><%=bean.getCategoria()%></td>
			<td><%=bean.getPrice()%></td>
			<td><%=bean.getIva()%></td>
		</tr>
		<%
						
					}
					else{
						%>
						<tr>
			<td><%=bean.getCode()%></td>
			<td><%=bean.getName()%></td>
			<td><%=bean.getDescription()%></td>
			<td><%=bean.getQuantity()%></td>
			<td><%=bean.getCategoria()%></td>
			<td><%=bean.getPrice()%></td>
			<td><%=bean.getIva()%></td>
			
			
		</tr>
						<%
					}
				}
			} else {
		%>
		<tr>
			<td colspan="6">No products available</td>
		</tr>
		<%
			}
		%>
	</table>
	<br>
	<table border=0>
	<tr>
	<th>Totale</th>
	</tr>
	<tr>
	<td><%out.println(importo); %></td>
	</tr>
	</table>

	<br>
	
	

	
	
	
</body>
</html>