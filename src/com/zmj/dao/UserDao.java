package com.zmj.dao;

import com.zmj.entity.User;

public interface UserDao {
    public User findUser(User user) throws Exception;//登陆
    public User findUserByName(String name) throws Exception;//查找是否有这个人
    public boolean insertUser(User user);//注册
    public User findUserById(int id) throws Exception;//通过id查找用户

    boolean updatePwd(int id,String pwd);//修改密码
}
