<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.*"%>
<%
// Check user credentials
	String header = "FollettoPoint";
	UserBean user =  (UserBean) request.getSession().getAttribute("user");
	if (user != null){
		 header = "Benvenuto " + user.getNome() +" "+ user.getCognome();
	}
%>

<!DOCTYPE html>
<html>
	
	<head>
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		<link rel=“icon” href=”imgs/struct/fav.jpg” type=“image/x-icon”>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link href="css/header.css" rel="stylesheet" type="text/css">

	</head>
	<body>
	
		<div class="topnav" id="myTopnav">
			  <a href="home" class="active"><%=header%></a>
			  <a href="ProductView.jsp">Catalogo</a>
			  <a href="CartView.jsp">Cart</a>
			  <a href="orders?sort=importo">Ordini</a>
			  <% 
			  	if(user != null && user.getTipo() != 0){
			  %>
			  	<a href="InsertView.jsp">Inserisci prodotto</a>
			  <%} %>
			  <a href="login-form.jsp" id="login-anchor">Login</a>
			  <a href="register.jsp">Register</a>
			  <a href="Logout">Logout</a>
			  
			  <div id="searchbar">
			  	
			  	<div id="contsearchbar">
			  		<input type="text" id="searchInput" placeholder="cerca qui..."></input>
			  		<p id="searchButton"> X</p>
			  	</div>
			  	
			  	<div id="suggerimenti"></div>
			  
			  </div>
			  
			  
			  <a href="javascript:void(0);" class="icon" onclick="myFunction()">
			    <i class="fa fa-bars"></i>
			  </a>
		</div>
	
		<script>
		$(document).ready(function() {
			  var searchBar = $("#searchInput");
			  var suggestions = $("#suggerimenti");
				
			  searchBar.on("input", function() {
			    var searchTerm = searchBar.val();
			    $("#suggerimenti").show();
			 

			    $.ajax({
			      url: "suggests", // URL del server per la gestione dei suggerimenti
			      method: "GET",
			      data: { 
			    	  term: searchTerm },
			      success: function(response) { 
			    	if(response == "null")
			    		$("#suggerimenti").hide();
			    	else{
			    		var resp = response;
			    		suggestions.html(response);
			    	}
			    		
			      }
			    });
			  });
			});

		</script>
	
		<script>
			function myFunction() {
			  var x = document.getElementById("myTopnav");
			  if (x.className === "topnav") {
			    x.className += " responsive";
			  } else {
			    x.className = "topnav";
			  }
			}
		</script>
		
		<script>
		$(document).ready(function() {
			  // Quando viene fatto clic sul div
			  $('#searchButton').click(function() {
			    // Resetta il contenuto del textarea
			    $('#searchInput').val('');
			    $('#suggerimenti').hide();
			  });
			});

		</script>
		

	
	</body>
</html>