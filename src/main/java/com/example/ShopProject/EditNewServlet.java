package com.example.ShopProject;

import DB.DBManager;
import Models.Post;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditNewServlet", value = "/editNew")
public class EditNewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("id")!=null) {
            Long id = Long.parseLong(request.getParameter("id"));
            Post post = DBManager.getPost(id);
            request.setAttribute("post", post);
        }
       request.getRequestDispatcher("editNew.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/xml; charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            Long id = Long.parseLong(request.getParameter("id"));
            String title = request.getParameter("title");
            String shortContent = request.getParameter("shortContent");
            String content = request.getParameter("content");
            Post post = DBManager.getPost(id);
            post.setTitle(title);
            post.setShortContent(shortContent);
            post.setContent(content);
            DBManager.updatePost(post);
        response.sendRedirect("/blog");

    }
}
