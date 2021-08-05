package com.example.ShopProject;

import DB.DBManager;
import Models.Comment;
import Models.LikeComment;
import Models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LikeCommentServlet", value = "/likeComment")
public class LikeCommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        Long postId = Long.parseLong(request.getParameter("post"));

        Comment comment = DBManager.getComment(id);
        User user = (User) request.getSession().getAttribute("auth");
        //если юзер уже ставил лайк
        if(DBManager.isLiked(user.getId(),id)){
            //количество лайков
            int like = comment.getLikeComment();
            comment.setLikeComment(--like);
            //заливаем коменты в базу коментов
            DBManager.updateComment(comment);
            //делаем так как будто пользователь еще не лайкал
            int doLike = 0;
            DBManager.updateLikeComment(user.getId(),id,doLike);
           // response.sendRedirect("/detailNew?id=" + postId);
        }else{
            if(DBManager.isLikeExist(user.getId(),id)){
                //если такой записи о лайках пользователя еще нету
                DBManager.addLikeComment(user.getId(),id);
            }
            //количество лайков
            int like = comment.getLikeComment();
            comment.setLikeComment(++like);
            //заливаем коменты в базу коментов
            DBManager.updateComment(comment);
            //делаем так как будто пользователь уже  пролайкал
            int doLike = 1;
            DBManager.updateLikeComment(user.getId(),id,doLike);
           // response.sendRedirect("/detailNew?id=" + postId);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
