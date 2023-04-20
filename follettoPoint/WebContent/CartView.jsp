<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.ProductBean,model.CartBean"%>
<head>
<meta charset="ISO-8859-1">
<title>Cart</title>
</head>
<body>
	<% 
	CartBean cart = (CartBean) request.getSession().getAttribute("cart");
		if(cart.getProducts().size() != 0) { %>
		<h2>Cart</h2>
		<table border="1">
		<tr>
			<th>Name</th>
			<th>Quantity</th>
			<th>Action</th>
		</tr>
		<% List<ProductBean> prodcart = cart.getProducts(); 	
		   for(ProductBean beancart: prodcart) {
		%>
		<tr>
			<td><%=beancart.getName()%></td>
			<td><%=beancart.getQuantity()%></td>
			<td><a href="cart?action=deleteC&id=<%=beancart.getCode()%>">Delete from cart</a></td>
		</tr>
		<%} %>
	</table>		
	<% }else{ %>
		<h2>Carrello vuoto</h2> 
	<% } %>
	<a href="ProductView.jsp">Torna al catalogo</a>
	
</body>
</html>