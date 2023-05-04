<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Collection<?> products = (Collection<?>) request.getAttribute("products");
	if(products == null) {
		response.sendRedirect("./product");	
		return;
	}
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.ProductBean,model.CartBean"%>

<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="ProductStyle.css" rel="stylesheet" type="text/css">
	<title>FollettoPoint</title>
</head>

<body>
	<%@ include file="header.jsp"%>
	<h2>Products</h2>
	<table border="1">
		<tr>
			<th>Code <a href="product?sort=id">Sort</a></th>
			<th>Name <a href="product?sort=nome">Sort</a></th>
			<th>Description <a href="product?sort=descrizione">Sort</a></th>
			<th>Category</th>
			<th>Action</th>
		</tr>
		<%
			if (products != null && products.size() != 0) {
				Iterator<?> it = products.iterator();
				while (it.hasNext()) {
					ProductBean bean = (ProductBean) it.next();
		%>
		<tr>
			<td><%=bean.getCode()%></td>
			<td><%=bean.getName()%></td>
			<td><%=bean.getDescription()%></td>
			<td><%=bean.getCategoria()%></td>
			
			<td><a href="product?action=delete&id=<%=bean.getCode()%>">Delete</a><br>
				<a href="detail?action=read&id=<%=bean.getCode()%>">Details</a><br>
				<a href="product?action=addC&id=<%=bean.getCode()%>">Add to cart</a>
			</td>
		</tr>
		<%
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
	
	
	<%@ include file="footer.jsp"%>
</body>
</html>