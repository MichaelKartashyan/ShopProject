<%@ page import="java.util.ArrayList" %>
<%@ page import="Models.Item" %><%--
  Created by IntelliJ IDEA.
  User: Михаил
  Date: 22.07.2021
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body>
<%@include file="navbar.jsp"%>




<div class="container">
    <table class="table ">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Price</th>
            <th scope="col">Remove</th>
        </tr>
        </thead>
        <tbody >
        <%
            ArrayList<Item> items = (ArrayList<Item>) request.getAttribute("items");
           if(items!=null){ for(Item item:items){
        %>


        <tr>
            <th scope="row"><%=item.getName()%></th>
            <td><%=item.getDescription()%></td>
            <td><%=item.getPrice()%></td>
            <td><a href="/deleteCart?id=<%=item.getId()%>" class="btn btn-danger" >Delete</a></td>
        </tr>

        <%
           }
        }
        %>
        </tbody>
    </table>

</div>








<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
