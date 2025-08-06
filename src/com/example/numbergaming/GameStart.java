package com.example.numbergaming;

import java.util.Scanner;

/**
 * 游戏启动器类，作为程序的入口点
 * 负责协调用户输入与游戏逻辑的交互
 */
public class GameStart {
    /**
     * 程序主方法，JVM从这里开始执行
     */
    public static void main(String[] args) {
        //创建数字猜谜游戏实例，初始化游戏
        NumberGuessingGame game = new NumberGuessingGame();

        //创建Scanner对象，用于读取用户的控制台输入
        Scanner scanner = new Scanner(System.in);

        //向用户显示游戏开始提示信息，说明输入范围
        System.out.println("请输入一个0-10之间的整数");

        //游戏主循环:只要游戏未结束，就持续接收用户猜测
        while (!game.isGameOver()) {
            //输入验证循环:确保用户输入的是整数
            while (!scanner.hasNextInt()) {
                //提示用户输入无效，需要重新输入整数
                System.out.println("输入无效，请输入一个整数！");
                //读取并丢弃无效输入，避免循环卡住
                scanner.next();
            }
            //获取用户输入的整数猜测值
            int guess = scanner.nextInt();
            //将猜测值传入游戏逻辑，获取判断结果
            String result = game.makeGuess(guess);
            //向用户显示本次猜测的结果反馈
            System.out.println(result);
        }
        //游戏结束后，关闭Scanner释放资源
        scanner.close();
    }
}
