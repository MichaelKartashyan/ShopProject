package com.example.ShopProject;

import DB.DBManager;
import Models.Comment;
import Models.Post;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "DetailNewServlet", value = "/detailNew")
public class DetailNewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("id")!=null) {
            response.setContentType("text/xml; charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            Long id = Long.parseLong(request.getParameter("id"));
            Post post = DBManager.getPost(id);
            request.setAttribute("post", post);

//            ArrayList<Comment> comments = DBManager.getComments(id);
//            request.setAttribute("comments",comments);

        }
        request.getRequestDispatcher("detailNew.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
