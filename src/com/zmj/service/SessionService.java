package com.zmj.service;

import com.zmj.entity.Session;

import java.util.List;

public interface SessionService {
    public List<Session> findAllSession();//查找到所有

    public void insertSession(Session session);//添加场次

    public boolean findSessionByExample(Session session) throws Exception;//查找是否这个时间点有安排

    public void deleteAllSession();//删除所有场次
}
