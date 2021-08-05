<%@ page import="java.util.ArrayList" %>
<%@ page import="Models.Post" %><%--
  Created by IntelliJ IDEA.
  User: Михаил
  Date: 28.07.2021
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Blog</title>
  <%@include file="links.jsp"%>
</head>
<body>
<%@include file="navbar.jsp"%>



<%
User user = (User) session.getAttribute("auth");
%>

<%ArrayList<Post> posts = (ArrayList<Post>) request.getAttribute("posts");%>

<%if(request.getAttribute("successMessage")!=null) { %>
<div class="alert alert-success" role="alert">
    <%=request.getAttribute("successMessage")%>
</div>
<% } %>


<div class="container">
   <% if(session.getAttribute("auth")!=null){ %>
<div>
    <a href="/myBlog?id=<%=user.getId()%>" class="text-dark">My posts</a> / <a href="/blog" class="text-dark" >All posts</a>
</div>
    <% } %>
    <% for(Post post : posts) { %>
    <div class="card text-center w-50 mx-auto my-3" >
        <div class="card-body text-start">
            <h5 class="card-title"><%=post.getTitle()%></h5>
            <p class="card-text"><%=post.getShortContent()%></p>
            <a href="/detailNew?id=<%=post.getId()%>" class="text-info " >Show news</a>
        </div>
        <div class="card-footer text-start">
            <img src="<%=post.getUser().getImagePath()%>" height="40px" width="40px" class="rounded-circle me-2"><%=post.getUser().getFullName()%> <%=post.getTimestamp()%>
        </div>
    </div>


    <% } %>


</div>


<%@include file="script.jsp"%>
</body>
</html>
