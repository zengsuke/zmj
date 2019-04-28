package com.zmj.view;

import com.zmj.entity.Cinema;
import com.zmj.entity.Hall;
import com.zmj.service.CinemaService;
import com.zmj.service.HallService;
import com.zmj.service.serviceImpl.CinemaServiceImpl;
import com.zmj.service.serviceImpl.HallServiceImpl;
import com.zmj.util.InputUtil;

import java.util.Scanner;

public class ManagerHallView {
    private HallService hallService;
    private CinemaService cinemaService;

    public ManagerHallView() {
        hallService = new HallServiceImpl();
        cinemaService = new CinemaServiceImpl();
    }

    public void ManagerHallCome() {
        Scanner input = new Scanner(System.in);
        System.out.println("欢迎来到放映厅管理界面");
        while (true) {
            if (hallService.findAllHall() != null) {
                System.out.println("现所存在的影厅如下：");
                for (Hall hall : hallService.findAllHall()) {
                    System.out.println(hall);
                }
            } else
                System.out.println("目前没有任何影厅！");
            System.out.println("请选择你的操作：1、添加放映厅2、删除放映厅0、返回");
            int choice = InputUtil.getInputByInt(input);
            switch (choice) {
                case 1:
                    insertHall();
                    break;
                case 2:
                    deleteHall();
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
     * 删除场厅
     */
    private void deleteHall() {
        Scanner input = new Scanner(System.in);
        try {
            System.out.println("请输入你要删除影厅的编号：");
            int hid = InputUtil.getInputByInt(input);
            if (hallService.findHallById(hid).size() > 0) {
                if (hallService.deleteHallById(hid)) {
                    System.out.println("删除成功！");
                } else
                    System.out.println("删除失败！");
            } else {
                System.out.println("此影厅不存在于这个影院中！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加场厅
     */
    private void insertHall() {
        Scanner input = new Scanner(System.in);
        try {
            if (cinemaService.allCinema().size() > 0) {
                for (Cinema c:cinemaService.allCinema()) {
                    System.out.println(c);
                }
                System.out.println("请输入你所在的影院编号：");
                int cid = InputUtil.getInputByInt(input);
                Cinema cinema = new Cinema();
                cinema.setCinema_id(cid);
                if (cinemaService.findCinemaById(cinema) == null) {
                    System.out.println("此电影院不存在！请重新输入！");
                } else {
                    System.out.println("请输入你要添加影厅的名称：");
                    String hn = InputUtil.getInputByString(input);
                    if (hallService.findHallByCN(cid, hn).size() > 0) {
                        System.out.println("此电影院存在这个影厅，请重新输入！");
                    } else {
                        System.out.println("请输入该影厅座位数量：(不得少于10个座位！)");
                        int seat = InputUtil.getInputByInt(input);
                        if(seat>=10){
                            Hall hall = new Hall();
                            hall.setCinema_id(cid);
                            hall.setHall_number(hn);
                            hall.setHall_seat(seat);
                            if (hallService.addHall(hall)) {
                                System.out.println("添加成功！");
                            } else
                                System.out.println("添加失败！");
                        }else
                            System.out.println("数量过少！");
                    }
                }
            } else
                System.out.println("你还没有影院！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
