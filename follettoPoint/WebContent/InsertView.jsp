<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
// Check user credentials
	UserBean usr = (UserBean) request.getSession().getAttribute("user");
	
	if ( usr == null || usr.getTipo() != 1){
		 response.sendRedirect("unauthorized.html");
	}
%>

<!DOCTYPE html>
<html>
	<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.ProductBean,model.CartBean"%>
	
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="css/product.css" rel="stylesheet" type="text/css">
		<title>FollettoPoint</title>
	</head>
	
	<body>
		<%@ include file="header.jsp"%>
		<h2>Insert</h2>
		<form action="insert" method="post" enctype='multipart/form-data'>
			<input type="hidden" name="action" value="insert"> 
			
			<label for="name">Name:</label><br> 
			<input name="name" type="text" maxlength="20" required placeholder="enter name"><br> 
			
			<label for="description">Description:</label><br>
			<textarea name="description" maxlength="100" rows="3" required placeholder="enter description"></textarea><br>
			
			<label for="price">Price:</label><br> 
			<input name="price" type="number" min="0" value="0" required><br>
			
			<label for="sconto">Sconto:</label><br> 
			<input name="sconto" type="number" min="0" value="0" required><br>
	
			<label for="quantity">Quantity:</label><br> 
			<input name="quantity" type="number" min="1" value="1" required><br>
		
			<label for="categoria">Categoria:</label><br> 
			<select name="categoria">
	        	<option value='1'>Elettrodomestici</option>
	        	<option value='2'>Folletto</option>
	        	<option value='3'>Caffè</option>
	    	</select> <br>
	    	
	    	<input type="file" name="img" required accept="image/png"> <br>
			
			<input type="submit" value="Add"><input type="reset" value="Reset">
	
		</form>	
		<br>
		<%@ include file="footer.jsp"%>
	</body>
</html>