package com.zmj.view;

import com.zmj.entity.User;
import com.zmj.service.UserService;
import com.zmj.service.serviceImpl.UserServiceImpl;
import com.zmj.util.InputUtil;

import java.util.Scanner;

public class ManagerView {
    private int manager_id=MainView.id;
    private UserService userService;
    public ManagerView() {
        userService=new UserServiceImpl();
    }

    public void ManagerWelcom(){
        findUsername();
        Scanner input=new Scanner(System.in);
        while (true){
            System.out.println("您有如下选择：");
            System.out.println("1、操作电影2、操作电影院3、操作场厅4、操作场次5、修改管理员密码6、查看所有用户0、返回");
            int choice= InputUtil.getInputByInt(input);
            switch (choice){
                case 1:
                    ManagerMovieView managerMovieView=new ManagerMovieView();
                    managerMovieView.ManagerMovieCome();
                    break;
                case 2:
                    ManagerCinemaView managerCinemaView=new ManagerCinemaView();
                    managerCinemaView.ManagerCinemaCome();
                    break;
                case 3:
                    ManagerHallView managerHallView=new ManagerHallView();
                    managerHallView.ManagerHallCome();
                    break;
                case 4:
                    ManagerSessionView managerSessionView=new ManagerSessionView();
                    managerSessionView.ManagerSessionCome();
                    break;
                case 5:
                    updatepwd();
                    break;
                case 6:
                    findAllUser();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("输入有误请重新输入！");
                    break;
            }
        }
    }

    /**
     * 查询所有用户
     */
    private void findAllUser() {
        if(userService.findAllUser().size()>0){
            for (User u:userService.findAllUser()) {
                System.out.println(u);
            }
        }else
            System.out.println("目前还未有任何用户注册！");
    }

    /**
     * 修改密码
     */
    private void updatepwd() {
        System.out.println("请输入你要修改的密码：");
        Scanner input=new Scanner(System.in);
        String pwd=InputUtil.getInputByString(input);
        if(userService.updatePwd(manager_id,pwd)){
            System.out.println("修改成功");
        }else
            System.out.println("修改失败！");
    }

    /**
     * 欢迎界面
     */
    private void findUsername(){
        try {
            String name=userService.findUsername(manager_id);
            System.out.println("**********************欢迎"+name+"进入管理员系统**********************");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
