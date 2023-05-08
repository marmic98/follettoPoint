<%
// Check user credentials
Boolean adminRoles = (Boolean) session.getAttribute("adminRoles");
if ((adminRoles == null) || (!adminRoles.booleanValue()))
{	
    response.sendRedirect("./login-form.jsp");
    return;
}
%>

<%
 Locale locale = request.getLocale();
 String language = locale.getLanguage();
 String country = locale.getCountry();
%>

<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Protected Page</title>
</head>
<body>
<h1>Welcome to the Protected Page</h1>
Congratulations. You have accessed a protected document.
<br><br>
<%= language %><br>
<%= country%><br>

<form action="Logout" method="get" > 
     <input type="submit" value="Logout"/>
</form> 
</body>
</html>

