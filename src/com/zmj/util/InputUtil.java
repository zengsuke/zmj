package com.zmj.util;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InputUtil {
    public static int getInputByInt(Scanner input) {
        while (true) {
            try {
                int i = input.nextInt();//捕获输入其他不同的类型值
                return i;
            } catch (InputMismatchException e) {
                System.out.println("输入类型错误！请重新输入：");
                input.nextLine();
            }
        }
    }

    public static String getInputByString(Scanner input) {
        while (true) {
            try {
                String i = input.next();//捕获输入其他不同的类型值
                return i;
            } catch (InputMismatchException e) {
                System.out.println("输入类型错误！请重新输入：");
                input.nextLine();
            }
        }
    }

    public static String getInputByTime(Scanner input) {
        while (true) {
            try {
                String i = input.nextLine();//捕获输入其他不同的类型值
                return i;
            } catch (InputMismatchException e) {
                System.out.println("输入类型错误！请重新输入：");
                input.nextLine();
            }
        }
    }

    public static Double getInputByDouble(Scanner input) {
        while (true) {
            try {
                Double i = input.nextDouble();//捕获输入其他不同的类型值
                return i;
            } catch (InputMismatchException e) {
                System.out.println("输入类型错误！请重新输入：");
                input.nextLine();
            }
        }
    }

    public static List<Integer> getTrueSeat() {
        while (true) {
            System.out.println("请输入第几排第几列：（输入格式：XX,XX）");
            Scanner input = new Scanner(System.in);
            String seat = InputUtil.getInputByString(input);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(seat);
            String seat1 = String.valueOf(stringBuffer);
            String[] list = seat1.split(",");
            List<Integer> list1 = new ArrayList<Integer>();
            for (int i = 0; i < list.length; i++) {
                try {
                    list1.add(Integer.valueOf(list[i]));
                } catch (NumberFormatException e) {
                    input.nextLine();
                }
            }
            if (list1.size() != 2) {
                System.out.println("输入格式错误！请重新输入");
            } else
                return list1;
        }
    }
}
