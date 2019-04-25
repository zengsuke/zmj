package com.zmj.service.serviceImpl;

import com.zmj.dao.CinemaDao;
import com.zmj.dao.daoImpl.CinemaDaoImpl;
import com.zmj.entity.Cinema;
import com.zmj.service.CinemaService;

import java.util.List;

public class CinemaServiceImpl implements CinemaService {
    CinemaDao cinemaDao=new CinemaDaoImpl();
    @Override
    public List<Cinema> allCinema() throws Exception {
       return cinemaDao.findAllCinema();
    }

    @Override
    public Cinema findCinemaById(Cinema cinema) throws Exception {
        return cinemaDao.findCinemaById(cinema);
    }

    @Override
    public boolean findCinemaByName(Cinema cinema) throws Exception {
        if(cinemaDao.findCinemaByName(cinema.getCinema_name())!=null){
            return true;
        }else
            return false;
    }

    @Override
    public boolean addCinema(Cinema cinema) {
        return cinemaDao.insertCinema(cinema);
    }

    @Override
    public boolean deleteCinemaByNameAddress(Cinema cinema) {
        return cinemaDao.deleteCinemaByNameAddress(cinema);
    }

    @Override
    public void updateCinemaNById(int id, String name) {
        if(cinemaDao.updateName(id,name)){
            System.out.println("修改成功");
        }else
            System.out.println("修改失败！");
    }

    @Override
    public void updateCinemaAById(int id, String address) {
        if(cinemaDao.updateAddress(id,address)){
            System.out.println("修改成功");
        }else
            System.out.println("修改失败！");
    }
}
