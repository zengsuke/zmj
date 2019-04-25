package com.zmj.service;

import com.zmj.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();//查找所有电影

    Movie findMovieByName(String name) throws Exception;//查找是否存在此电影

    boolean insertMovie(Movie movie);//添加

    boolean deleteMovie(String name);//删除

    boolean updateMovieByType(String type,String name);//修改

    boolean updateMovieByIntroduction(String introduction,String name);//修改

    boolean updateMovieByTime(int time,String name);//修改时间

    Movie findMovieById(int mid) throws Exception;//查找

    void addTicket(int id);//加票

    List<Movie> findMovieByType(int i) throws Exception;//种类
}
