package com.zmj.view;

import com.zmj.entity.Movie;
import com.zmj.service.MovieService;
import com.zmj.service.serviceImpl.MovieServiceImpl;

public class MovieView {
    private MovieService movieService;

    public MovieView() {
        movieService=new MovieServiceImpl();
    }
    public void MovieCome(){
        System.out.println("欢迎来到电影浏览界面");
        if(movieService.findAll().size()!=0){
            for (Movie m:movieService.findAll()) {
                System.out.println(m);
            }
        }else {
            System.out.println("暂时没有电影可选择！");
        }

    }
}
