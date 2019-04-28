package com.zmj.entity;
/**
 * 用户
 */
public class User {
    private int user_id;
    private String user_name;
    private int user_type;
    private String user_pwd;

    public User() {
    }

    public User(int user_id, String user_name, int user_type, String user_pwd) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_type = user_type;
        this.user_pwd = user_pwd;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    @Override
    public String toString() {
        return "用户【" +
                "用户id=" + user_id +
                ", 用户姓名=" + user_name +
                ", 用户密码=" + user_pwd +
                '】';
    }
}
