package Models;

import java.sql.Timestamp;

public class Post {
    Long id;
    String title;
    String shortContent;
    String content;
    User user;
    Timestamp timestamp;

    public Post() {
    }

    public Post(String title, String shortContent, String content, User user, Timestamp timestamp) {
        this.title = title;
        this.shortContent = shortContent;
        this.content = content;
        this.user = user;
        this.timestamp = timestamp;
    }

    public Post(Long id, String title, String shortContent, String content, User user, Timestamp timestamp) {
        this.id = id;
        this.title = title;
        this.shortContent = shortContent;
        this.content = content;
        this.user = user;
        this.timestamp = timestamp;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortContent() {
        return shortContent;
    }

    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", shortContent='" + shortContent + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
