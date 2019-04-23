package com.zmj.service;

import com.zmj.entity.User;

public interface UserService {
    public boolean login(User user) throws Exception;//登陆
    public boolean insert(User user) throws Exception;//注册
    public int choiceType(int id) throws Exception;
}
