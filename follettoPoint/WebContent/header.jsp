<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link href="headerStyle.css" rel="stylesheet" type="text/css">

	</head>
	<body>
	
		<div class="topnav" id="myTopnav">
			  <a href="#home" class="active">Home</a>
			  <a href="ProductView.jsp">Catalogo</a>
			  <a href="CartView.jsp">Cart</a>
			  <a href="#contact">Chi siamo</a>
			  <a href="InsertView.jsp">Inserisci prodotto</a>
			  <a href="javascript:void(0);" class="icon" onclick="myFunction()">
			    <i class="fa fa-bars"></i>
			  </a>
		</div>

	
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
	
	</body>
</html>