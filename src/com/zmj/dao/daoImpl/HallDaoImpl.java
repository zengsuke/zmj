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
    public List<Hall> findHallByExample(int cid, int hid) throws Exception {
        String sql="select * from dvd_hall where cinema_id=? and hall_number=?";
        List<Object> list=new ArrayList<>();
        list.add(cid);
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
    public boolean deleteHallByExample(Hall hall) {
        String sql="delete from dvd_hall where hall_number=? and cinema_id=?";
        List<Object> list=new ArrayList<>();
        list.add(hall.getHall_number());
        list.add(hall.getCinema_id());
        return BaseDao.excuteUpdate(sql,list);
    }
}
