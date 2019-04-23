package com.zmj.view;

import com.zmj.entity.Cinema;
import com.zmj.entity.Movie;
import com.zmj.service.CinemaService;
import com.zmj.service.MovieService;
import com.zmj.service.serviceImpl.CinemaServiceImpl;
import com.zmj.service.serviceImpl.MovieServiceImpl;
import com.zmj.util.InputUtil;

import java.util.Scanner;

public class CinemaView {
    private CinemaService cinemaService;
    private MovieService movieService;
    public CinemaView() {
        cinemaService=new CinemaServiceImpl();
        movieService=new MovieServiceImpl();
    }

    public void CinemaCome(){//进入电影院
        System.out.println("欢迎进入购票界面");
        Scanner input=new Scanner(System.in);
        while (true){
            try {
                if(cinemaService.allCinema().size()>0){
                    System.out.println("电影院有：");
                    for (Cinema c:cinemaService.allCinema()) {
                        System.out.println(c);
                    }
                }else
                    System.out.println("目前没有电影院！");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(movieService.findAll().size()>0){
                System.out.println("电影有：");
                for (Movie m:movieService.findAll()) {
                    System.out.println(m);
                }
            }else
                System.out.println("目前没有电影！");
            System.out.println("请输入你的选择：");
            System.out.println("1、选择电影院 2、选择电影 0、返回");
            int choice= InputUtil.getInputByInt(input);
            switch (choice){
                case 1:
                    choiceCinema();
                    break;
                case 2:
                    choiceMovie();
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
        System.out.println("请输入你想要去看的电影名字：");

    }

    public void choiceCinema(){//选择电影院
            System.out.println("请输入你想要去的电影院编号：");
            Cinema cinema=new Cinema();
            Scanner input=new Scanner(System.in);
            int cinema_id=InputUtil.getInputByInt(input);
            cinema.setCinema_id(cinema_id);
            try {
                if(cinemaService.findCinemaById(cinema)!=null){
                    System.out.println("选择成功，开始选择电影");
                }else
                    System.out.println("没有此编号的电影院，请重新选择！");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
