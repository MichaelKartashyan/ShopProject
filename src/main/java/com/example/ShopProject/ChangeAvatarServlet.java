package com.example.ShopProject;

import DB.DBManager;
import Models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ChangeAvatarServlet", value = "/changeAvatar")
public class ChangeAvatarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/profile.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String avatar = request.getParameter("avatar");
        Long id = Long.parseLong(request.getParameter("id"));
        User user = DBManager.getUser(id);
        System.out.println(avatar);
        if(!avatar.trim().isEmpty()) {
            user.setImagePath(avatar);
            if (DBManager.updateUser(user)) {
                request.getSession().setAttribute("auth", user);
                request.setAttribute("successMessage", "User is updated");
                request.getRequestDispatcher("profile.jsp").forward(request, response);
            }
        }else{
            request.setAttribute("errorMessage","avatar is null");
            request.getRequestDispatcher("profile.jsp").forward(request,response);
        }



    }
}
