package com.zmj.dao;

import com.zmj.entity.Movie;

import java.util.List;

public interface MovieDao {
    List<Movie> findAll();//查找所有

    Movie findMovieByName(String name) throws Exception;

    boolean insertMovie(Movie movie);

    boolean deleteMovie(String name);

    boolean updateMovieByType(String type,String name);

    boolean updateMovieByIntroduction(String introduction,String name);

    boolean updateMovieByTime(int time,String name);

    Movie findMovieById(int mid) throws Exception;

    boolean addTicket(int id);

    List<Movie> findMovieByType(int i) throws Exception;
}
