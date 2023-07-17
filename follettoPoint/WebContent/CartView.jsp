<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.ProductBean,model.CartBean, model.ProductCartBean"%>
<head>
	<meta charset="ISO-8859-1">
	<link href="css/cart.css" rel="stylesheet" type="text/css">
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
		<p class="titleCat">Carrello</h2>
		<div class="cart">
		
			<% List<ProductCartBean> prodcart = cart.getProducts(); 	
			double totale = 0;   
			for(ProductCartBean beancart: prodcart) {
				   totale = totale +  (beancart.getProduct().getPrice() * beancart.getQuantityCart());
			%>
			
			
				<div class="cartItem">
					<img class="imgProd" src="imgs/<%=beancart.getProduct().getCode()%>.png" alt="Product Image">
					<a href="detail?action=read&id=<%=beancart.getProduct().getCode()%>">
						<p><%=beancart.getProduct().getName()%></p>
					</a> 
					 <p>€<%=beancart.getProduct().getPrice()%></p> 
					
						<form action="cart" method="post">
							<input type="hidden" name="action" value="aggiornaQuantita">
		        			<input type="hidden" name="id" value="<%= beancart.getProduct().getCode() %>">
		        
		        			<button type="submit" name="operazione" value="decrementa">-</button>
		        			<p><%=beancart.getQuantityCart()%></p>
		        			<button type="submit" name="operazione" value="incrementa">+</button>
		    			</form>
					
					<p>€<%=beancart.getProduct().getPrice() * beancart.getQuantityCart() %></p>	
					
					
					
					<a class="adminFunc" href="cart?action=deleteC&id=<%=beancart.getProduct().getCode()%>">Rimuovi</a>
				</div>
			
			<%} %>
	</div>
	
	<% if(totale > 0) %>
		<p class="adminFunc">Totale <%= totale %></p>
	
	
	
	<a class="adminFunc" href="orders?action=insert&source=cart&totale=<%=totale%>">Concludi ordine</a>		
	<% 
	}else{ %>
		<h2 style="text-align: center; padding: 13% 0%;">Carrello vuoto</h2> 
	<% } %>
	
	<%@ include file="footer.jsp"%>
	
	
</body>
</html>