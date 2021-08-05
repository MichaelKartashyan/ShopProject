<%@ page import="java.util.ArrayList" %>
<%@ page import="Models.Item" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>shop </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body>
<%@include file="navbar.jsp"%>

<div class="container mt-5">

<%
    ArrayList<Item> items = (ArrayList<Item>) request.getAttribute("items");
    for(Item item: items){
%>

    <div class="card col-5 mb-1" >

        <div class="card-body">
            <h5 class="card-title" ><%=item.getName()%> - <%=item.getPrice()%> USD</h5>
            <p class="card-text"><%=item.getDescription()%></p>
            <a href="/addCart?id=<%=item.getId()%>" class="btn btn-success"> + Add to basket</a>
        </div>
    </div>

   <% } %>

</div>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>