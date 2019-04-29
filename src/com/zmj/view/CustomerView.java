package com.zmj.view;


import com.zmj.entity.Session;
import com.zmj.entity.Ticket;
import com.zmj.entity.User;
import com.zmj.service.*;
import com.zmj.service.serviceImpl.*;
import com.zmj.util.InputUtil;

import java.util.Scanner;

/**
 * 顾客主界面
 */
public class CustomerView {
    private int user_id = MainView.id;
    private CustomerBuyView customerBuyView;
    private CustomerCommentView customerCommentView;
    private CardService cardService;
    private TicketService ticketService;
    private UserService userService;
    private SessionService sessionService;

    public CustomerView() {
        customerBuyView = new CustomerBuyView();
        cardService = new CardServiceImpl();
        ticketService = new TicketServiceImpl();
        customerCommentView = new CustomerCommentView();
        userService = new UserServiceImpl();
        sessionService = new SessionServiceImpl();
    }

    /**
     * 进入电影院
     */
    public void CustomerWelcom() {
        findUsername();
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("请输入你的选择：");
            System.out.println("1、购票 2、充值 3、修改密码 4、购买电影卡 5、查询余额 6、购票记录7、影评8、查看个人信息0、返回");
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
                case 8://个人信息
                    information();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("输入有误，请重新输入！");
                    break;
            }
        }
    }

    /**
     * 个人信息
     */
    private void information() {
        try {
            if (userService.finduserById(user_id) != null) {
                System.out.println(userService.finduserById(user_id));
            } else
                System.out.println("非法登陆！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查票
     *
     * @param user_id
     */
    private void findTicketById(int user_id) {
        try {
            if (ticketService.findTicketByUid(user_id).size() > 0) {
                for (Ticket t : ticketService.findTicketByUid(user_id)) {
                    Session session = sessionService.findSessionById1(t.getSession_id());
                    System.out.println(t + ",开始时间：" + session.getBegin_time() + ",结束时间：" + session.getEnd_time() + "】");
                }
            } else
                System.out.println("您没买任何票！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 充值
     */
    private void insertmoney() {
        while (true) {
            System.out.println("请输入你的选择：(1、个人余额充值 2、影城卡充值 0、返回");
            Scanner input = new Scanner(System.in);
            int i = InputUtil.getInputByInt(input);
            switch (i) {
                case 1:
                    moneyByUser();
                    break;
                case 2:
                    moneyByCard();
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
     * 充值影城卡
     */
    private void moneyByCard() {
        try {
            if (cardService.findCardById(user_id).size() > 0) {
                System.out.println("请输入充值金额：(不得低于50)");
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

    /**
     * 充值个人余额
     */
    private void moneyByUser() {
        try {
            if(userService.finduserById(user_id)==null){
                System.out.println("出现内部错误！");
            }else {
                System.out.println("请输入金额：(不得低于50)");
                Scanner input = new Scanner(System.in);
                double money = InputUtil.getInputByDouble(input);
                if (money > 50) {
                    if(userService.updateUser(money, user_id)){
                        System.out.println("充值成功");
                    } else
                        System.out.println("充值失败！");
                }else
                    System.out.println("充值金额不得低于50！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 查卡
     *
     * @param user_id
     */
    private void findCard(int user_id) {
        while (true) {
            System.out.println("请输入你的选择：(1、个人余额查询 2、影城卡余额查询 0、返回");
            Scanner input = new Scanner(System.in);
            int i = InputUtil.getInputByInt(input);
            switch (i) {
                case 1:
                    findByUser(user_id);
                    break;
                case 2:
                    findByCard(user_id);
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
     * 查找影城卡余额
     * @param user_id
     */
    private void findByCard(int user_id) {
        try {
            if (cardService.findCardById(user_id).size() > 0) {
                System.out.println(cardService.findCardById(user_id).get(0));
            } else
                System.out.println("未办理任何电影卡");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查找个人余额
     * @param user_id
     */
    private void findByUser(int user_id) {
        try {
            if (userService.finduserById(user_id)==null){
                System.out.println("出现内部错误！");
            }else {
                User user=userService.finduserById(user_id);
                System.out.println("用户：用户id="+user.getUser_id()+",用户名="+user.getUser_name()+",用户个人余额="+user.getUser_money());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 买卡
     */
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

    /**
     * 修改密码
     */
    private void updatepwd() {
        System.out.println("请输入你要修改的新密码：");
        Scanner input = new Scanner(System.in);
        String pwd = InputUtil.getInputByString(input);
        if (userService.updatePwd(user_id, pwd)) {
            System.out.println("修改成功");
        } else
            System.out.println("修改失败！");
    }

    /**
     * 欢迎界面
     */
    private void findUsername() {
        try {
            String name = userService.findUsername(user_id);
            System.out.println("**********************欢迎【..." + name + "...】进入购票界面**********************");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
