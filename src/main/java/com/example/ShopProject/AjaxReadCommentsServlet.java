package com.example.ShopProject;

import DB.DBManager;
import Models.Comment;
import Models.Post;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "AjaxReadCommentsServlet", value = "/ajaxReadComments")
public class AjaxReadCommentsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/xml; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(request.getParameter("id"));
        Post post = DBManager.getPost(id);

        if(post!=null) {
            ArrayList<Comment> comments = DBManager.getComments(id);
            PrintWriter out = response.getWriter();
            Gson gson = new Gson();
            String result = gson.toJson(comments);
            out.print(result);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
