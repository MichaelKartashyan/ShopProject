<%@ page import="Models.User" %>
<%
    if(session.getAttribute("auth")!=null){
        User user = (User) session.getAttribute("auth");

%>

<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Shop</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" >
            <div class="navbar-nav">
                <a class="nav-link " aria-current="page" href="/">Shop</a>
                <a class="nav-link " aria-current="page" href="/shopPage2">Page 2</a>
                <a class="nav-link" href="/cart">Cart</a>
                <a class="nav-link" href="/addBlog">Add Blog</a>
                <a class="nav-link" href="/blog">Blog</a>

            </div>

        </div>
        <div class="navbar-nav float-end">
            <a href="/profile" class="nav-link  ">Admin Panel</a>
            <a  class="nav-link float-end "><%=user.getFullName()%></a>
            <a href="/auth?logout=1" class="nav-link ">Log out</a>
        </div>
    </div>
</nav>


<% }else { %>

<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Shop</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-link " aria-current="page" href="/">Shop</a>
                <a class="nav-link " aria-current="page" href="/shopPage2">Page 2</a>
                <a class="nav-link" href="/cart">Cart</a>
                <a class="nav-link" href="/blog">Blog</a>

            </div>

        </div>
        <a href="/register.jsp" class="nav-link float-end ">Register</a>
        <a href="/auth.jsp" class="nav-link float-end ">Log In</a>
    </div>
</nav>

<% } %>
