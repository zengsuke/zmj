package com.zmj.service.serviceImpl;

import com.zmj.dao.HallDao;
import com.zmj.dao.daoImpl.HallDaoImpl;
import com.zmj.entity.Hall;
import com.zmj.service.HallService;

import java.util.List;

public class HallServiceImpl implements HallService {
    HallDao hallDao;

    public HallServiceImpl() {
        hallDao=new HallDaoImpl();
    }

    @Override
    public List<Hall> findAllHall() {
        return hallDao.findAll();
    }

    @Override
    public List<Hall> findHallByExample(int cid, int hid) throws Exception {
        return hallDao.findHallByExample(cid,hid);
    }

    @Override
    public boolean addHall(Hall hall) {
        return hallDao.insertHall(hall);
    }

    @Override
    public boolean deleteHallByExample(Hall hall) {
        return hallDao.deleteHallByExample(hall);
    }
}
