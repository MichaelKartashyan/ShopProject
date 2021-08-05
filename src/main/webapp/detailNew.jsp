<%@ page import="java.util.ArrayList" %>
<%@ page import="Models.Post" %>
<%@ page import="Models.Comment" %><%--
  Created by IntelliJ IDEA.
  User: Михаил
  Date: 28.07.2021
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Detail new</title>
  <%@include file="links.jsp"%>
</head>
<body onload="loadComments()">

<%@include file="navbar.jsp"%>



<%
    if(request.getAttribute("post")!=null){Post post = (Post) request.getAttribute("post");

%>
<div class="container">
      <h1 class="card-title"><%=post.getTitle()%></h1>

  <p><%=post.getContent()%></p>


    <% if(session.getAttribute("auth")!=null){ %>
    <% User user = (User) request.getSession().getAttribute("auth");
    if(post.getUser().getId()==user.getId()){
    %>

    <a href="/editNew?id=<%=post.getId()%>" class="btn btn-primary">Edit post</a>
    <% } %>
    <% } %>
</div>






<%--<div class="container my-5">--%>

<%--    <form class="" method="post" action="/addcommentsblog">--%>
<%--        <div class="  col-6 d-flex justify-content-between ">--%>
<%--            <textarea class="w-100" id="comment" name="textComment"></textarea>--%>
<%--            <input type="hidden"  id="id_post" name="postId" value="<%=post.getId()%>">--%>
<%--            <button type="submit" class="btn btn-primary col-3 ms-3" style="height: 40px;" >Add Comment</button>--%>
<%--        </div>--%>

<%--    </form>--%>

<%--    <% if(request.getAttribute("comments")!=null) {--%>
<%--        ArrayList<Comment> comments = (ArrayList<Comment>) request.getAttribute("comments");--%>

<%--        for(Comment comment:comments){--%>

<%--    %>--%>

<%--    <div class="row d-flex mt-3">--%>
<%--        <div class="col-md-8">--%>
<%--            <div class="card p-3">--%>
<%--                <div class="d-flex justify-content-between align-items-center">--%>
<%--                    <div class="user d-flex flex-row align-items-center"> <img src="<%=comment.getUser().getImagePath()%>" width="30" class="user-img rounded-circle mr-2"> <span><small class="font-weight-bold text-primary"><%=comment.getUser().getFullName()%></small> <small class="font-weight-bold"><%=comment.getTextComment()%></small></span> </div> <small>--%>
<%--                    <% User user = (User) request.getSession().getAttribute("auth");--%>
<%--                    if(comment.getUser().getId()==user.getId()){--%>

<%--                        //Delete and like  Comment--%>

<%--                    %>--%>
<%--                    <img width="20px" height="20px" src="https://img.icons8.com/material-two-tone/452/filled-like.png"> <%=comment.getLikeComment()%> <a href="/deleteComment?id=<%=comment.getId()%>&post=<%=comment.getPostId()%>" class="text-danger" style="text-decoration: none; font-size: 20px;">X</a>--%>
<%--                   <% }else{ %>--%>
<%--                        <a href="/likeComment?id=<%=comment.getId()%>&post=<%=comment.getPostId()%>"><img width="20px" height="20px" src="https://img.icons8.com/material-two-tone/452/filled-like.png"></a> <%=comment.getLikeComment()%>--%>
<%--                    <% } %>--%>
<%--                </small>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <%--%>
<%--            }--%>
<%--        }--%>
<%--    %>--%>
<%--</div>--%>




<script type="text/javascript">
    function loadComments(){

        $.ajax({
            url: "/ajaxReadComments?id=<%=post.getId()%>",
            success: function (result){

                let commentList = result;

                console.log(commentList)

                let htmlCode = "";


                for(let i=0; i<commentList.length;i++) {
                    htmlCode += "<div class='row d-flex mt-3'>" +
                        "<div class='col-md-8'>" +
                        "<div class='card p-3'>" +
                        "<div class='d-flex justify-content-between align-items-center'>" +
                        "<div class='user d-flex flex-row align-items-center'> <img src=' " + commentList[i].user.imagePath + " '  width='30' class='user-img rounded-circle mr-2'> <span><small class='font-weight-bold text-primary'>" + commentList[i].user.fullName + "</small> <small class='font-weight-bold'>" + commentList[i].TextComment + "</small></span> </div> <small>"

                    <% if(session.getAttribute("auth")!=null){ %>
                    <% User user = (User) request.getSession().getAttribute("auth"); %>
                    if (commentList[i].user.id == <%=user.getId()%>) {

                        //Delete and like  Comment

                        htmlCode += "<span class='me-1'> <img width='20px' height='20px' src='https://img.icons8.com/material-two-tone/452/filled-like.png' class='mb-2'> "+ commentList[i].likeComment + "</span> <a onclick='deleteComment(" +commentList[i].id + "," + commentList[i].postId + ")'  class='text-danger' style='text-decoration: none; font-size: 20px; cursor: pointer'>X</a>"

                    }else{

                        htmlCode +=  "<span class='me-4'>  <a   onclick='likeComment(" +commentList[i].id + ")' style='cursor: pointer;'  ><img width='20px' height='20px' src='https://img.icons8.com/material-two-tone/452/filled-like.png'></a> "+ commentList[i].likeComment + "</span>"

                    }
                    <% }else { %>
                    htmlCode +=  "<span class='me-3'> <img width='20px' height='20px' src='https://img.icons8.com/material-two-tone/452/filled-like.png'>" + commentList[i].likeComment + "</span>"
                    <% } %>
                    htmlCode += "</small> </div> </div> </div> </div>"
                }

                document.getElementById("commentList").innerHTML = htmlCode;
            },
            dataType: "json"
        });
    }

    function likeComment(idComent){
        $.ajax({
            type:"GET",
            url: "/likeComment?id="+idComent+"&post=<%=post.getId()%>",
            success: function (){
                loadComments();
            },

        });
    }


    function deleteComment(idComment,postId){
        $.ajax({
            type:"GET",
            url:"/deleteComment?id="+idComment+"&post="+postId,
            success:function (){
                loadComments();
            }
        });
    }
</script>



<div class="container my-5">

    <% if(session.getAttribute("auth")!=null){ %>
        <div class="  col-9 d-flex justify-content-between ">
            <textarea class="w-100" id="textComment" name="textComment"></textarea>
            <button type="submit" class="btn btn-primary col-3 ms-3" style="height: 40px; width: 200px" onclick="addComment()" >Add Comment</button>
        </div>
    <% } %>
        <script type="text/javascript">
            function addComment(){
               let commentText = document.getElementById("textComment");

                $.post("/addcommentsblog", {
                   textComment:  commentText.value,
                    postId: <%=post.getId()%>
                }, function (result){
                    commentText.value = "";
                    loadComments();
                });

            }


        </script>



    <div id="commentList">

    </div>

</div>

<% } %>







<%@include file="script.jsp"%>
</body>
</html>
