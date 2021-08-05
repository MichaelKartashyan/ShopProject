package Models;

public class User {
    private Long id;
    private String email;
    private String password;
    private String fullName;
    private String imagePath;

    public User() {
    }

    public User(String email, String password, String fullName) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }

    public User(String email, String password, String fullName, String imagePath) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.imagePath = imagePath;
    }

    public User(String email, String fullName) {
        this.email = email;
        this.fullName = fullName;
    }

    public User(Long id, String email, String password, String fullName, String imagePath) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.imagePath = imagePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String  getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullName=" + fullName +
                '}';
    }
}
