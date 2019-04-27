package com.zmj.entity;

/**
 * 票房
 */
public class BoxOffice {
    private int office_id;
    private int movie_id;
    private int movie_count;
    private double movie_money;

    public BoxOffice() {
    }

    public int getOffice_id() {
        return office_id;
    }

    public void setOffice_id(int office_id) {
        this.office_id = office_id;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public int getMovie_count() {
        return movie_count;
    }

    public void setMovie_count(int movie_count) {
        this.movie_count = movie_count;
    }

    public double getMovie_money() {
        return movie_money;
    }

    public void setMovie_money(double movie_money) {
        this.movie_money = movie_money;
    }

    @Override
    public String toString() {
        return "【票房:" +
                "票房id=" + office_id +
                ", 电影id=" + movie_id +
                ", 卖出票数=" + movie_count +
                ", 收益价=" + movie_money +
                '】';
    }
}
