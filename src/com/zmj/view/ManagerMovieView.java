package com.zmj.view;

import com.zmj.entity.Movie;
import com.zmj.service.MovieService;
import com.zmj.service.serviceImpl.MovieServiceImpl;
import com.zmj.util.InputUtil;

import java.util.Scanner;

public class ManagerMovieView {
    private MovieService movieService;

    public ManagerMovieView() {
        movieService=new MovieServiceImpl();
    }

    public void ManagerMovieCome(){
        System.out.println("欢迎管理员来到电影浏览界面");
        Scanner input=new Scanner(System.in);
        while (true){
            System.out.println("1、添加电影2、删除电影3、修改电影4、查找所有0、返回");
            System.out.println("请输入你的选择：");
            int choice= InputUtil.getInputByInt(input);
            switch (choice){
                case 1:
                    insertMovie();
                    break;
                case 2:
                    deleteMovie();
                    break;
                case 3:
                    updateMovie();
                    break;
                case 4:
                    findAllMovie();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("输入有误！");
                    break;
            }
        }

    }

    public void findAllMovie() {//查找所有
        if(movieService.findAll().size()>0){
            System.out.println("目前有的电影：");
            for (Movie m:movieService.findAll()) {
                System.out.println(m);
            }
        }else
            System.out.println("目前没电影可看！");

    }

    public void updateMovie() {//修改电影信息
        Scanner input=new Scanner(System.in);
        findAllMovie();
        System.out.println("请输入你要修改的电影：");
        String name=InputUtil.getInputByString(input);
        try {
            if(movieService.findMovieByName(name)!=null){
                System.out.println(movieService.findMovieByName(name).toString());
                while (true){
                    System.out.println("修改什么？");
                    System.out.println("1、类型 2、介绍3、时长0、返回");
                    int choice=InputUtil.getInputByInt(input);
                    switch (choice){
                        case 1:
                            System.out.println("请输入修改后的类型：");
                            String type=InputUtil.getInputByString(input);
                            if(movieService.updateMovieByType(type,name)){
                                System.out.println("修改成功");
                            }else
                                System.out.println("修改失败！");
                            break;
                        case 2:System.out.println("请输入修改后的介绍：");
                            String introduction=InputUtil.getInputByString(input);
                            if(movieService.updateMovieByIntroduction(introduction,name)){
                                System.out.println("修改成功");
                            }else
                                System.out.println("修改失败！");
                            break;
                        case 3:System.out.println("请输入修改后的时长：");
                            int time=InputUtil.getInputByInt(input);
                            if(movieService.updateMovieByTime(time,name)){
                                System.out.println("修改成功");
                            }else
                                System.out.println("修改失败！");
                            break;
                        case 0:
                            return;
                        default:
                            System.out.println("输入有误请重新输入！");
                            break;
                    }
                }
            }else
                System.out.println("不存在此电影，请重新选择！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteMovie() {
        findAllMovie();
        Scanner input=new Scanner(System.in);
        System.out.println("请输入你要删除的电影：");
        String name=InputUtil.getInputByString(input);
        try {
            if(movieService.findMovieByName(name)!=null){
                System.out.println(movieService.findMovieByName(name).toString());
                System.out.println("确定要删除吗？");
                System.out.println("1、删除 2、不删除");
                int choice=InputUtil.getInputByInt(input);
                switch (choice){
                    case 1:
                        if(movieService.deleteMovie(name)){
                            System.out.println("删除成功");
                        }else
                            System.out.println("删除失败！");
                        break;
                    case 2:
                        return;
                    default:
                        System.out.println("输入有误请重新输入！");
                        break;
                }
            }else
                System.out.println("没有此电影。。。");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void insertMovie(){
        Scanner input=new Scanner(System.in);
        System.out.println("请输入你要添加电影的名字：");
        String name=InputUtil.getInputByString(input);
        try {
            if(movieService.findMovieByName(name)==null){
                System.out.println("请输入你要添加电影的类型：");
                String type=InputUtil.getInputByString(input);
                System.out.println("请输入你要添加电影的内容介绍：");
                String introduction=InputUtil.getInputByString(input);
                System.out.println("请输入你要添加电影的时长(m)：");
                int time=InputUtil.getInputByInt(input);
                Movie movie=new Movie();
                movie.setMovie_name(name);
                movie.setMovie_type(type);
                movie.setMovie_introduction(introduction);
                movie.setMovie_time(time);
                if(movieService.insertMovie(movie)){
                    System.out.println("添加成功");
                }else
                    System.out.println("添加失败！");
            }else
                System.out.println("此电影已存在，请重新选择！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
