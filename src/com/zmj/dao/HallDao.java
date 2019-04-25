package com.zmj.dao;

import com.zmj.entity.Hall;

import java.util.List;

public interface HallDao {

    public List<Hall> findAll();

    public List<Hall> findHallById(int hid) throws Exception;

    public boolean insertHall(Hall hall);

    public boolean deleteHallById(int hid);

    List<Hall> findHallByCid(int cid) throws Exception;

    List<Hall> findHallByCN(int cid, String hn)throws Exception;
}
