package com.zmj.dao;

import com.zmj.entity.Session;

import java.util.Date;
import java.util.List;

public interface SessionDao {
    List<Session> findAllSession();//查询所有

    boolean insertSession(Session session);//添加

    List<Session> findSessionByExample(Session session) throws Exception;//查找

    boolean deleteAll();//删除所有

    boolean deleteSessionById(int id);//删除个别场次

    boolean findSessionById(int id) throws Exception;//查找个别

    boolean updateSessionByExample(int id, Double price);//修改

    boolean deleteSessionByTime(Date date);

    List<Session> findSessionByIdMid(int cid, int mid) throws Exception;

    List<Session> findSeatById(int id)throws Exception;

    boolean reduceTicket(int session_id);
}
