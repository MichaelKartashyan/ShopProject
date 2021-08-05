package com.example.ShopProject;

import DB.DBManager;
import Models.Item;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "DeleteCartServlet", value = "/deleteCart")
public class DeleteCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        HttpSession session = request.getSession();
        ArrayList<Item> items= (ArrayList<Item>) session.getAttribute("items");


        for(Item item : items) {
         if(id==item.getId()) {
             items.remove(item);
             break;
         }
        }


        session.setAttribute("items",items);
        response.sendRedirect("/cart");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
