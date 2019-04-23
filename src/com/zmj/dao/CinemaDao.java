package com.zmj.dao;

import com.zmj.entity.Cinema;

import java.util.List;

public interface CinemaDao {
    public List<Cinema> findAllCinema() throws Exception;//查询所有
    public Cinema findCinemaById(Cinema cinema) throws Exception;//通过id查找
    public Cinema findCinemaByName(String name) throws Exception;//通过name查找
    public boolean insertCinema(Cinema cinema);//添加
    public boolean deleteCinemaByNameAddress(Cinema cinema);//删除
}
