<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	ProductBean product = (ProductBean) request.getAttribute("product");
	
	CartBean cart = (CartBean) request.getAttribute("cart");
	
	
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.ProductBean,model.CartBean"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="ProductStyle.css" rel="stylesheet" type="text/css">
	<title><%product.getName();%></title>
</head>

<body>

	<%
	if (product != null) {
	%>
		<table border="1">
			<tr>
				<th>Code</th>
				<th>Name</th>
				<th>Description</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Category</th>
				<th></th>
			</tr>
			<tr>
				<td><%=product.getCode()%></td>
				<td><%=product.getName()%></td>
				<td><%=product.getDescription()%></td>
				<td><%=product.getPrice()%></td>
				<td><%=product.getQuantity()%></td>
				<td><%=product.getCategoria()%></td>
				<td><a href="detail?action=addC&id=<%=product.getCode()%>">Aggiungi al carrello</a></td>
			</tr>
		</table>
	<%
		}
	%>
	
	
	<br>
	<a href="CartView.jsp">Cart</a>
	<a href="ProductView.jsp">Catalogo</a>
</body>
</html>