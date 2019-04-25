package com.zmj.view;

import com.zmj.entity.Card;
import com.zmj.entity.Hall;
import com.zmj.entity.Session;
import com.zmj.entity.Ticket;
import com.zmj.service.*;
import com.zmj.service.serviceImpl.*;
import com.zmj.util.InputUtil;


import java.util.List;
import java.util.Scanner;

public class CustomerTiketView {
    private SessionService sessionService;
    private TicketService ticketService;
    private MovieService movieService;
    private HallService hallService;
    private CardService cardService;
    private int[][] number;
    private List<Ticket> tickets;
    private int user_id = MainView.id;

    public CustomerTiketView() {
        sessionService = new SessionServiceImpl();
        ticketService = new TicketServiceImpl();
        movieService = new MovieServiceImpl();
        hallService = new HallServiceImpl();
        cardService = new CardServiceImpl();
    }

    public void TicketCome(int sid) {
        allSeat(sid);
        System.out.println("请选择座位：");
        List<Integer> list = InputUtil.getTrueSeat();//获取正确位置格式
        Ticket ticket = new Ticket();
        ticket.setTicket_line(list.get(0) - 1);
        ticket.setTicket_colume(list.get(1) - 1);
        if (0 <= ticket.getTicket_line() && ticket.getTicket_line() < number.length
                && 0 <= ticket.getTicket_colume() && ticket.getTicket_colume() < number[ticket.getTicket_line()].length) {
            if (compareTicket(ticket)) {
                System.out.println("已售位置，不可再被购买！");
            } else {
                List<Session> sessions = null;
                try {
                    sessions = sessionService.findSeatById(sid);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                double price = sessions.get(0).getMovie_price();
                ticket.setTicket_price(price);
                ticket.setSession_id(sid);
                ticket.setUser_id(user_id);
                payType(ticket);
            }
        } else
            System.out.println("您输入的位置超出原有位置！");

    }

    private void payType(Ticket ticket) {
        System.out.println("请选择你所要支付的方式：");
        System.out.println("1、支付宝 2、影城卡(6折)0、返回");
        Scanner input = new Scanner(System.in);
        int choice = InputUtil.getInputByInt(input);
        switch (choice) {
            case 1:
                payBymoney(ticket);
                break;
            case 2:
                payByCard(ticket);
                break;
            case 0:
                return;
            default:
                System.out.println("输入错误请重新输入！");
                break;
        }
    }

    private void payByCard(Ticket ticket) {
        try {
            if (cardService.findCardById(user_id).size() > 0) {
                Card card=cardService.findCardById(user_id).get(0);
                Session session = sessionService.findSeatById(ticket.getSession_id()).get(0);
                double price=session.getMovie_price()*0.6;
                if(card.getUser_money()-price>0){
                    ticket.setTicket_type(1);
                    ticketService.addTicket(ticket);
                    System.out.println("此次消费："+price+"元");
                    movieService.addTicket(session.getMovie_id());
                    sessionService.reduceTicket(ticket.getSession_id());
                    cardService.reduceCardById(price, user_id);
                }else
                    System.out.println("余额不足！");

            }else
                System.out.println("此账户没有办理影城卡");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void payBymoney(Ticket ticket) {
        ticket.setTicket_type(0);
        ticketService.addTicket(ticket);
        try {
            int mid = sessionService.findSeatById(ticket.getSession_id()).get(0).getMovie_id();
            movieService.addTicket(mid);
            sessionService.reduceTicket(ticket.getSession_id());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private boolean compareTicket(Ticket ticket) {//与售出位置进行比较，看选入的是否有此座位
        if (tickets.size() > 0) {
            for (int i = 0; i < tickets.size(); i++) {
                if (tickets.get(i).getTicket_line() == ticket.getTicket_line() && tickets.get(i).getTicket_colume() == ticket.getTicket_colume()) {
                    return true;//此位置存在了
                }
            }
        }
        return false;
    }

    private void allSeat(int sid) {//展示座位
        try {
            List<Session> sessions = sessionService.findSeatById(sid);
            List<Hall> hall = hallService.findHallById(sessions.get(0).getHall_id());
            int seat_number = hall.get(0).getHall_seat();
            int colume = seat_number / 10;
            number = new int[10][colume];
            tickets = ticketService.findTicketBySid(sid);
            for (int i = 0; i < 10; i++) {//行
                for (int j = 0; j < number[i].length; j++) {//列
                    if (tickets.size() > 0) {//是否被购买了
                        for (int k = 0; k < tickets.size(); k++) {
                            if (tickets.get(k).getTicket_line() == i && tickets.get(k).getTicket_colume() == j) {
                                System.out.print("【" + "已售" + "】");
                            } else
                                System.out.print("（" + (i + 1) + "," + (j + 1) + "）");
                        }
                    } else
                        System.out.print("（" + (i + 1) + "," + (j + 1) + "）");
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
