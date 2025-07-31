package com.example.numbergaming;
import java.util.Random;
import java.util.Scanner;
public class NumberGuessingGame {
    public static void main(String[] args) {
        Random random = new Random();
        int secretNumber = random.nextInt(11); 
        Scanner scanner = new Scanner(System.in);
        int guess;

        System.out.println("请输入一个0-10之间的整数");

        while (true) {

            while (!scanner.hasNextInt()) {
                System.out.println("输入无效，请输入一个整数！");
                scanner.next();
            }
            guess = scanner.nextInt();


            if (guess < 0 || guess > 10) {
                System.out.println("输入超出范围，请输入0-10之间的整数！");
                continue;
            }


            if (guess > secretNumber) {
                System.out.println("太大了！再猜一次吧！");
            } else if (guess < secretNumber) {
                System.out.println("太小了！再猜一次吧！");
            } else {
                System.out.println("猜对了！");
                break;
            }
        }
        scanner.close();
    }
}
