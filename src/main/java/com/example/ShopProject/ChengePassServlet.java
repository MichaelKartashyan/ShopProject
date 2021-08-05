package com.example.ShopProject;

import DB.DBManager;
import Models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ChengePassServlet", value = "/changePass")
public class ChengePassServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/profile.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldPass = request.getParameter("oldPass");
        String pass = request.getParameter("pass");
        String repass = request.getParameter("repass");
        Long id = Long.parseLong(request.getParameter("id"));

        User user = DBManager.getUser(id);
        if (user.getPassword().equals(oldPass)) {
            if (oldPass.equals(pass)) {
                request.setAttribute("errorMessage", "New password match old password");
                request.getRequestDispatcher("profile.jsp").forward(request, response);
            } else {
                if (pass.equals(repass)) {
                    user.setPassword(pass);
                    if (DBManager.updateUser(user)) {
                        request.getSession().setAttribute("auth", user);
                        request.setAttribute("successMessage", "User is updated");
                        request.getRequestDispatcher("profile.jsp").forward(request, response);
                    }


                } else {
                    request.setAttribute("errorMessage", "Different password entered");
                    request.getRequestDispatcher("profile.jsp").forward(request, response);
                }
            }

        } else {
            request.setAttribute("errorMessage", "Password mismatch");
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        }

    }
}
