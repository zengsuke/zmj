package com.zmj.dao;

import com.zmj.entity.Cinema;

import java.util.List;

public interface CinemaDao {
    List<Cinema> findAllCinema() throws Exception;//查询所有
    Cinema findCinemaById(Cinema cinema) throws Exception;//通过id查找
    Cinema findCinemaByName(String name) throws Exception;//通过name查找
    boolean insertCinema(Cinema cinema);//添加
    boolean deleteCinemaByNameAddress(Cinema cinema);//删除

    boolean updateName(int id, String name);

    boolean updateAddress(int id, String address);
}
