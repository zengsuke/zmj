package com.zmj.entity;

/**
 * 场厅
 */
public class Hall {
    private int hall_id;//id
    private String hall_number;//场厅
    private int cinema_id;//电影院
    private int hall_seat;//座位数量

    public Hall() {
    }

    public Hall(int hall_id, String hall_number, int cinema_id,int hall_seat) {
        this.hall_id = hall_id;
        this.hall_number = hall_number;
        this.cinema_id = cinema_id;
        this.hall_seat = hall_seat;
    }

    public int getHall_id() {
        return hall_id;
    }

    public void setHall_id(int hall_id) {
        this.hall_id = hall_id;
    }

    public String getHall_number() {
        return hall_number;
    }

    public void setHall_number(String hall_number) {
        this.hall_number = hall_number;
    }

    public int getCinema_id() {
        return cinema_id;
    }

    public void setCinema_id(int cinema_id) {
        this.cinema_id = cinema_id;
    }

    public int getHall_seat() {
        return hall_seat;
    }

    public void setHall_seat(int hall_seat) {
        this.hall_seat = hall_seat;
    }

    @Override
    public String toString() {
        return "场厅【" +
                "hall_id=" + hall_id +
                ", 场厅名称=" + hall_number +
                ", 电影院编号=" + cinema_id +
                ", 座位数量=" + hall_seat +
                '】';
    }
}
