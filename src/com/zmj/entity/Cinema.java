package com.zmj.entity;

/**
 * 电影院
 */
public class Cinema {
    private int cinema_id;//id
    private String cinema_name;//电影院的名字
    private String cinema_address;//电影院地址

    public Cinema() {
    }

    public Cinema(int cinema_id, String cinema_name, String cinema_address) {
        this.cinema_id = cinema_id;
        this.cinema_name = cinema_name;
        this.cinema_address = cinema_address;
    }

    public int getCinema_id() {
        return cinema_id;
    }

    public void setCinema_id(int cinema_id) {
        this.cinema_id = cinema_id;
    }

    public String getCinema_name() {
        return cinema_name;
    }

    public void setCinema_name(String cinema_name) {
        this.cinema_name = cinema_name;
    }

    public String getCinema_address() {
        return cinema_address;
    }

    public void setCinema_address(String cinema_address) {
        this.cinema_address = cinema_address;
    }

    @Override
    public String toString() {
        return "电影院编号：" + cinema_id +
                ",电影院名字：" + cinema_name +
                ",电影院地址：" + cinema_address;
    }
}
