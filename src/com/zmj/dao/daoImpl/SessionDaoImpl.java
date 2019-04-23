package com.zmj.dao.daoImpl;

import com.zmj.dao.BaseDao;
import com.zmj.entity.Session;

import java.util.ArrayList;
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
        String sql = "insert into dvd_session(movie_id,cinema_id,hall_id,movie_price,begin_time,end_time,seat_number)values(?,?,?,?,?,?,?)";
        List<Object> list = new ArrayList<>();
        list.add(session.getMovie_id());
        list.add(session.getCinema_id());
        list.add(session.getHall_id());
        list.add(session.getMovie_price());
        list.add(session.getBegin_time());
        list.add(session.getEnd_time());
        list.add(session.getSeat_number());
        return BaseDao.excuteUpdate(sql, list);
    }

    @Override
    public List<Session> findSessionByExample(Session session) throws Exception {
        String sql = "select * from dvd_session where movie_id=? and cinema_id=? and hall_id=?";
        List<Object> list = new ArrayList<>();
        list.add(session.getMovie_id());
        list.add(session.getCinema_id());
        list.add(session.getHall_id());
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
}
