package com.zmj.entity;

/**
 * 电影
 */
public class Movie {
    private int movie_id;//id
    private String movie_name;//名字
    private String movie_type;//类型
    private String movie_introduction;//介绍
    private int movie_time;//时长
    private int movie_count;//购票数量

    public Movie() {
    }

    public Movie(int movie_id, String movie_name, String movie_type, String movie_introduction, int movie_time, int movie_count) {
        this.movie_id = movie_id;
        this.movie_name = movie_name;
        this.movie_type = movie_type;
        this.movie_introduction = movie_introduction;
        this.movie_time = movie_time;
        this.movie_count = movie_count;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getMovie_type() {
        return movie_type;
    }

    public void setMovie_type(String movie_type) {
        this.movie_type = movie_type;
    }

    public String getMovie_introduction() {
        return movie_introduction;
    }

    public void setMovie_introduction(String movie_introduction) {
        this.movie_introduction = movie_introduction;
    }

    public int getMovie_time() {
        return movie_time;
    }

    public void setMovie_time(int movie_time) {
        this.movie_time = movie_time;
    }

    public int getMovie_count() {
        return movie_count;
    }

    public void setMovie_count(int movie_count) {
        this.movie_count = movie_count;
    }

    @Override
    public String toString() {
        return "电影编号：" + movie_id +
                ", 电影名称：" + movie_name +
                ", 电影类型：" + movie_type +
                ", 电影介绍：" + movie_introduction +
                ", 电影时长：" + movie_time+
                ",电影购票数量："+movie_count;
    }
}
