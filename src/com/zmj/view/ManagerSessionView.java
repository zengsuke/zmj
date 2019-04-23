package com.zmj.view;

import com.zmj.entity.Cinema;
import com.zmj.entity.Movie;
import com.zmj.entity.Session;
import com.zmj.service.CinemaService;
import com.zmj.service.HallService;
import com.zmj.service.MovieService;
import com.zmj.service.SessionService;
import com.zmj.service.serviceImpl.CinemaServiceImpl;
import com.zmj.service.serviceImpl.HallServiceImpl;
import com.zmj.service.serviceImpl.MovieServiceImpl;
import com.zmj.service.serviceImpl.SessionServiceImpl;
import com.zmj.util.InputUtil;
import com.zmj.util.TimeUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ManagerSessionView {
    private SessionService sessionService;
    private CinemaService cinemaService;
    private HallService hallService;
    private MovieService movieService;

    public ManagerSessionView() {
        sessionService = new SessionServiceImpl();
        cinemaService = new CinemaServiceImpl();
        hallService = new HallServiceImpl();
        movieService = new MovieServiceImpl();
    }

    public void ManagerSessionCome() {
        System.out.println("欢迎来到场次管理界面：");
        AllSession();
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("请输入你的选择：1、增加场次2、删除场次3、修改场次4、查看场次0、返回");
            int choice = InputUtil.getInputByInt(input);
            switch (choice) {
                case 1:
                    addSession();
                    break;
                case 2:
                    deleteSession();
                    break;
                case 3:
                    break;
                case 4:
                    AllSession();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("输入出错误请重新输入！");
                    break;


            }
        }

    }

    private void deleteSession() {
        ManagerCinemaView managerCinemaView = new ManagerCinemaView();
        managerCinemaView.ManagerFindAll();
        Scanner input = new Scanner(System.in);
        while (true){
            System.out.println("请输入你的选择：1、删除所有的场次2、删除个别场次0、返回");
            int choice = InputUtil.getInputByInt(input);
            switch (choice){
                case 1:
                    sessionService.deleteAllSession();
                    break;
                case 2:
                    break;
                case 0:
                    return;
                default:
                    System.out.println("输入有误请重新输入！");
                    input.nextLine();
                    break;
            }
        }
    }

    private void addSession() {
        ManagerCinemaView managerCinemaView = new ManagerCinemaView();
        managerCinemaView.ManagerFindAll();
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("请输入电影院编号：");
            int cid = InputUtil.getInputByInt(input);
            Cinema cinema = new Cinema();
            cinema.setCinema_id(cid);
            try {
                if (cinemaService.findCinemaById(cinema) != null) {//判断是否存在此电影院
                    ManagerMovieView managerMovieView = new ManagerMovieView();
                    managerMovieView.findAllMovie();
                    System.out.println("请输入要安排的电影名称：");
                    String mname = InputUtil.getInputByString(input);
                    if (movieService.findMovieByName(mname) != null) {//判断是否存在此电影
                        Movie movie = movieService.findMovieByName(mname);

                        System.out.println("请输入场厅：");
                        int hid = InputUtil.getInputByInt(input);
                        if (hallService.findHallByExample(cid, hid).size()>0) {//判断此电影院此否有此影厅
                            Session session = new Session();
                            int seat=hallService.findHallByExample(cid, hid).get(0).getHall_seat();
                            session.setMovie_id(movie.getMovie_id());
                            session.setCinema_id(cid);
                            session.setHall_id(hid);
                            session.setSeat_number(seat);
                            Date datebegin = TimeUtil.compareSystemTime();//输入开始时间
                            session.setBegin_time(datebegin);
                            if (findSessionByExample(session)) {//判断是否此时间有安排其他电影在此影厅
                                Date dateend = TimeUtil.comparebeginTime(datebegin);//输入结束时间
                                session.setEnd_time(dateend);
                                System.out.println("请输入票价：");
                                Double price = InputUtil.getInputByDouble(input);
                                session.setMovie_price(price);
                                sessionService.insertSession(session);//添加
                            } else
                                System.out.println("在这个电影院，此场厅，此时间已有安排其他电影！");

                        } else
                            System.out.println("此电影院没有这个场厅！");
                        break;
                    } else
                        System.out.println("不存在此电影名！");
                    break;
                } else
                    System.out.println("不存在此电影院！");
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    public boolean findSessionByExample(Session session) {//查找是否存在此场次
        boolean result = false;//不能安排
        try {
            result = sessionService.findSessionByExample(session);//可以安排则为true
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public void AllSession() {//查找所有场次
        if (sessionService.findAllSession().size() > 0) {
            for (Session s : sessionService.findAllSession()) {
                System.out.println(s);
            }
        }//先查找到所有场次
        else
            System.out.println("目前没有布置任何场次！");
    }
}
