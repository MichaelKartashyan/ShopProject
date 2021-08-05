package com.example.ShopProject;

import DB.DBManager;
import Models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ProfileServlet", value = "/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("profile.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("name");
        String email = request.getParameter("email");
        Long id =  Long.parseLong(request.getParameter("id"));

        User user = DBManager.getUser(id);
        user.setFullName(fullName);
        user.setEmail(email);

        if(DBManager.updateUser(user)){
            request.getSession().setAttribute("auth", user);
            request.setAttribute("successMessage","User is updated");
            request.getRequestDispatcher("profile.jsp").forward(request,response);
        }else{
            request.setAttribute("errorMessage","user are not updated");
            request.getRequestDispatcher("profile.jsp").forward(request,response);
        }

    }
}
