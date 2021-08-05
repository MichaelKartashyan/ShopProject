package com.example.ShopProject;

import DB.DBManager;
import Models.Post;
import Models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(name = "AddBlogServlet", value = "/addBlog")
public class AddBlogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/addBlog.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/xml; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        String shortContent = request.getParameter("shortContent");
        String content = request.getParameter("content");
        User user = (User) request.getSession().getAttribute("auth");
        Post post = new Post(title,shortContent,content,user,new Timestamp(System.currentTimeMillis()));
        if(DBManager.addPost(post)){
            request.setAttribute("successMessage","post success created");
            request.getRequestDispatcher("addBlog.jsp").forward(request,response);
        }
    }
}
