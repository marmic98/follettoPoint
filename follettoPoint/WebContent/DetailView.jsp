<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	ProductBean product = (ProductBean) request.getAttribute("product");
	if (product == null)
		response.sendRedirect("/ProductView.jsp");
	
	CartBean cart = (CartBean) request.getAttribute("cart");
	
	
%>

<!DOCTYPE html>
<html>

	<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.ProductBean,model.CartBean"%>

<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="ProductStyle.css" rel="stylesheet" type="text/css">
	<title><%=product.getName()%></title>
</head>

<body>
	<%@ include file="header.jsp"%>
	
	<table border="1">
		<tr>
			<th>Code</th>
			<th>Name</th>
			<th>Description</th>
			<th>Price</th>
			<th>Category</th>
			<th>Gallery</th>
		</tr>
		<tr>
			<td><%=product.getCode()%></td>
			<td><%=product.getName()%></td>
			<td><%=product.getDescription()%></td>
			<td><%=product.getPrice()%></td>
			<td><%=product.getCategoria()%></td>
			<td><img src="imgs/<%=product.getCode()%>.png"></td>
		</tr>
	</table>
	
	
		<form action="detail?q=" method="get">
			<input type="hidden" name="action" value="addC">
			<input type="hidden" name="id" value="<%=product.getCode()%>"> 
			<select name="q">
				<%
					int i;
					for(i = 1; i <= product.getQuantity(); i++){
				%>
       			<option value='<%= i %>'><%= i %></option>
      			
      			<%} %>
   			</select>
   			<br>
   			<button type="submit">Aggiungi al Carrello</button>
  			</form>
	
	<br>
	
</body>
</html>