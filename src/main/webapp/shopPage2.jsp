<%@ page import="java.util.ArrayList" %>
<%@ page import="Models.Item" %>
<%@ page import="Models.User" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Shop</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<%@include file="navbar.jsp"%>







<div class="container col-10 mx-auto text-center">

    <h1 class="mt-3">Welcome to MIKE SHOP</h1>
    <p>Electronic devices with high quality and service</p>
    <div class="d-flex flex-wrap" >
        <%
        ArrayList<Item> items = (ArrayList<Item>) request.getAttribute("items");
        for(Item item: items){
        %>


        <div class="card text-center  mt-5 mx-auto" style="width: 18rem;">
            <h5 class="card-title card-header"><%=item.getName()%></h5>
            <div class="card-body">

                <h5 class="card-text text-success my-4">$<%=item.getPrice()%></h5>
                <p class="card-text"><%=item.getDescription()%></p>
                <div class="row">
                    <a href="/addCart?Sid=<%=item.getId()%>" class="btn btn-success">Buy Now</a>
                </div>
            </div>
        </div>
        <%
            }
        %>
    </div>
</div>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
