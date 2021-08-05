<%--
  Created by IntelliJ IDEA.
  User: Михаил
  Date: 26.07.2021
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Profile</title>
   <%@include file="links.jsp"%>
</head>
<body>
<%@include file="navbar.jsp"%>
<% if(session.getAttribute("auth")!=null){ User user = (User) session.getAttribute("auth"); %>


<%if(request.getAttribute("errorMessage")!=null) { %>
<div class="alert alert-danger col-3 mx-auto" role="alert">
    <%=request.getAttribute("errorMessage")%>
</div>

<% } %>

<%if(request.getAttribute("successMessage")!=null) { %>
<div class="alert alert-success" role="alert">
    <%=request.getAttribute("successMessage")%>
</div>

<% } %>
<div class="container">

            <div class="row"> <img class="mx-auto image-icon rounded-circle " src="<%=user.getImagePath()%>"  style="width: 250px!important; height: 250px!important;"></div>
    <div class="col-12 d-flex">
        <div class="col-3">
                <form method="post" action="/profile">
                    <input type="hidden" value="<%=user.getId()%>" name="id" >
                    <div class="mb-3">
                        <label  class="form-label">Full name</label>
                        <input type="text" class="form-control"  name="name" aria-describedby="emailHelp" value="<%=user.getFullName()%>">
                    </div>

                    <div class="mb-3">
                        <label  class="form-label">Email address</label>
                        <input type="email" class="form-control"  name="email" aria-describedby="emailHelp" value="<%=user.getEmail()%>">
                    </div>



                    <button type="submit" class="btn btn-success">Accept</button>
                </form>

        </div>

        <div class="col-3 mx-5">
            <form method="post" action="/changePass">
                <input type="hidden" value="<%=user.getId()%>" name="id" >
                <div class="mb-3">
                    <label  class="form-label">Old password</label>
                    <input type="password" class="form-control"  name="oldPass" >
                </div>

                <div class="mb-3">
                    <label  class="form-label">New Password</label>
                    <input type="password" class="form-control"  name="pass" >
                </div>

                <div class="mb-3">
                    <label  class="form-label">Re password</label>
                    <input type="password" class="form-control"  name="repass"  >
                </div>



                <button type="submit" class="btn btn-success">Accept Password</button>
            </form>
        </div>

        <div class="col-3 mx-5">
            <form method="post" action="/changeAvatar">
                <input type="hidden" value="<%=user.getId()%>" name="id" >

                <div class="mb-3">
                    <label  class="form-label">Avatar path</label>
                    <input type="text" class="form-control"  name="avatar" value="<%=user.getImagePath()%>">
                </div>

<%--                <div class="mb-3">--%>
<%--                    <label  class="form-label">Image</label>--%>
<%--                    <input type="file" class="form-control"  name="avatar" value="<%=user.getImagePath()%>">--%>
<%--                </div>--%>

                <button type="submit" class="btn btn-success">Accept avatar</button>
            </form>
        </div>
    </div>
</div>

<%} else {%>
    <p>you are not enter </p><a href="/">Go home </a>
<% } %>

<%@include file="script.jsp"%>
</body>
</html>
