package Models;

public class Comment {
    Long id;
    String TextComment;
    User user;
    Long postId;
    int likeComment;

    public Comment() {
    }

    public Comment(String textComment, User user, Long postId) {
        TextComment = textComment;
        this.user = user;
        this.postId = postId;
    }

    public Comment(String textComment, User user, Long postId, int likeComment) {
        TextComment = textComment;
        this.user = user;
        this.postId = postId;
        this.likeComment = likeComment;
    }

    public Comment(Long id, String textComment, User user, Long postId, int likeComment) {
        this.id = id;
        TextComment = textComment;
        this.user = user;
        this.postId = postId;
        this.likeComment = likeComment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTextComment() {
        return TextComment;
    }

    public void setTextComment(String textComment) {
        TextComment = textComment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public int getLikeComment() {
        return likeComment;
    }

    public void setLikeComment(int likeComment) {
        this.likeComment = likeComment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", TextComment='" + TextComment + '\'' +
                ", user=" + user +
                ", postId=" + postId +
                ", likeComment=" + likeComment +
                '}';
    }
}
