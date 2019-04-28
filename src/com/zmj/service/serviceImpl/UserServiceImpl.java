package com.zmj.service.serviceImpl;

import com.zmj.dao.UserDao;
import com.zmj.dao.daoImpl.UserDaoImpl;
import com.zmj.entity.User;
import com.zmj.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao=new UserDaoImpl();
    @Override
    public boolean login(User user) throws Exception {//登陆
        if(userDao.findUser(user)!=null){
            return true;
        }else
            return false;
    }

    @Override
    public boolean insert(User user) throws Exception {//注册
        if(userDao.findUserByName(user.getUser_name())==null){
            boolean result=userDao.insertUser(user);
            User user1=userDao.findUserByName(user.getUser_name());
            System.out.println("您获得的账号为："+user1.getUser_id());
            return result;
        }
        System.out.println("你输入的用户已存在！");
        return false;
    }

    @Override
    public int choiceType(int id) throws Exception {
        return userDao.findUserById(id).getUser_type();
    }

    @Override
    public boolean updatePwd(int manager_id,String pwd) {
        return userDao.updatePwd(manager_id,pwd);
    }

    @Override
    public List<User> findAllUser() {
        return userDao.findAll();
    }

    @Override
    public String findUsername(int id) throws Exception {
        return userDao.findUserById(id).getUser_name();
    }

    @Override
    public User finduserById(int user_id) throws Exception {
        return userDao.findUserById(user_id);
    }
}
