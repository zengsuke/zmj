package com.zmj.entity;

public class Card {
    private int card_id;
    private int user_id;
    private double user_money;

    public Card(int card_id, int user_id, double user_money) {
        this.card_id = card_id;
        this.user_id = user_id;
        this.user_money = user_money;
    }

    public Card() {
    }

    public int getCard_id() {
        return card_id;
    }

    public void setCard_id(int card_id) {
        this.card_id = card_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public double getUser_money() {
        return user_money;
    }

    public void setUser_money(double user_money) {
        this.user_money = user_money;
    }

    @Override
    public String toString() {
        return "电影卡{" +
                "卡号=" + card_id +
                ", 用户编号=" + user_id +
                ", 所剩金额=" + user_money +
                '}';
    }
}
