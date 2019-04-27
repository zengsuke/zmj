package com.zmj.entity;

/**
 * 影评
 */
public class Comment {
    private int comment_id;
    private int user_id;
    private int movie_id;
    private String comment_content;

    public Comment() {
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

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public Comment(int comment_id, int user_id, int movie_id, String comment_content) {
        this.comment_id = comment_id;
        this.user_id = user_id;
        this.movie_id = movie_id;
        this.comment_content = comment_content;
    }

    @Override
    public String toString() {
        return "影评【" +
                "影评id=" + comment_id +
                ", 用户id=" + user_id +
                ", 电影id=" + movie_id +
                ", 影评内容='" + comment_content + '\'' +
                '】';
    }
}
