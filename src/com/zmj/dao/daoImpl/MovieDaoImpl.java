package com.zmj.dao.daoImpl;

import com.zmj.dao.BaseDao;
import com.zmj.dao.MovieDao;
import com.zmj.entity.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieDaoImpl implements MovieDao {
    @Override
    public List<Movie> findAll() {
        String sql="select * from dvd_movie";
        Class<Movie> cls=Movie.class;
        return BaseDao.executeAll(sql,cls);
    }

    @Override
    public Movie findMovieByName(String name) throws Exception {
        String sql="select * from dvd_movie where movie_name=?";
        List<Object> list=new ArrayList<>();
        list.add(name);
        Class<Movie> cls=Movie.class;
        List<Movie> movies=BaseDao.executeQuery(sql,cls,list);
        if(movies.size()!=0){
            return movies.get(0);
        }else
            return null;
    }

    @Override
    public boolean insertMovie(Movie movie) {
        String sql="insert into dvd_movie(movie_name,movie_type,movie_introduction,movie_time)values(?,?,?,?)";
        List<Object> list=new ArrayList<>();
        list.add(movie.getMovie_name());
        list.add(movie.getMovie_type());
        list.add(movie.getMovie_introduction());
        list.add(movie.getMovie_time());
        return BaseDao.excuteUpdate(sql,list);
    }

    @Override
    public boolean deleteMovie(String name) {
        String sql="delete from dvd_movie where movie_name=?";
        List<Object> list=new ArrayList<>();
        list.add(name);
        return BaseDao.excuteUpdate(sql,list);
    }

    @Override
    public boolean updateMovieByType(String type,String name) {
        String sql="update dvd_movie set movie_type=? where movie_name=?";
        List<Object> list=new ArrayList<>();
        list.add(type);
        list.add(name);
        return BaseDao.excuteUpdate(sql,list);
    }

    @Override
    public boolean updateMovieByIntroduction(String introduction,String name) {
        String sql="update dvd_movie set movie_introduction=? where movie_name=?";
        List<Object> list=new ArrayList<>();
        list.add(introduction);
        list.add(name);
        return BaseDao.excuteUpdate(sql,list);
    }

    @Override
    public boolean updateMovieByTime(int time,String name) {
        String sql="update dvd_movie set movie_time=? where movie_name=?";
        List<Object> list=new ArrayList<>();
        list.add(time);
        list.add(name);
        return BaseDao.excuteUpdate(sql,list);
    }

    @Override
    public Movie findMovieById(int mid) throws Exception {
        String sql="select * from dvd_movie where movie_id=?";
        List<Object> list=new ArrayList<>();
        list.add(mid);
        Class<Movie> cls=Movie.class;
        List<Movie> movies=BaseDao.executeQuery(sql,cls,list);
        if(movies.size()!=0){
            return movies.get(0);
        }else
            return null;
    }

    @Override
    public boolean addTicket(int id) {
        String sql="update dvd_movie set movie_count=movie_count+? where movie_id=?";
        List<Object> list=new ArrayList<>();
        list.add(1);
        list.add(id);
        return BaseDao.excuteUpdate(sql,list);
    }

    @Override
    public List<Movie> findMovieByType(int i) throws Exception {
        String sql="select * from dvd_movie where movie_type=?";
        List<Object> list=new ArrayList<>();
        list.add(i);
        Class<Movie> cls=Movie.class;
        List<Movie> movies=BaseDao.executeQuery(sql,cls,list);
        if(movies.size()!=0){
            return movies;
        }else
            return null;
    }
}
