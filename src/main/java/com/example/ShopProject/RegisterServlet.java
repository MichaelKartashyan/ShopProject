package com.example.ShopProject;

import DB.DBManager;
import Models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");

        if(password.equals(repassword)){
            if(!DBManager.isExist(email)){
                User user = new User(email,password,name);
                DBManager.addUser(user);
                System.out.println(user);
            }else{
                request.setAttribute("error","Email is alredy exist");
                request.getRequestDispatcher("register.jsp").forward(request,response);
            }
        }else{
            request.setAttribute("error","Passwords is not equal");
            request.getRequestDispatcher("register.jsp").forward(request,response);
        }
        request.getRequestDispatcher("register.jsp").forward(request,response);
    }
}
