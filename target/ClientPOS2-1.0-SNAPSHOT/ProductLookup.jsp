<%@page import="com.carolsboutique.clientpos.barcode.service.BarcodeReader"%>
<%@page import="com.carolsboutique.clientpos.product.model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Lookup Page</title>
    </head>
    <%
        Product product = (Product)request.getAttribute("product");
        String lookupResp = (String)request.getAttribute("lookupResp");
        BarcodeReader barcodeReader = new BarcodeReader();
        //need method in barcode dao to scan poduct ID in
    %>
    <body>
        <% if(lookupResp!=null){ %>
        <h2><%=lookupResp %></h2><br>
        <% } %>
        <% if(product==null){ %>
        <% barcodeReader.readBarcode(); %>
        <form action="ProductLookup" method="get">
            <h2>Please enter Product ID, or scan Product Barcode: </h2>
            <label>Product ID:<br><input type="text" name="productID"></label>
            <input type="submit" name="submit" value="Look Up">
        </form>
        <% } else { %>
        <h1><%=product.getName()%></h1>
        <p><%=product.getDescription()%></p><br>
        <p>Size: <%=product.getSize()%></p>
        <p>Price: <%=product.getPrice()%></p>
        <% } %>
        <br>
        <a href="ProductController?submit=getIbtPage">Create an IBT</a><br>
        <a href="ProductController?submit=getKeepAsidePage">Create a Keep Aside</a><br>
    </body>
</html>
