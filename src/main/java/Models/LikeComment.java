package Models;

public class LikeComment {
    Long id;
    Long idUser;
    Long idComment;
    boolean doLike;

    public LikeComment() {
    }

    public LikeComment(Long idUser, Long idComment, boolean doLike) {
        this.idUser = idUser;
        this.idComment = idComment;
        this.doLike = doLike;
    }

    public LikeComment(Long id, Long idUser, Long idComment, boolean doLike) {
        this.id = id;
        this.idUser = idUser;
        this.idComment = idComment;
        this.doLike = doLike;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdComment() {
        return idComment;
    }

    public void setIdComment(Long idComment) {
        this.idComment = idComment;
    }

    public boolean isDoLike() {
        return doLike;
    }

    public void setDoLike(boolean doLike) {
        this.doLike = doLike;
    }

    @Override
    public String toString() {
        return "LikeComment{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", idComment=" + idComment +
                ", doLike=" + doLike +
                '}';
    }
}
