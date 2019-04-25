package com.zmj.service;

import com.zmj.entity.Session;

import java.util.Collection;
import java.util.List;

public interface SessionService {
    List<Session> findAllSession();//查找到所有

    void insertSession(Session session);//添加场次

    boolean findSessionByExample(Session session) throws Exception;//查找是否这个时间点有安排

    void deleteAllSession();//删除所有场次

    void deleteSessionById(int id);//删除个别

    boolean findSessionById(int id) throws Exception;//查找场次通过id

    boolean updateSessionByExample(int id, Double price);//修改

    boolean deleteSessionByTime();//删除过期场次

    List<Session> findSessionByIdMid(int cid, int mid) throws Exception;

    List<Session> findSeatById(int id) throws Exception;//查找场次通过id

    void reduceTicket(int session_id);

    List<Session> findSessionBymovieid(int mid) throws Exception;
}
