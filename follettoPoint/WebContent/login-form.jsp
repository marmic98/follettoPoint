<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
// Check user credentials
	
	if (request.getSession().getAttribute("user") != null){
		 response.sendRedirect("ProductView.jsp");
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login form</title>
</head>
<body>

<form action="Login" method="post"> 
<fieldset>
     <legend>Login Custom</legend>
     <label for="username">Login</label>
     <input id="username" type="text" name="username" placeholder="enter login"> 
     <br>   
     <label for="password">Password</label>
     <input id="password" type="password" name="password" placeholder="enter password"> 
     <br>
     <input type="submit" value="Login"/>
     <input type="reset" value="Reset"/>
</fieldset>
</form> 

</body>
</html>
