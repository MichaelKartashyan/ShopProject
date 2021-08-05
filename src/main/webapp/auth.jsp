<%--
  Created by IntelliJ IDEA.
  User: Михаил
  Date: 23.07.2021
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log In</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<%@include file="navbar.jsp"%>


<%if(request.getAttribute("errorMessage")!=null) { %>
<div class="alert alert-danger col-3 mx-auto" role="alert">
   <%=request.getAttribute("errorMessage")%>
</div>

<% } %>
<%
    HttpSession session1 = request.getSession();
    if(session1.getAttribute("auth")!=null){
%>

    <% }else { %>

<div class="container col-3">
    <form method="post" action="/auth">
        <div class="mb-3">
            <label  class="form-label">Email address</label>
            <input type="email" class="form-control"  name="email" aria-describedby="emailHelp">
        </div>
        <div class="mb-3">
            <label class="form-label">Password</label>
            <input type="password" class="form-control"  name="password">
        </div>

        <button type="submit" class="btn btn-primary">Log In</button>
    </form>
</div>

    <% } %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
