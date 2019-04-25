package com.zmj.view;


import com.zmj.entity.Ticket;
import com.zmj.service.*;
import com.zmj.service.serviceImpl.*;
import com.zmj.util.InputUtil;

import java.util.Scanner;

public class CustomerView {
    private int user_id = MainView.id;
    private CustomerBuyView customerBuyView;
    private CustomerCommentView customerCommentView;
    private CardService cardService;
    private TicketService ticketService;

    public CustomerView() {
        customerBuyView = new CustomerBuyView();
        cardService = new CardServiceImpl();
        ticketService = new TicketServiceImpl();
        customerCommentView = new CustomerCommentView();
    }

    public void CustomerWelcom() {//进入电影院
        System.out.println("欢迎进入购票界面");
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("请输入你的选择：");
            System.out.println("1、购票 2、充值 3、修改密码 4、购买电影卡 5、查询余额 6、购票记录7、影评0、返回");
            int choice = InputUtil.getInputByInt(input);
            switch (choice) {
                case 1:
                    customerBuyView.buy();//购票
                    break;
                case 2://充值
                    insertmoney();
                    break;
                case 3://密码
                    updatepwd();
                    break;
                case 4://购卡
                    buyCard();
                    break;
                case 5://查询余额
                    findCard(user_id);
                    break;
                case 6://购票记录
                    findTicketById(user_id);
                    break;
                case 7://影评
                    customerCommentView.commentWelcome(user_id);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("输入有误，请重新输入！");
                    break;
            }
        }
    }

    private void findTicketById(int user_id) {
        try {
            if (ticketService.findTicketByUid(user_id).size() > 0) {
                for (Ticket t : ticketService.findTicketByUid(user_id)) {
                    System.out.println(t);
                }
            } else
                System.out.println("您没买任何票！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertmoney() {
        try {
            if (cardService.findCardById(user_id).size() > 0) {
                System.out.println("请输入充值金额：");
                Scanner input = new Scanner(System.in);
                double money = InputUtil.getInputByDouble(input);
                if (money > 50) {
                    if (cardService.updateCardById(money, user_id)) {
                        System.out.println("充值成功");
                    } else
                        System.out.println("充值失败！");
                } else
                    System.out.println("充值金额不得低于50");
            } else
                System.out.println("此账户没有影城卡！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void findCard(int user_id) {
        try {
            if (cardService.findCardById(user_id).size() > 0) {
                System.out.println(cardService.findCardById(user_id).get(0));
            } else
                System.out.println("未办理任何电影卡");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buyCard() {

        try {
            if (cardService.findCardById(user_id).size() > 0) {
                System.out.println("此账号已有电影卡！");
            } else {
                System.out.println("请充值金额：");
                Scanner input = new Scanner(System.in);
                double money = InputUtil.getInputByDouble(input);
                if (money >= 100) {
                    if (cardService.addCard(money, user_id)) {
                        System.out.println("办卡成功");
                    } else
                        System.out.println("办卡失败！请联系客服");
                } else
                    System.out.println("第一次充入金额不得少于100");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updatepwd() {
        UserService userService = new UserServiceImpl();
        System.out.println("请输入你要修改的密码：");
        Scanner input = new Scanner(System.in);
        String pwd = InputUtil.getInputByString(input);
        if (userService.updatePwd(user_id, pwd)) {
            System.out.println("修改成功");
        } else
            System.out.println("修改失败！");
    }


}
