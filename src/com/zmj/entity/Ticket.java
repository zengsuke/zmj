package com.zmj.entity;

/**
 * 购票记录
 */
public class Ticket {
    private int ticket_id;
    private int session_id;
    private int user_id;
    private int ticket_type;//0，普通，1，有卡的
    private int ticket_line;//行
    private int ticket_colume;//列
    private double ticket_price;//价格

    public Ticket() {
    }

    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public int getSession_id() {
        return session_id;
    }

    public void setSession_id(int session_id) {
        this.session_id = session_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTicket_type() {
        return ticket_type;
    }

    public void setTicket_type(int ticket_type) {
        this.ticket_type = ticket_type;
    }



    public double getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(double ticket_price) {
        this.ticket_price = ticket_price;
    }

    public int getTicket_line() {
        return ticket_line;
    }

    public void setTicket_line(int ticket_line) {
        this.ticket_line = ticket_line;
    }

    public int getTicket_colume() {
        return ticket_colume;
    }

    public void setTicket_colume(int ticket_colume) {
        this.ticket_colume = ticket_colume;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticket_id=" + ticket_id +
                ", session_id=" + session_id +
                ", user_id=" + user_id +
                ", ticket_type=" + ticket_type +
                ", ticket_line=" + ticket_line +
                ", ticket_colume=" + ticket_colume +
                ", ticket_price=" + ticket_price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (ticket_line != ticket.ticket_line) return false;
        return ticket_colume == ticket.ticket_colume;
    }

    @Override
    public int hashCode() {
        int result = ticket_line;
        result = 31 * result + ticket_colume;
        return result;
    }
}
