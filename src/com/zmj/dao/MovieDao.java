package com.zmj.dao;

import com.zmj.entity.Movie;

import java.util.List;

public interface MovieDao {
    public List<Movie> findAll();//查找所有

    public Movie findMovieByName(String name) throws Exception;

    public boolean insertMovie(Movie movie);

    public boolean deleteMovie(String name);

    public boolean updateMovieByType(String type,String name);

    public boolean updateMovieByIntroduction(String introduction,String name);

    public boolean updateMovieByTime(int time,String name);
}
