package com.zmj.entity;


import java.util.Date;

/**
 * 场次
 */
public class Session {
    private int session_id;
    private int movie_id;
    private int cinema_id;
    private int hall_id;
    private double movie_price;//价格
    private int seat_number;//座位
    private Date begin_time;//开始时间
    private Date end_time;//结束时间
    private int session_state;//状态

    public Session() {
    }

    public int getSession_id() {
        return session_id;
    }

    public void setSession_id(int session_id) {
        this.session_id = session_id;
    }

    public int getCinema_id() {
        return cinema_id;
    }

    public void setCinema_id(int cinema_id) {
        this.cinema_id = cinema_id;
    }

    public Date getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(Date begin_time) {
        this.begin_time = begin_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public int getHall_id() {
        return hall_id;
    }

    public void setHall_id(int hall_id) {
        this.hall_id = hall_id;
    }

    public double getMovie_price() {
        return movie_price;
    }

    public void setMovie_price(double movie_price) {
        this.movie_price = movie_price;
    }

    public int getSeat_number() {
        return seat_number;
    }

    public void setSeat_number(int seat_number) {
        this.seat_number = seat_number;
    }

    public int getSession_state() {
        return session_state;
    }

    public void setSession_state(int session_state) {
        this.session_state = session_state;
    }

    @Override
    public String toString() {
        return "场次【" +
                "场次id=" + session_id +
                ", 电影id=" + movie_id +
                ", 电影院id=" + cinema_id +
                ", 场厅id=" + hall_id +
                ", 票价=" + movie_price +
                ", 座位数量=" + seat_number +
                ", 开始时间=" + begin_time +
                ", 结束时间=" + end_time +
                "】";
    }
}
