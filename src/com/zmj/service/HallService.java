package com.zmj.service;

import com.zmj.entity.Hall;

import java.util.List;

public interface HallService {

    public List<Hall> findAllHall();

    public List<Hall> findHallByExample(int cid, int hid) throws Exception;

    public boolean addHall(Hall hall);

    public boolean deleteHallByExample(Hall hall);
}
