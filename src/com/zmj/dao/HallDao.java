package com.zmj.dao;

import com.zmj.entity.Hall;

import java.util.List;

public interface HallDao {

    public List<Hall> findAll();

    public List<Hall> findHallByExample(int cid, int hid) throws Exception;

    public boolean insertHall(Hall hall);

    public boolean deleteHallByExample(Hall hall);
}
