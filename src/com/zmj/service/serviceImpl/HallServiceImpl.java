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
    public List<Hall> findHallById(int hid) throws Exception {
        return hallDao.findHallById(hid);
    }

    @Override
    public boolean addHall(Hall hall) {
        return hallDao.insertHall(hall);
    }

    @Override
    public boolean deleteHallById(int id) {
        return hallDao.deleteHallById(id);
    }

    @Override
    public List<Hall> findHallByCid(int cid) throws Exception {
        return hallDao.findHallByCid(cid);
    }

    @Override
    public List<Hall> findHallByCN(int cid, String hn) throws Exception {
        return hallDao.findHallByCN(cid,hn);
    }
}
