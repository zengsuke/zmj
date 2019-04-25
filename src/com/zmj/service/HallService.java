package com.zmj.service;

import com.zmj.entity.Hall;

import java.util.Collection;
import java.util.List;

public interface HallService {

    public List<Hall> findAllHall();

    public List<Hall> findHallById(int hid) throws Exception;

    public boolean addHall(Hall hall);

    public boolean deleteHallById(int hid);

    List<Hall> findHallByCid(int cid) throws Exception;

    List<Hall> findHallByCN(int cid, String hn)throws Exception;
}
