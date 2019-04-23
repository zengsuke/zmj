package com.zmj.entity;

public class Ticket {
    private int ticket_id;
    private int session_id;
    private int user_id;
    private double ticket_type;
    private int seat_number;

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

    public double getTicket_type() {
        return ticket_type;
    }

    public void setTicket_type(double ticket_type) {
        this.ticket_type = ticket_type;
    }

    public int getSeat_number() {
        return seat_number;
    }

    public void setSeat_number(int seat_number) {
        this.seat_number = seat_number;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticket_id=" + ticket_id +
                ", session_id=" + session_id +
                ", user_id=" + user_id +
                ", ticket_type=" + ticket_type +
                ", seat_number=" + seat_number +
                '}';
    }
}
