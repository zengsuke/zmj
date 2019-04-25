package com.zmj.view;

import com.zmj.entity.*;
import com.zmj.service.*;
import com.zmj.service.serviceImpl.*;
import com.zmj.util.InputUtil;


import java.util.*;

public class CustomerTiketView {
    private SessionService sessionService;
    private TicketService ticketService;
    private MovieService movieService;
    private HallService hallService;
    private CardService cardService;
    private BoxOfficeService boxOfficeService;
    private int[][] number;
    private List<Ticket> tickets;
    private int user_id = MainView.id;

    public CustomerTiketView() {
        sessionService = new SessionServiceImpl();
        ticketService = new TicketServiceImpl();
        movieService = new MovieServiceImpl();
        hallService = new HallServiceImpl();
        cardService = new CardServiceImpl();
        boxOfficeService = new BoxOfficeServiceImpl();
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
                try {
                    List<Session> sessions = sessionService.findSeatById(sid);
                    int type = movieService.findMovieById(sessions.get(0).getMovie_id()).getMovie_type();
                    double price = sessions.get(0).getMovie_price();
                    ticket.setTicket_type(type);
                    ticket.setTicket_price(price);
                    ticket.setSession_id(sid);
                    ticket.setUser_id(user_id);
                    payType(ticket);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } else
            System.out.println("您输入的位置超出原有位置！");

    }

    private void payType(Ticket ticket) {
        while (true){
            System.out.println("请选择你所要支付的方式：");
            System.out.println("1、支付宝 2、影城卡(6折)0、返回");
            Scanner input = new Scanner(System.in);
            int choice = InputUtil.getInputByInt(input);
            switch (choice) {
                case 1:
                    payBymoney(ticket);
                    return;
                case 2:
                    payByCard(ticket);
                    return;
                case 0:
                    return;
                default:
                    System.out.println("输入错误请重新输入！");
                    break;
            }
        }
    }

    private void payByCard(Ticket ticket) {
        try {
            if (cardService.findCardById(user_id).size() > 0) {
                Card card = cardService.findCardById(user_id).get(0);
                Session session = sessionService.findSeatById(ticket.getSession_id()).get(0);
                double price = session.getMovie_price() * 0.6;
                if (card.getUser_money() - price > 0) {
                    ticketService.addTicket(ticket);
                    System.out.println("此次消费：" + price + "元");
                    movieService.addTicket(session.getMovie_id());
                    sessionService.reduceTicket(ticket.getSession_id());
                    cardService.reduceCardById(price, user_id);
                    Movie movie = movieService.findMovieById(session.getMovie_id());
                    if (boxOfficeService.findOfficeByMid(movie.getMovie_id())) {//查找效益中是否有此电影
                        if (boxOfficeService.updateOffice(movie.getMovie_id(), 1, session.getMovie_price())) {
                            System.out.println();
                        } else
                            System.out.println("后台系统出错，为将价格加入效益表中！");
                    } else {//没有就进行添加
                        if (boxOfficeService.insertOffice(movie.getMovie_id(), 1, session.getMovie_price())) {
                            System.out.println();
                        } else
                            System.out.println("后台系统出错，为将此次效益加入效益表中！");
                    }

                } else
                    System.out.println("余额不足！");

            } else
                System.out.println("此账户没有办理影城卡");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void payBymoney(Ticket ticket) {
        ticketService.addTicket(ticket);
        try {
            Session session = sessionService.findSeatById(ticket.getSession_id()).get(0);
            double price = session.getMovie_price();
            System.out.println("此次消费：" + price + "元");
            movieService.addTicket(session.getMovie_id());
            sessionService.reduceTicket(ticket.getSession_id());
            Movie movie = movieService.findMovieById(session.getMovie_id());
            if (boxOfficeService.findOfficeByMid(session.getMovie_id())) {//查找效益中是否有此电影
                if (boxOfficeService.updateOffice(session.getMovie_id(), 1, session.getMovie_price())) {
                    System.out.println();
                } else
                    System.out.println("后台系统出错，为将价格加入效益表中！");
            } else {//没有就进行添加
                if (boxOfficeService.insertOffice(movie.getMovie_id(), 1, session.getMovie_price())) {
                    System.out.println();
                } else
                    System.out.println("后台系统出错，为将此次效益加入效益表中！");
            }
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
            Map<Integer, Ticket> seat = new HashMap<Integer, Ticket>();//用hashmap存储
            for (int i = 0; i < tickets.size(); i++) {
                seat.put(i,tickets.get(i));
            }
            for (int i = 0; i < 10; i++) {//行
                for (int j = 0; j < number[i].length; j++) {//列
                    if (tickets.size() > 0) {//是否被购买了
                        Ticket ticket=new Ticket();
                        ticket.setTicket_line(i);
                        ticket.setTicket_colume(j);
                        if (seat.containsValue(ticket)) {//比较是否存在，在类中重写equals hashcode方法
                            System.out.print("【" + "已售" + "】");
                        } else
                            System.out.print("（" + (i + 1) + "," + (j + 1) + "）");
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
