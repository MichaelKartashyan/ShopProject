package com.example.ShopProject;

import DB.DBManager;
import Models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AuthServlet", value = "/auth")
public class AuthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(request.getParameter("logout")!=null){
            session.removeAttribute("auth");
        }
        request.getRequestDispatcher("/shopPage2").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = DBManager.logIN(email,password);
        if(user.getFullName()!=null){
            HttpSession session = request.getSession();
            session.setAttribute("auth",user);

        }else {
            request.setAttribute("errorMessage","invalid Login or password");
            request.getRequestDispatcher("auth.jsp").forward(request,response);
        }

        response.sendRedirect("/auth");
    }
}
