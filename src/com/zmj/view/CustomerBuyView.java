package com.zmj.view;

import com.zmj.entity.Cinema;
import com.zmj.entity.Movie;
import com.zmj.entity.Session;
import com.zmj.service.CinemaService;
import com.zmj.service.MovieService;
import com.zmj.service.SessionService;
import com.zmj.service.serviceImpl.*;
import com.zmj.util.InputUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CustomerBuyView {
    private int user_id = MainView.id;
    private CinemaService cinemaService;
    private MovieService movieService;
    private SessionService sessionService;
    private CustomerTiketView customerTiketView;
    public CustomerBuyView() {
        cinemaService = new CinemaServiceImpl();
        movieService = new MovieServiceImpl();
        sessionService = new SessionServiceImpl();
        customerTiketView=new CustomerTiketView();
    }


    public void buy() {
        while (true) {
            System.out.println("请输入你的选择：");
            System.out.println("1、选择电影2、查看热门电影0、返回");
            Scanner input = new Scanner(System.in);
            int choice = InputUtil.getInputByInt(input);
            switch (choice) {
                case 1:
                    choiceMovie();
                    break;
                case 2:
                    guessMovie();//前三
                    break;
                case 0:
                    return;
                default:
                    System.out.println("输入有误，请重新输入！");
                    break;
            }
        }
    }

    private void choiceMovie() {
        AllMovie();
        System.out.println("请输入你想要去看的电影编号：");//movie
        Scanner input = new Scanner(System.in);
        int mid = InputUtil.getInputByInt(input);
        try {
            if (movieService.findMovieById(mid) != null) {
                AllCinema();
                System.out.println("请输入你想要去的电影院编号：");//cinema
                Cinema cinema = new Cinema();
                Scanner input1 = new Scanner(System.in);
                int cinema_id = InputUtil.getInputByInt(input1);
                cinema.setCinema_id(cinema_id);
                try {
                    if (cinemaService.findCinemaById(cinema) != null) {
                        choiceHall(cinema_id,mid);
                    } else
                        System.out.println("没有此编号的电影院，请重新选择！");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else
                System.out.println("目前没有此电影，请重新选择！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void choiceHall(int cid, int mid) {//选择场次
        try {
            if(sessionService.findSessionByIdMid(cid,mid).size()>0){
                for (Session s:sessionService.findSessionByIdMid(cid,mid)) {
                    System.out.println(s);
                }
                System.out.println("请选择你想要的场次编号：");
                Scanner input=new Scanner(System.in);
                int sid=InputUtil.getInputByInt(input);
                if(sessionService.findSessionById(sid)){
                    customerTiketView.TicketCome(sid);
                }else
                    System.out.println("此场次相关内容不存在！");
            }else
                System.out.println("此电影院没有安排此电影的场次！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AllCinema() {
        try {
            if (cinemaService.allCinema().size() > 0) {
                System.out.println("电影院有：");
                for (Cinema c : cinemaService.allCinema()) {
                    System.out.println(c);
                }
            } else
                System.out.println("目前没有电影院！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AllMovie() {
        if (movieService.findAll().size() > 0) {
            System.out.println("电影有：");
            for (Movie m : movieService.findAll()) {
                System.out.println(m);
            }
        } else
            System.out.println("目前没有电影！");
    }

    public void guessMovie() {
        if (movieService.findAll().size() > 0) {
            System.out.println("猜你可能喜欢的电影有：");
            List<Movie> movies = movieService.findAll();
            movies.sort(new Comparator<Movie>() {
                @Override
                public int compare(Movie o1, Movie o2) {
                    return o2.getMovie_count() - o1.getMovie_count();//降序
                }
            });
            for (int i = 0; i < 3; i++) {
                System.out.println(movies.get(i).toString());
            }
        } else
            System.out.println("目前没有电影！");
    }
}
