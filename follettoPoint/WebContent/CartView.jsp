<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.ProductBean,model.CartBean, model.ProductCartBean"%>
<head>
	<meta charset="ISO-8859-1">
	<title>Cart</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<% 
		CartBean cart = (CartBean) request.getSession().getAttribute("cart");
	
		if (cart == null)
			cart = new CartBean();
		
		if(cart.getProducts().size() != 0) {
	%>
		<h2>Cart</h2>
		<table border="1">
		<tr>
			
		</tr>
		<% List<ProductCartBean> prodcart = cart.getProducts(); 	
		double totale = 0;   
		for(ProductCartBean beancart: prodcart) {
			   totale = totale +  (beancart.getProduct().getPrice() * beancart.getQuantityCart());
		%>
		<tr>
			<td><%=beancart.getProduct().getName()%></td>
			<td></td>
			<td>
				
				<form action="cart" method="post">
					<input type="hidden" name="action" value="aggiornaQuantita">
        			<input type="hidden" name="id" value="<%= beancart.getProduct().getCode() %>">
        
        			<button type="submit" name="operazione" value="decrementa">-</button>
        			<p><%=beancart.getQuantityCart()%></p>
        			<button type="submit" name="operazione" value="incrementa">+</button>
    </form>

			
			</td>
			<td><a href="cart?action=deleteC&id=<%=beancart.getProduct().getCode()%>">Delete from cart</a></td>
		</tr>
		<%} %>
	</table>
	<br>
	<% if(totale > 0) %>
		<p>Totale: <%= totale %></p>
	<br>
	
	
	<a href="orders?action=insert&totale=<%=totale%>">Concludi ordine</a>		
	<% }else{ %>
		<h2>Carrello vuoto</h2> 
	<% } %>
	<a href="ProductView.jsp">Torna al catalogo</a>
	
	
	
</body>
</html>