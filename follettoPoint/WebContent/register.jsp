<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



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
		<h2>Insert</h2>
		<form action="Register" method="post">
			<input type="hidden" name="action" value="register1">
	
			<label for="email">Email</label><br> 
			<input name="email" type="email" required><br>
	
			<br>
			
			<input type="submit" value="Registrati"><input type="reset">
			<br>
		</form>	
		<%@ include file="footer.jsp"%>
	</body>
</html>