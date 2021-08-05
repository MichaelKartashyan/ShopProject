package DB;

import Models.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBManager {
    private static Connection connection;
    private  static String imagePath = "https://thumbs.dreamstime.com/b/пользователь-сми-вектора-значка-профиля-аватары-по-умолчанию-176256935.jpg";

    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop?serverTimezone=UTC","root","");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Item> getItems(){
        ArrayList<Item> items = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("Select * FROM items");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                items.add(
                        new Item(
                                resultSet.getLong("id"),
                                resultSet.getString("name"),
                                resultSet.getString("description"),
                                resultSet.getDouble("price")
                        )
                );
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return items;
    }

    public static Item getItem(Long id){
        Item item = new Item();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("Select * FROM items WHERE id = ?");
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                    item =   new Item(
                                resultSet.getLong("id"),
                                resultSet.getString("name"),
                                resultSet.getString("description"),
                                resultSet.getDouble("price")
                );
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return item;
    }


    public static User logIN(String email, String password){
        User user = new User();
        try{

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users " +
                    "WHERE email=? AND password=?");
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                user = new User(resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getString("image_path"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }



    public static boolean isExist(String email) {
        boolean tru = false;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from users where email=?");
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                tru = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return tru;
    }

    public static boolean addUser(User user) {
        int rows = 0;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users"+
                    "(email,password,full_name,image_path)"+
                    "VALUES(?,?,?,?)");
            preparedStatement.setString(1,user.getEmail());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getFullName());
            preparedStatement.setString(4,imagePath);
            rows = preparedStatement.executeUpdate();
        }catch (Exception e){e.printStackTrace();}
        return rows>0;
    }

    public static User getUser(Long id) {
        User user = new User();
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users " +
                    "WHERE id=?");
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getString("image_path")
                );
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public static boolean updateUser(User user) {
        int row = 0;

        try{
            PreparedStatement statement = connection.prepareStatement("UPDATE users " +
                    "SET email=?, " +
                    " password=?, full_name=?, image_path=? " +
                    "WHERE id=?");

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullName());
            statement.setString(4, user.getImagePath());
            statement.setLong(5, user.getId());

            row = statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }

        return row>0;
    }

    public static boolean addPost(Post post) {
        int rows = 0;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO posts"+
                    "(title,short_content,content,poster_id,date)"+
                    "VALUES(?,?,?,?,?)");
            preparedStatement.setString(1,post.getTitle());
            preparedStatement.setString(2,post.getShortContent());
            preparedStatement.setString(3,post.getContent());
            preparedStatement.setLong(4,post.getUser().getId());
            preparedStatement.setTimestamp(5,post.getTimestamp());
            rows = preparedStatement.executeUpdate();
        }catch (Exception e){e.printStackTrace();}
        return rows>0;
    }

    public static ArrayList<Post> getPosts() {
        ArrayList<Post> posts = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT p.id, p.title, p.short_content, p.content, p.date, " +
                            "u.id as id_poster, " +
                            "u.full_name, u.image_path,u.email, u.password" +
                            " from posts p " +
                            "inner join users u on p.poster_id = u.id");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                posts.add(new Post(
                        resultSet.getLong("p.id"),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        new User(
                                resultSet.getLong("id_poster"),
                                resultSet.getString("email"),
                                resultSet.getString("password"),
                                resultSet.getString("full_name"),
                                resultSet.getString("image_path")
                        ),
                        resultSet.getTimestamp("date")
                )
                );
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return posts;
    }

    public static Post getPost(Long id) {
        Post post = new Post();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from posts where id=?");
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                User user = DBManager.getUser(resultSet.getLong("poster_id"));
                post = new Post(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        user,
                        resultSet.getTimestamp("date")

                );
            }

        }catch (Exception e){e.printStackTrace();}
    return post;
    }

    public static boolean updatePost(Post post) {
        int row = 0;

        try{
            PreparedStatement statement = connection.prepareStatement("UPDATE posts " +
                    "SET title=?,short_content=?, content=?" +
                    "WHERE id=?");

            statement.setString(1, post.getTitle());
            statement.setString(2, post.getShortContent());
            statement.setString(3, post.getContent());
            statement.setLong(4, post.getId());

            row = statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }

        return row>0;
    }

    public static ArrayList<Post> getMyPosts(Long id) {
        ArrayList<Post> posts = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT p.id, p.title, p.short_content, p.content, p.date, " +
                            "u.id as id_poster, " +
                            "u.full_name, u.image_path,u.email, u.password" +
                            " from posts p " +
                            "inner join users u on p.poster_id = u.id where u.id=?");
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                posts.add(new Post(
                                resultSet.getLong("p.id"),
                                resultSet.getString("title"),
                                resultSet.getString("short_content"),
                                resultSet.getString("content"),
                                new User(
                                        resultSet.getLong("id_poster"),
                                        resultSet.getString("email"),
                                        resultSet.getString("password"),
                                        resultSet.getString("full_name"),
                                        resultSet.getString("image_path")
                                ),
                                resultSet.getTimestamp("date")
                        )
                );
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return posts;
    }


    public static boolean addComment(Comment comment){
        int row = 0;

        try{
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO comments"+
                    "(comment,post_id,user_id,like_comment)"+
                    "VALUES(?,?,?,?)");
            preparedStatement.setString(1,comment.getTextComment());
            preparedStatement.setLong(2,comment.getPostId());
            preparedStatement.setLong(3,comment.getUser().getId());
            preparedStatement.setInt(4,0);


            row = preparedStatement.executeUpdate();
        }catch (Exception e){e.printStackTrace();}

        return row>0;
    }



    public static ArrayList<Comment> getComments(Long id) {
        ArrayList<Comment> comments = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT *  from comments c " +
                            "inner join users u on c.user_id = u.id " +
                            "where c.post_id=?");
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                comments.add(new Comment(
                                resultSet.getLong("c.id"),
                                resultSet.getString("comment"),
                        new User(
                                resultSet.getLong("u.id"),
                                resultSet.getString("email"),
                                resultSet.getString("password"),
                                resultSet.getString("full_name"),
                                resultSet.getString("image_path")
                        ),
                                resultSet.getLong("post_id"),
                                resultSet.getInt("like_comment")
                        )
                );
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return comments;
    }


    public static void deleteComment(Long id){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM comments"+
                    " WHERE id=?");
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        }catch (Exception e){e.printStackTrace();}
    }

    public static boolean updateComment(Comment comment){
        int row = 0;

        try{
            PreparedStatement statement = connection.prepareStatement("UPDATE comments " +
                    "SET comment=?, like_comment=? " +
                    "WHERE id=?");

            statement.setString(1, comment.getTextComment());
            statement.setInt(2, comment.getLikeComment());
            statement.setLong(3, comment.getId());

            row = statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }

        return row>0;
    }

    public static Comment getComment(Long id) {
        Comment comment = new Comment();
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT *  from comments c " +
                            "inner join users u on c.user_id = u.id " +
                            "where c.id=?");
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                comment = new Comment(
                                resultSet.getLong("c.id"),
                                resultSet.getString("comment"),
                                new User(
                                        resultSet.getLong("u.id"),
                                        resultSet.getString("email"),
                                        resultSet.getString("password"),
                                        resultSet.getString("full_name"),
                                        resultSet.getString("image_path")
                                ),
                                resultSet.getLong("post_id"),
                        resultSet.getInt("like_comment")
                        );
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return comment;
    }


    public static boolean addLikeComment(Long userId, Long commentId) {
        int rows=0;

        try{
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO like_comment " +
                    "(id_user, id_comment, do_like)" +
                    "VALUES (?,?,?)");
            preparedStatement.setLong(1,userId);
            preparedStatement.setLong(2,commentId);
            preparedStatement.setInt(3,1);
            rows = preparedStatement.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }

        return rows>0;
    }


    public static boolean isLiked(Long userId, Long commentId){
        int do_like=0;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT do_like " +
                    "FROM like_comment " +
                    "WHERE id_user=? AND id_comment=? ");
            preparedStatement.setLong(1,userId);
            preparedStatement.setLong(2,commentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                do_like = resultSet.getInt("do_like");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return do_like>0;
    }


    public  static void updateLikeComment(Long userId, Long commentId,int doLike){
        try{
            PreparedStatement statement = connection.prepareStatement("UPDATE like_comment " +
                    "SET do_like=? " +
                    "WHERE id_user=? AND id_comment=? ");

            statement.setInt(1, doLike);
            statement.setLong(2, userId);
            statement.setLong(3, commentId);

            statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static boolean isLikeExist(Long userId, Long commentId){
        boolean do_like = true;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * " +
                    "FROM like_comment " +
                    "WHERE id_user=? AND id_comment=? ");
            preparedStatement.setLong(1,userId);
            preparedStatement.setLong(2,commentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                do_like = false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return do_like;
    }

    public static boolean deletePost(Long id) {
        int rows =0;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM posts"+
                    " WHERE id=?");
            preparedStatement.setLong(1,id);
           rows = preparedStatement.executeUpdate();
        }catch (Exception e){e.printStackTrace();}

        return rows>0;
    }
}
