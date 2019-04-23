package com.zmj.entity;

public class Comment {
    private int comment_id;
    private int user_id;
    private String comment_content;

    public Comment() {
    }

    public Comment(int comment_id, int user_id, String comment_content) {
        this.comment_id = comment_id;
        this.user_id = user_id;
        this.comment_content = comment_content;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "comment_id=" + comment_id +
                ", user_id=" + user_id +
                ", comment_content='" + comment_content + '\'' +
                '}';
    }
}
