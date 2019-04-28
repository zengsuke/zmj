package com.zmj.service;

import com.zmj.entity.User;

import java.util.List;


public interface UserService {
    boolean login(User user) throws Exception;//登陆
    boolean insert(User user) throws Exception;//注册
    int choiceType(int id) throws Exception;
    boolean updatePwd(int manager_id,String pwd);//修改
    List<User> findAllUser();
    String findUsername(int id) throws Exception;

    User finduserById(int user_id) throws Exception;
}
