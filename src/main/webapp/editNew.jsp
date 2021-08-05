<%@ page import="Models.Post" %><%--
  Created by IntelliJ IDEA.
  User: Михаил
  Date: 28.07.2021
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Blog</title>
    <script src="https://cdn.tiny.cloud/1/avg41nywxwv4lre92oi9o5i21g0p8e23ac129ueamdjmgul2/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>

    <%@include file="links.jsp"%>

</head>
<body>
<%@include file="navbar.jsp"%>


<% if(session.getAttribute("auth")!=null){ %>


<%if(request.getAttribute("successMessage")!=null) { %>
<div class="alert alert-success" role="alert">
    <%=request.getAttribute("successMessage")%>
</div>

<% } %>


<%
    if(request.getAttribute("post")!=null){Post post = (Post) request.getAttribute("post");
        User user = (User) request.getSession().getAttribute("auth");
    if(post.getUser().getId()==user.getId()){
%>



    <div class="container">
        <div class="row mt-5">
            <div class="col-6 offset-3">
                <form action="/editNew" method="post">
                    <input type="hidden" value="<%=post.getId()%>" name="id">
                    <div class="form-control border-0 my-5" >
                        <label>Title:</label>
                        <input type="text" name="title" style="width: 500px;" value="<%=post.getTitle()%>">
                    </div>

                    <div class="form-control border-0 my-5">
                        <label>Short content:</label>
                        <textarea name="shortContent" style="width: 500px;height: 100px;" > <%=post.getShortContent()%></textarea>
                    </div>

                    <div class="form-control border-0 my-5">
                        <label>Content:</label>
                        <textarea id="contentArea" name="content" style="width: 500px;height: 500px;"><%=post.getContent()%></textarea>
                    </div>

                    <button type="submit" class="btn btn-primary">Submit</button>
                    <a href="/deletePost?id=<%=post.getId()%>" class="btn btn-danger">Delete Post</a>
                </form>
            </div>
        </div>

    </div>

    <script>
        tinymce.init({
            selector: 'textarea',
            plugins: 'a11ychecker advcode casechange export formatpainter linkchecker autolink lists checklist media mediaembed pageembed permanentpen powerpaste table advtable tinycomments tinymcespellchecker',
            toolbar: 'a11ycheck addcomment showcomments casechange checklist code export formatpainter pageembed permanentpen table',
            toolbar_mode: 'floating',
            tinycomments_mode: 'embedded',
            tinycomments_author: 'Author name',
        });
    </script>

 <% } %>
<% }else{ %>
<div class="alert alert-warning" role="alert">
    <p>you are not enter, please <a href="/auth.jsp">Enter</a></p>
</div>
<% } %>

<% } %>

<%@include file="script.jsp"%>
</body>
</html>
