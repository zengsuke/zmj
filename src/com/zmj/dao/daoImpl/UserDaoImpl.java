package com.zmj.dao.daoImpl;

import com.zmj.dao.BaseDao;
import com.zmj.dao.UserDao;
import com.zmj.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public User findUser(User user) throws Exception {//查找，登陆
        String sql="select * from dvd_user where user_id=?  and user_pwd=?";
        List<Object> list = new ArrayList<>();
        list.add(user.getUser_id());
        list.add(user.getUser_pwd());
        Class<User> cls=User.class;
        List<User> users=BaseDao.executeQuery(sql,cls,list);
        if(users.size()!=0){
            return users.get(0);
        }else
            return null;
    }

    @Override
    public User findUserByName(String name) throws Exception {//通过名字找用户，协助注册
        String sql="select * from dvd_user where user_name=?";
        List<Object> list = new ArrayList<>();
        list.add(name);
        Class<User> cls = User.class;
        List<User> users=BaseDao.executeQuery(sql,cls,list);
        if(users.size()!=0){
            return users.get(0);
        }else
            return null;
    }

    @Override
    public boolean insertUser(User user) {//注册
        String sql="insert into dvd_user(user_name,user_type,user_pwd)values(?,?,?)";
        List<Object> list=new ArrayList<>();
        list.add(user.getUser_name());
        list.add(1);
        list.add(user.getUser_pwd());
        return BaseDao.excuteUpdate(sql,list);
    }

    @Override
    public User findUserById(int id) throws Exception {
        String sql="select * from dvd_user where user_id=?";
        List<Object> list=new ArrayList<>();
        list.add(id);
        Class<User> cls=User.class;
        List<User> users=BaseDao.executeQuery(sql,cls,list);
        if(users.size()!=0){
            return users.get(0);
        }else
            return null;
    }

    @Override
    public boolean updatePwd(int id,String pwd) {
        String sql="update dvd_user set user_pwd=? where user_id=?";
        List<Object> list=new ArrayList<>();
        list.add(pwd);
        list.add(id);
        if(BaseDao.excuteUpdate(sql,list)){
            return true;
        }else
            return false;
    }
}
