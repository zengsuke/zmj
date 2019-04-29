package com.zmj.view;

import com.zmj.entity.User;
import com.zmj.service.SessionService;
import com.zmj.service.UserService;
import com.zmj.service.serviceImpl.SessionServiceImpl;
import com.zmj.service.serviceImpl.UserServiceImpl;
import com.zmj.util.InputUtil;

import java.util.Scanner;

public class MainView {
    private UserService userService;
    private SessionService sessionService;
    static int id;//记录id

    public MainView() {
        userService = new UserServiceImpl();
        sessionService = new SessionServiceImpl();
    }

    public void MainComing() {
        sessionService.deleteSessionByTime();//刷新场次，修改过期场次状态
        System.out.println("Welcom!!!!");
        while (true) {
            System.out.println("请输入你的选择：");
            System.out.println("1、登陆  2、注册  3、忘记密码0、退出");
            Scanner input = new Scanner(System.in);
            int choice = InputUtil.getInputByInt(input);
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    regist();
                    break;
                case 3:
                    lossing();//忘记密码
                    break;
                case 0:
                    System.out.println("登出成功！");
                    System.exit(0);
                default:
                    System.out.println("输入有误");
                    break;
            }
        }
    }

    /**
     * 忘记密码
     */
    private void lossing() {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入你的账号：");
        int user_id = InputUtil.getInputByInt(input);
        try {
            if(userService.finduserById(user_id) == null){
                System.out.println("此账户不存在！");
            }else {
                System.out.println("请输入你的姓名：");
                String name=InputUtil.getInputByString(input);
                if(userService.findUser(user_id,name)==null){
                    System.out.println("账号或名字输入有误！");
                }else {
                    System.out.println(userService.findUser(user_id,name));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void login() {
        User user = new User();
        Scanner input = new Scanner(System.in);
        System.out.println("请输入你的账号：");
        int user_id = InputUtil.getInputByInt(input);
        System.out.println("请输入你的密码：");
        String user_pwd = InputUtil.getInputByString(input);
        try {
            if (userService.finduserById(user_id) == null) {
                System.out.println("此账户不存在！");
            } else {
                user.setUser_id(user_id);
                user.setUser_pwd(user_pwd);
                boolean result = userService.login(user);
                if (result) {
                    System.out.println("登陆成功");
                    id = user_id;
                    intoCinema(id);
                } else
                    System.out.println("输入密码或者账号有误");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void intoCinema(int id) {
        try {
            if (userService.choiceType(id) == 0) {
                ManagerView managerView = new ManagerView();
                managerView.ManagerWelcom();
            } else if (userService.choiceType(id) != 0) {
                CustomerView cinemaView = new CustomerView();
                cinemaView.CustomerWelcom();
            } else {
                System.out.println("出现内部错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void regist() {
        User user = new User();
        Scanner input = new Scanner(System.in);
        System.out.println("请输入注册的名称：");
        String user_name = InputUtil.getInputByString(input);
        System.out.println("请输入注册的密码：");
        String user_pwd = InputUtil.getInputByString(input);
        user.setUser_name(user_name);
        user.setUser_pwd(user_pwd);
        try {
            boolean result = userService.insert(user);
            if (result) {
                System.out.println("注册成功！");
            } else
                System.out.println("注册失败！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
