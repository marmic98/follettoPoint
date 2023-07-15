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
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.*"%>

<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="css/product.css" rel="stylesheet" type="text/css">
	<title>FollettoPoint</title>
</head>

<body>
	<%@ include file="header.jsp"%>
	<h2>Catalogo</h2>
	
		
			Code <a href="product?sort=id">Sort</a>
			Name <a href="product?sort=nome">Sort</a>
			Description <a href="product?sort=descrizione">Sort</a>
			<br>
		
		<%
			if (products != null && products.size() != 0) {
				Iterator<?> it = products.iterator();
				while (it.hasNext()) {
					ProductBean bean = (ProductBean) it.next();
					if(user == null || user.getTipo() != 1){
						if(bean.getQuantity() > 0){
		%>
		<div class="product">
	        	<a href="detail?action=read&id=<%=bean.getCode()%>">
	            	<img class="imgProd" src="imgs/<%=bean.getCode()%>.png" alt="Product Image">
	            </a>
	            <a class="nameProd" href="detail?action=read&id=<%=bean.getCode()%>">
	            	<p class="nameProd"><%=bean.getName()%></p>
	            </a>
	            
	             
	             <p class="descrProd"><%=bean.getDescription()%></p>
	             <p>Sconto: <%=bean.getSconto()%></p>
	            
	                
	              <p class="priceProd">€ <%= bean.getPrice()%></p> 
	                
	              <a class="addToCart" href="home?action=addC&id=<%=bean.getCode()%>">Aggiungi al carrello</a>
		    		        
	        </div>
		<%
			}
		}else{
				%><div class="product">
	        	<a href="detail?action=read&id=<%=bean.getCode()%>">
	            	<img class="imgProd" src="imgs/<%=bean.getCode()%>.png" alt="Product Image">
	            </a>
	            <a class="nameProd" href="detail?action=read&id=<%=bean.getCode()%>">
	            	<p class="nameProd"><%=bean.getName()%></p>
	            </a>
	            
	             
	             <p class="descrProd"><%=bean.getDescription()%></p>
	             <p>Sconto: <%=bean.getSconto()%></p>
	            
	                
	              <p class="priceProd">€ <%= bean.getPrice()%></p> 
	                
	              <a class="addToCart" href="home?action=addC&id=<%=bean.getCode()%>">Aggiungi al carrello</a>
		    		        
	        </div><%
				}
					}
			}
		%>
	
	
	<br>
	
	
	<%@ include file="footer.jsp"%>
</body>
</html>