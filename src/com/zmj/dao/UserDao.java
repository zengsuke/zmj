package com.zmj.dao;

import com.zmj.entity.User;

import java.util.List;

public interface UserDao {
    User findUser(User user) throws Exception;//登陆
    User findUserByName(String name) throws Exception;//查找是否有这个人
    boolean insertUser(User user);//注册
    User findUserById(int id) throws Exception;//通过id查找用户
    boolean updatePwd(int id,String pwd);//修改密码
    List<User> findAll();//查找所有用户

    User findUserByNI(int user_id, String name) throws Exception;

    boolean updateUser(double money, int user_id);
}
