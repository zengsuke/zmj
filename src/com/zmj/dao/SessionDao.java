package com.zmj.dao;

import com.zmj.entity.Session;

import java.util.List;

public interface SessionDao {
    public List<Session> findAllSession();//查询所有

    public boolean insertSession(Session session);//添加

    public List<Session> findSessionByExample(Session session) throws Exception;//查找

    public boolean deleteAll();//删除所有
}
