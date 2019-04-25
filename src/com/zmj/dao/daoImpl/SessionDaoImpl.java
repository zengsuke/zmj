package com.zmj.dao.daoImpl;

import com.zmj.dao.BaseDao;
import com.zmj.entity.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SessionDaoImpl implements com.zmj.dao.SessionDao {
    @Override
    public List<Session> findAllSession() {
        String sql = "select * from dvd_session";
        Class<Session> cls = Session.class;
        return BaseDao.executeAll(sql, cls);
    }

    @Override
    public boolean insertSession(Session session) {
        String sql = "insert into dvd_session(movie_id,cinema_id,hall_id,movie_price,begin_time,end_time,seat_number,session_state)values(?,?,?,?,?,?,?)";
        List<Object> list = new ArrayList<>();
        list.add(session.getMovie_id());
        list.add(session.getCinema_id());
        list.add(session.getHall_id());
        list.add(session.getMovie_price());
        list.add(session.getBegin_time());
        list.add(session.getEnd_time());
        list.add(session.getSeat_number());
        list.add(0);
        return BaseDao.excuteUpdate(sql, list);
    }

    @Override
    public List<Session> findSessionByExample(Session session) throws Exception {
        String sql = "select * from dvd_session where movie_id=? and cinema_id=? and hall_id=? and session_state=?";
        List<Object> list = new ArrayList<>();
        list.add(session.getMovie_id());
        list.add(session.getCinema_id());
        list.add(session.getHall_id());
        list.add(0);
        Class<Session> cls = Session.class;
        return BaseDao.executeQuery(sql, cls, list);

    }

    @Override
    public boolean deleteAll() {//重置表
        String sql = "truncate table dvd_session";
        List<Object> list = new ArrayList<>();
        BaseDao.excuteUpdate(sql, list);
        return true;
    }

    @Override
    public boolean deleteSessionById(int id) {
        String sql="update dvd_session set session_state=? where session_id=?";
        List<Object> list = new ArrayList<>();
        list.add(1);
        list.add(id);
        if(BaseDao.excuteUpdate(sql, list)){
            return true;
        }else
            return false;
    }

    @Override
    public boolean findSessionById(int id) throws Exception {
        String sql="select * from dvd_session where session_id=? and session_state=?";
        List<Object> list = new ArrayList<>();
        list.add(id);
        list.add(0);
        Class<Session> cls=Session.class;
        if(BaseDao.executeQuery(sql,cls,list).size()>0){
            return true;//查找到了
        }else
            return false;
    }

    @Override
    public boolean updateSessionByExample(int id, Double price) {
        String sql="update dvd_session set movie_price=? where session_id=? and session_state=?";
        List<Object> list = new ArrayList<>();
        list.add(price);
        list.add(id);
        list.add(0);
        if(BaseDao.excuteUpdate(sql, list)){
            return true;
        }else
            return false;
    }

    @Override
    public boolean deleteSessionByTime(Date date) {
        String sql="update dvd_session set session_state=?  where begin_time<?";
        List<Object> list = new ArrayList<>();
        list.add(1);
        list.add(date);
        if(BaseDao.excuteUpdate(sql, list)){
            return true;
        }else
            return false;
    }

    @Override
    public List<Session> findSessionByIdMid(int cid, int mid) throws Exception {
        String sql="select * from dvd_session where cinema_id=? and movie_id=? and session_state=?";
        List<Object> list=new ArrayList<>();
        list.add(cid);
        list.add(mid);
        list.add(0);
        Class<Session> cls=Session.class;
        return BaseDao.executeQuery(sql,cls,list);
    }

    @Override
    public List<Session> findSeatById(int id) throws Exception {
        String sql="select * from dvd_session where session_id=? and session_state=?";
        List<Object> list = new ArrayList<>();
        list.add(id);
        list.add(0);
        Class<Session> cls=Session.class;
        return BaseDao.executeQuery(sql,cls,list);
    }

    @Override
    public boolean reduceTicket(int session_id) {
        String sql="update dvd_session set seat_number=seat_number-1 where session_id=? and session_state=?";
        List<Object> list = new ArrayList<>();
        list.add(session_id);
        list.add(0);
        if(BaseDao.excuteUpdate(sql, list)){
            return true;
        }else
            return false;
    }
}
