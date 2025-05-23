package edu.cdtu.entity;

public class User
{
    private String username;//用户名
    private String password;//密码
    private String okpwd;//确认密码

    public User()
    {

    }

    public User(String username, String password, String okpwd)
    {
        this.username = username;
        this.password = password;
        this.okpwd = okpwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOkpwd() {
        return okpwd;
    }

    public void setOkpwd(String okpwd) {
        this.okpwd = okpwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", okpwd='" + okpwd + '\'' +
                '}';
    }
}
