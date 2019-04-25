package com.zmj.service.serviceImpl;

import com.zmj.dao.MovieDao;
import com.zmj.dao.daoImpl.MovieDaoImpl;
import com.zmj.entity.Movie;
import com.zmj.service.MovieService;

import java.util.List;

public class MovieServiceImpl implements MovieService {
    MovieDao movieDao;

    public MovieServiceImpl() {
        movieDao=new MovieDaoImpl();
    }

    @Override
    public List<Movie> findAll() {
       return movieDao.findAll();
    }

    @Override
    public Movie findMovieByName(String name) throws Exception {
        return movieDao.findMovieByName(name);
    }

    @Override
    public boolean insertMovie(Movie movie) {
        return movieDao.insertMovie(movie);
    }

    @Override
    public boolean deleteMovie(String name) {
        return movieDao.deleteMovie(name);
    }

    @Override
    public boolean updateMovieByType(String type,String name) {
        return movieDao.updateMovieByType(type,name);

    }

    @Override
    public boolean updateMovieByIntroduction(String introduction,String name) {
        return movieDao.updateMovieByIntroduction(introduction,name);
    }

    @Override
    public boolean updateMovieByTime(int time,String name) {
        return movieDao.updateMovieByTime(time,name);
    }

    @Override
    public Movie findMovieById(int mid) throws Exception {
        return movieDao.findMovieById(mid);
    }

    @Override
    public void addTicket(int id) {
        if(movieDao.addTicket(id)){
            System.out.println("");
        }else
            System.out.println("后台增加电影票房数量有误！");
    }

    @Override
    public List<Movie> findMovieByType(int i) throws Exception {
        return movieDao.findMovieByType(i);
    }


}
