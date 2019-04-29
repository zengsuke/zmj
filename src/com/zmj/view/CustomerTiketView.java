package com.zmj.view;

import com.zmj.entity.*;
import com.zmj.service.*;
import com.zmj.service.serviceImpl.*;
import com.zmj.util.InputUtil;


import java.util.*;

/**
 * 购票界面
 */
public class CustomerTiketView {
    private SessionService sessionService;
    private TicketService ticketService;
    private MovieService movieService;
    private HallService hallService;
    private CardService cardService;
    private BoxOfficeService boxOfficeService;
    private UserService userService;
    private int lottery_id;//抽奖
    private int[][] number;//座位
    private List<Ticket> tickets;//已购的位置
    private int user_id = MainView.id;

    public CustomerTiketView(int lottery_id) {
        sessionService = new SessionServiceImpl();
        ticketService = new TicketServiceImpl();
        movieService = new MovieServiceImpl();
        hallService = new HallServiceImpl();
        cardService = new CardServiceImpl();
        userService = new UserServiceImpl();
        boxOfficeService = new BoxOfficeServiceImpl();
        this.lottery_id = lottery_id;
    }

    /**
     * @param sid
     */
    public void TicketCome(int sid) {
        allSeat(sid);
        try {
            if (sessionService.findSeatById(sid).get(0).getSeat_number() <= 0) {//查看场次中的位置是否还剩余
                System.out.println("售空！");
            } else {
                while (true) {
                    System.out.println("请选择座位：");
                    List<Integer> list = InputUtil.getTrueSeat();//获取正确位置格式
                    Ticket ticket = new Ticket();
                    ticket.setTicket_line(list.get(0) - 1);
                    ticket.setTicket_colume(list.get(1) - 1);
                    if (0 <= ticket.getTicket_line() && ticket.getTicket_line() < number.length
                            && 0 <= ticket.getTicket_colume() && ticket.getTicket_colume() < number[ticket.getTicket_line()].length) {//判断所选位置是否越界
                        if (compareTicket(ticket)) {//查看此位置是否已被购买，返回true则说明被购买
                            System.out.println("已售位置，不可再被购买！");
                        } else {//添加至影票表，场次中的座位数量减一，电影的卖出票数加一
                            List<Session> sessions = sessionService.findSeatById(sid);
                            int type = movieService.findMovieById(sessions.get(0).getMovie_id()).getMovie_type();
                            double price = sessions.get(0).getMovie_price();
                            ticket.setTicket_type(type);
                            ticket.setTicket_price(price);
                            ticket.setSession_id(sid);
                            ticket.setUser_id(user_id);
                            payType(ticket);
                            break;
                        }
                    } else
                        System.out.println("您输入的位置超出原有位置！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 购买使用的类型
     *
     * @param ticket
     */
    private void payType(Ticket ticket) {
        while (true) {
            System.out.println("请选择你所要支付的方式：");
            System.out.println("1、支付宝 2、影城卡(6折)0、返回");
            Scanner input = new Scanner(System.in);
            int choice = InputUtil.getInputByInt(input);
            switch (choice) {
                case 1:
                    pay(ticket, 1);
                    return;
                case 2:
                    pay(ticket, 0);
                    return;
                case 0:
                    return;
                default:
                    System.out.println("输入错误请重新输入！");
                    break;
            }
        }
    }

    private void pay(Ticket ticket, int i) {
        try {
            if (i == 0) {//影城卡购买
                if (cardService.findCardById(user_id).size() > 0) {
                    Card card = cardService.findCardById(user_id).get(0);
                    Session session = sessionService.findSeatById(ticket.getSession_id()).get(0);
                    double price = 0;
                    if (lottery_id == 1) {
                        price = 0;//免单
                    } else {
                        price = session.getMovie_price() * 0.6;
                    }
                    if (card.getUser_money() - price > 0) {//查看是否还有钱买的起
                        ticketService.addTicket(ticket);
                        System.out.println("此次消费：" + price + "元");
                        lottery_id = 0;//清空一次免单机会
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
            } else {//余额购买
                if (userService.finduserById(user_id) == null) {
                    System.out.println("内部出现错误！");
                } else {
                    if (userService.finduserById(user_id).getUser_money() - ticket.getTicket_price() < 0) {
                        System.out.println("账户余额不足，请充值！");
                    } else {
                        ticketService.addTicket(ticket);
                        Session session = sessionService.findSeatById(ticket.getSession_id()).get(0);
                        double price = 0;
                        if (lottery_id == 1) {
                            price = 0;//免单
                        } else {
                            price = ticket.getTicket_price();
                        }
                        System.out.println("此次消费：" + price + "元");
                        lottery_id = 0;//清空一次免单机会
                        movieService.addTicket(session.getMovie_id());
                        sessionService.reduceTicket(ticket.getSession_id());
                        userService.updateUser(userService.finduserById(user_id).getUser_money() - price,user_id);
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
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 与售出位置进行比较，看选入的是否有此座位
     *
     * @param ticket
     * @return
     */
    private boolean compareTicket(Ticket ticket) {
        if (tickets.size() > 0) {
            for (int i = 0; i < tickets.size(); i++) {
                if (tickets.get(i).getTicket_line() == ticket.getTicket_line() && tickets.get(i).getTicket_colume() == ticket.getTicket_colume()) {
                    return true;//此位置存在了
                }
            }
        }
        return false;
    }

    /**
     * 展示座位
     *
     * @param sid
     */
    private void allSeat(int sid) {
        try {
            List<Session> sessions = sessionService.findSeatById(sid);
            List<Hall> hall = hallService.findHallById(sessions.get(0).getHall_id());
            int seat_number = hall.get(0).getHall_seat();
            int colume = seat_number / 10;//十排
            number = new int[10][colume];
            tickets = ticketService.findTicketBySid(sid);
            Map<Integer, Ticket> seat = new HashMap<Integer, Ticket>();//用hashmap存储
            for (int i = 0; i < tickets.size(); i++) {
                seat.put(i, tickets.get(i));//将购买了此场次的位置存储在hashmap中
            }
            for (int i = 0; i < 10; i++) {//行
                for (int j = 0; j < number[i].length; j++) {//列
                    if (tickets.size() > 0) {//此场次是否有票购买了
                        Ticket ticket = new Ticket();
                        ticket.setTicket_line(i);
                        ticket.setTicket_colume(j);
                        if (seat.containsValue(ticket)) {
                            //比较是否存在，在类中重写equals hashcode方法
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
