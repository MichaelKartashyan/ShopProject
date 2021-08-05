package com.example.ShopProject;

import DB.DBManager;
import Models.Item;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AddCartServlet", value = "/addCart")
public class AddCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("id")!=null) {
        Long id = Long.parseLong(request.getParameter("id"));

        if (id != null) {
            ArrayList<Item> items = new ArrayList<>();
            HttpSession session = request.getSession();
            if (session.getAttribute("items") != null) {
                items = (ArrayList<Item>) session.getAttribute("items");
            }
            Item item = DBManager.getItem(id);
            items.add(item);

            session.setAttribute("items", items);
            response.sendRedirect("/");
        }
    }

        if(request.getParameter("Sid")!=null) {
            Long Sid = Long.parseLong(request.getParameter("Sid"));
            if (Sid != null) {
                ArrayList<Item> items = new ArrayList<>();
                HttpSession session = request.getSession();
                if (session.getAttribute("items") != null) {
                    items = (ArrayList<Item>) session.getAttribute("items");
                }
                Item item = DBManager.getItem(Sid);
                items.add(item);

                session.setAttribute("items", items);
                response.sendRedirect("/shopPage2");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
