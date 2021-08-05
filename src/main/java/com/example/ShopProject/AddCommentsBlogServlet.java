package com.example.ShopProject;

import DB.DBManager;
import Models.Comment;
import Models.User;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddCommentsBlogServlet", value = "/addcommentsblog")
public class AddCommentsBlogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        if(request.getSession().getAttribute("auth")!=null){
//            response.setContentType("text/xml; charset=UTF-8");
//            request.setCharacterEncoding("UTF-8");
//            String textComment = request.getParameter("textComment");
//            User user = (User) request.getSession().getAttribute("auth");
//            Long postId = Long.parseLong(request.getParameter("postId"));
//            Comment comment = new Comment(textComment,user,postId);
//
//            DBManager.addComment(comment);
//            response.sendRedirect("/detailNew?id="+postId);
//        }



        if(request.getSession().getAttribute("auth")!=null){
            response.setContentType("text/xml; charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            String textComment = request.getParameter("textComment");
            User user = (User) request.getSession().getAttribute("auth");
            Long postId = Long.parseLong(request.getParameter("postId"));
            Comment comment = new Comment(textComment,user,postId);


            if(DBManager.addComment(comment)){

            }
//            response.sendRedirect("/detailNew?id="+postId);
        }

    }
}
