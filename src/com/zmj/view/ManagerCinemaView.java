package com.zmj.view;

import com.zmj.entity.Cinema;
import com.zmj.service.CinemaService;
import com.zmj.service.serviceImpl.CinemaServiceImpl;
import com.zmj.util.InputUtil;
import java.util.Scanner;

public class ManagerCinemaView {
    private CinemaService cinemaService;

    private int manager_id=MainView.id;
    private int i=-1;

    public ManagerCinemaView() {
        cinemaService=new CinemaServiceImpl();
    }

    public void ManagerCinemaCome(){
        System.out.println("欢迎"+manager_id+"来到管理员电影院系统：");
        Scanner input=new Scanner(System.in);
        while (true){
            System.out.println("请输入你的选择：");
            System.out.println("1、添加电影院 2、删除影院 3、修改影院 4、查看影院 0、返回");
            int i= InputUtil.getInputByInt(input);
            switch (i){
                case 1:
                    ManagerInsertCinema();
                    break;
                case 2:
                    ManagerDeleteCinema();
                    break;
                case 3:
                    ManagerUpdateCinema();
                    break;
                case 4:
                    ManagerFindAll();
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
     * 修改影院
     */
    private void ManagerUpdateCinema() {
        System.out.println("请输入要修改的电影院编号：");
        Scanner input=new Scanner(System.in);
        int id=InputUtil.getInputByInt(input);
        Cinema cinema=new Cinema();
        cinema.setCinema_id(id);
        try {
            if(cinemaService.findCinemaById(cinema)!=null){
                while (true){
                    System.out.println("请输入你想修改的内容：1、电影院名字 2、地址 0、返回");
                    int choice=InputUtil.getInputByInt(input);
                    switch (choice){
                        case 1:
                            System.out.println("请输入修改后的名字：");
                            String name=InputUtil.getInputByString(input);
                            cinemaService.updateCinemaNById(id,name);
                            break;
                        case 2:
                            System.out.println("请输入修改后的地址：");
                            String address=InputUtil.getInputByString(input);
                            cinemaService.updateCinemaAById(id,address);
                            break;
                        case 0:
                            return;
                        default:
                            break;

                    }
                }
            }else
                System.out.println("没有此电影相关内容！");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有影院
     */
    public void ManagerFindAll() {
        try {
            if(cinemaService.allCinema().size()>0){
                for (Cinema c:cinemaService.allCinema()) {
                    System.out.println(c);
                }
            }else
                System.out.println("目前没有电影院！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除影院
     */
    public void ManagerDeleteCinema() {
            try {
                System.out.println("请输入电影院名字：");
                String cinema_name=InputUtil.getInputByString(new Scanner(System.in));
                Cinema cinema=new Cinema();
                cinema.setCinema_name(cinema_name);
                if(cinemaService.findCinemaByName(cinema)){
                    System.out.println("请输入电影院的地址：");
                    String cinema_address=InputUtil.getInputByString(new Scanner(System.in));
                    cinema.setCinema_address(cinema_address);
                    if(cinemaService.deleteCinemaByNameAddress(cinema)){
                        System.out.println("删除成功");
                    }else
                        System.out.println("删除失败！");
                } else
                    System.out.println("不存在此电影院！");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    /**
     * 添加影院
     */
    public void ManagerInsertCinema(){
        try {
            System.out.println("请输入电影院名字：");
            String cinema_name=InputUtil.getInputByString(new Scanner(System.in));
            Cinema cinema=new Cinema();
            cinema.setCinema_name(cinema_name);
            if(!cinemaService.findCinemaByName(cinema)){
                System.out.println("请输入电影院的地址：");
                String cinema_address=InputUtil.getInputByString(new Scanner(System.in));
                cinema.setCinema_address(cinema_address);
                if( cinemaService.addCinema(cinema)){
                    System.out.println("添加成功");
                }else
                    System.out.println("添加失败！");
            }else
                System.out.println("重名！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
