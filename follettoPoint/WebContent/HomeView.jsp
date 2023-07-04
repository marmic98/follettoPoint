<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    Collection<?> products = (Collection<?>) request.getAttribute("products");
Collection<?> products2 = (Collection<?>) request.getAttribute("products2");

    if(products == null) {
        response.sendRedirect("./home");	
        return;
    }
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.*"%>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="ProductStyle.css" rel="stylesheet" type="text/css">
    <title>FollettoPoint</title>
</head>
<style>
.product-section {
    display: flex;
    overflow-x: auto; /* Aggiunge uno scroll orizzontale se i prodotti superano la larghezza dello schermo */
    white-space: nowrap; /* Impedisce il wrapping dei prodotti su più righe */
}

.product {
    width: 300px;
    margin: 10px;
    padding: 10px;
    border: 1px solid #ccc;
    text-align: center;
}

.product img {
    width: 200px;
    height: 200px;
    object-fit: cover;
    margin-bottom: 10px;
}
</style>
<body>
    <%@ include file="header.jsp"%>
    <h2>Prodotti</h2>
    <h3><a href="product?sort=null">Reset filtri</a></h3>
    <%-- Section: Products sorted by ID --%>
    <h3>Prodotti piu' recenti, <a href="product?sort=nome">Ordina per nome</a></h3>
    <div class="product-section">
        <%
            if (products != null && products.size() != 0) {
                Iterator<?> it = products.iterator();
                while (it.hasNext()) {
                    ProductBean bean = (ProductBean) it.next();
        %>
        <div class="product">
            <img src="imgs/<%=bean.getCode()%>.png" alt="Product Image">
            <p><%=bean.getName()%></p>
               <p>Price: <%= bean.getPrice() - (bean.getPrice() * bean.getSconto() / 100) %></p>
             <p>ID: <%=bean.getCode()%></p>
             <p>Quantità: <%=bean.getQuantity()%></p>
              <p>Sconto: <%=bean.getSconto()%></p>
            <p>
                <a href="home?action=delete&id=<%=bean.getCode()%>">Delete</a>
                <br>
                <a href="detail?action=read&id=<%=bean.getCode()%>">Details</a>
                <br>
                <a href="home?action=addC&id=<%=bean.getCode()%>">Add to cart</a>
            </p>
        </div>
        <%
                }
            } else {
        %>
        <p>No products available</p>
        <%
            }
        %>
    </div>
    
    <%-- Section: Products sorted by discount --%>
    <h3>Prodotti scontati, <a href="product?sort=prezzo">Ordina per prezzo</a></h3>
    <div class="product-section">
        <%
            if (products2 != null && products2.size() != 0) {
                Iterator<?> it = products2.iterator();
                while (it.hasNext()) {
                    ProductBean bean = (ProductBean) it.next();
        %>
      
        <div class="product">
            <img src="imgs/<%=bean.getCode()%>.png" alt="Product Image">
            <p><%=bean.getName()%></p>
            <p>Price: <%= bean.getPrice() - (bean.getPrice() * bean.getSconto() / 100) %></p>
             <p>ID: <%=bean.getCode()%></p>
             <p>Quantità: <%=bean.getQuantity()%></p>
              <p>Sconto: <%=bean.getSconto()%></p>
            <p>
                <a href="home?action=delete&id=<%=bean.getCode()%>">Delete</a>
                <br>
                <a href="detail?action=read&id=<%=bean.getCode()%>">Details</a>
                <br>
                <a href="home?action=addC&id=<%=bean.getCode()%>">Add to cart</a>
            </p>
        </div>
        <%
                }
            } else {
        %>
        <p>No products available</p>
        <%
            }
        %>
    </div>
    
    <%@ include file="footer.jsp"%>
</body>
</html>