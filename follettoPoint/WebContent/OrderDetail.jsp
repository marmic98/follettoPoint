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
	
	<title>FollettoPoint</title>
</head>



<body>

<style>
		body {
			font-family: Roboto;
			display: flex;
			justify-content: center;
			align-items: center;
			
			margin: auto;
			
		}

		div{
			width: 80%;
		}

		h2 {
			font-size: 24px;
			font-weight: bold;
			margin-bottom: 20px;
			text-align: right;
			margin-right: 70px;
		}
		
		table{
			width: 100%;
		}
	
		th, td {
			font-size: 25px;
			padding: 16px;
			text-align: left;
		}
		
		p{
			text-align: right;
			font-size: 25px;
			padding: 16px;
			
			font-weight: bold;
		}

	
	</style>

	
	<%  String importo = request.getParameter("importo");%>
	<%  String code = request.getParameter("id");%>
	
	<div>
	<img alt="logo" src="imgs/struct/logo.png">
	<h2><%="Fattura numero: " + code%></h2>
	<table border="0">
		<tr>
			<th>Nome </th>
			<th>Descrizione </th>
			<th>Quantità</th>
			<th>Categoria</th>
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
			}
		%>
		
	</table>
	<br>
	<p>Totale:<br><%=importo %></p>
</div>
	<br>
	
</body>
</html>