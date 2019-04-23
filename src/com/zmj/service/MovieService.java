package com.zmj.service;

import com.zmj.entity.Movie;

import java.util.List;

public interface MovieService {
    public List<Movie> findAll();//查找所有电影

    public Movie findMovieByName(String name) throws Exception;//查找是否存在此电影

    public boolean insertMovie(Movie movie);

    public boolean deleteMovie(String name);

    public boolean updateMovieByType(String type,String name);

    public boolean updateMovieByIntroduction(String introduction,String name);

    public boolean updateMovieByTime(int time,String name);
}
