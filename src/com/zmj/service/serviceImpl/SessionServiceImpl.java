package com.zmj.service.serviceImpl;

import com.zmj.dao.SessionDao;
import com.zmj.dao.daoImpl.SessionDaoImpl;
import com.zmj.entity.Session;
import com.zmj.service.SessionService;
import com.zmj.util.TimeUtil;

import java.util.List;

public class SessionServiceImpl implements SessionService {
    private SessionDao sessionDao;

    public SessionServiceImpl() {
        sessionDao=new SessionDaoImpl();
    }

    @Override
    public List<Session> findAllSession() {
        return sessionDao.findAllSession();
    }

    @Override
    public void insertSession(Session session) {
        if(sessionDao.insertSession(session)){
            System.out.println("添加成功");
        }else
            System.out.println("添加失败！");
    }

    @Override
    public boolean findSessionByExample(Session session) throws Exception {
        if(sessionDao.findSessionByExample(session).size()>0){
            for (Session s:sessionDao.findSessionByExample(session)) {
                if(TimeUtil.compareTime(s.getBegin_time(),session.getBegin_time())&&TimeUtil.compareTime(session.getBegin_time(),s.getEnd_time())){
                    System.out.println("此场次已存在：");
                    System.out.println(s);
                    return false;//存在就不能添加
                }else
                    return true;//不存在可以添加
            }
        }
        else{
            System.out.println("目前没有此场次安排！");
            return true;//不存在可以添加
        }
        return true;
    }

    @Override
    public void deleteAllSession() {
        if(sessionDao.deleteAll()){
            System.out.println("删除成功");
        }else
            System.out.println("删除失败");
    }
}
