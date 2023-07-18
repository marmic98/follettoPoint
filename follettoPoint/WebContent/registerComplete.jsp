<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html lang="it">
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
			 <input type="hidden" name="action" value="register2">
			 
			<label for="nome">Nome</label><br>
			<input name="nome" type="text" required><br>

			
			<label for="cognome">Cognome</label><br>
			<input name="cognome" type="text" required><br>
			
			<label for="indirizzo">Indirizzo</label><br>
			<input name="indirizzo" type="text" required><br>
			
			<label for="numero">Telefono</label><br>
			<input name="numero" type="text" required><br>
			
			<input type="hidden" name="tipo" value="0">
			
			<label for="pwd">Password</label><br> 
			<input name="pwd" type="password" required><br>
		
			<br>
			<input type="submit" value="Registrati"><input type="reset">
			<br>
		</form>	
		<%@ include file="footer.jsp"%>
	</body>
</html>