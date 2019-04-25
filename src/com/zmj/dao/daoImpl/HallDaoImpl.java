package com.zmj.dao.daoImpl;

import com.zmj.dao.BaseDao;
import com.zmj.entity.Hall;

import java.util.ArrayList;
import java.util.List;

public class HallDaoImpl implements com.zmj.dao.HallDao {
    @Override
    public List<Hall> findAll() {
        String sql="select * from dvd_hall";
        Class<Hall> cls=Hall.class;
        List<Hall> halls=BaseDao.executeAll(sql,cls);
        if(halls.size()>0){
            return halls;
        }else
            return null;
    }

    @Override
    public List<Hall> findHallById(int hid) throws Exception {
        String sql="select * from dvd_hall where hall_id=?";
        List<Object> list=new ArrayList<>();
        list.add(hid);
        Class<Hall> cls=Hall.class;
        List<Hall> halls=BaseDao.executeQuery(sql,cls,list);
        return halls;
    }

    @Override
    public boolean insertHall(Hall hall) {
        String sql="insert into dvd_hall(hall_number,cinema_id,hall_seat)values(?,?,?)";
        List<Object> list=new ArrayList<>();
        list.add(hall.getHall_number());
        list.add(hall.getCinema_id());
        list.add(hall.getHall_seat());
        return BaseDao.excuteUpdate(sql,list);
    }

    @Override
    public boolean deleteHallById(int hid) {
        String sql="delete from dvd_hall where hall_id=?";
        List<Object> list=new ArrayList<>();
        list.add(hid);
        return BaseDao.excuteUpdate(sql,list);
    }

    @Override
    public List<Hall> findHallByCid(int cid) throws Exception {
        String sql="select * from dvd_hall where cinema_id=?";
        List<Object> list=new ArrayList<>();
        list.add(cid);
        Class<Hall> cls=Hall.class;
        return BaseDao.executeQuery(sql,cls,list);
    }

    @Override
    public List<Hall> findHallByCN(int cid, String hn) throws Exception {
        String sql="select * from dvd_hall where cinema_id=? and hall_number=?";
        List<Object> list=new ArrayList<>();
        list.add(cid);
        list.add(hn);
        Class<Hall> cls=Hall.class;
        List<Hall> halls=BaseDao.executeQuery(sql,cls,list);
        return halls;
    }
}
