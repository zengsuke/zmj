package com.zmj.util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtil {
    public static int getInputByInt(Scanner input){
        while (true){
            try {
                int i=input.nextInt();//捕获输入其他不同的类型值
                return i;
            }catch (InputMismatchException e){
                System.out.println("输入类型错误！请重新输入：");
                input.nextLine();
            }
        }
    }
    public static String getInputByString(Scanner input){
        while (true){
            try {
                String i=input.next();//捕获输入其他不同的类型值
                return i;
            }catch (InputMismatchException e){
                System.out.println("输入类型错误！请重新输入：");
                input.nextLine();
            }
        }
    }
    public static String getInputByTime(Scanner input){
        while (true){
            try {
                String i=input.nextLine();//捕获输入其他不同的类型值
                return i;
            }catch (InputMismatchException e){
                System.out.println("输入类型错误！请重新输入：");
                input.nextLine();
            }
        }
    }

    public static Double getInputByDouble(Scanner input){
        while (true){
            try {
                Double i=input.nextDouble();//捕获输入其他不同的类型值
                return i;
            }catch (InputMismatchException e){
                System.out.println("输入类型错误！请重新输入：");
                input.nextLine();
            }
        }
    }
}
