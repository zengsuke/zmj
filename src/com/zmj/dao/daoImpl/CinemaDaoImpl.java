package com.zmj.dao.daoImpl;

import com.zmj.dao.BaseDao;
import com.zmj.dao.CinemaDao;
import com.zmj.entity.Cinema;

import java.util.ArrayList;
import java.util.List;

public class CinemaDaoImpl implements CinemaDao {
    @Override
    public List<Cinema> findAllCinema() throws Exception {
        String sql="select * from dvd_cinema";
        Class<Cinema> cls=Cinema.class;
        return BaseDao.executeAll(sql,cls);
    }

    @Override
    public Cinema findCinemaById(Cinema cinema) throws Exception {
        String sql="select * from dvd_cinema where cinema_id=?";
        List<Object> list=new ArrayList<>();
        list.add(cinema.getCinema_id());
        Class<Cinema> cls=Cinema.class;
        List<Cinema> cinemas=BaseDao.executeQuery(sql,cls,list);
        if(cinemas.size()!=0){
            return cinemas.get(0);
        }else
            return null;
    }

    @Override
    public Cinema findCinemaByName(String name) throws Exception {
        String sql="select * from dvd_cinema where cinema_name=?";
        List<Object> list=new ArrayList<>();
        list.add(name);
        Class<Cinema> cls=Cinema.class;
        List<Cinema> cinemas=BaseDao.executeQuery(sql,cls,list);
        if(cinemas.size()!=0){
            return cinemas.get(0);
        }else
            return null;
    }

    @Override
    public boolean insertCinema(Cinema cinema) {
        String sql="insert into dvd_cinema(cinema_name,cinema_address)values(?,?)";
        List<Object> list=new ArrayList<>();
        list.add(cinema.getCinema_name());
        list.add(cinema.getCinema_address());
        return BaseDao.excuteUpdate(sql,list);
    }

    @Override
    public boolean deleteCinemaByNameAddress(Cinema cinema) {
        String sql="delete from dvd_cinema where cinema_name=? and cinema_address=?";
        List<Object> list=new ArrayList<>();
        list.add(cinema.getCinema_name());
        list.add(cinema.getCinema_address());
        return BaseDao.excuteUpdate(sql,list);
    }
}
