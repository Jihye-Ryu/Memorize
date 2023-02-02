package project.java.memorize.model;

public class User {
    
    public interface Entity {
        String TBL_USER_INFO = "USER_INFO";
        String COL_USER_ID_USER_INFO = "USER_INFO.USER_ID";    
        String COL_USER_ID = "USER_ID";    // 사용자 아이디
        String COL_PASSWORD = "PASSWORD";  // 비밀번호
        String COL_NAME = "NAME";    // 닉네임
        String COL_EMAIL = "EMAIL";  // 이메일
    }
    
    private String userId;
    private String password;
    private String name;
    private String email;
    
    public User() {}
    
    public User(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    
    @Override
    public String toString() {
        return String.format(
                "User(ID=%s, password=%s, name=%s, email=%s)", 
                userId, password, name, email);
    }

}
