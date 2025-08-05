package com.example.numbergaming;
import java.util.Scanner;
public class GameStart {
    public static void main(String[] args) {
        NumberGuessingGame game = new NumberGuessingGame();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个0-10之间的整数");
        while (!game.isGameOver()) {
            while (!scanner.hasNextInt()) {
                System.out.println("输入无效，请输入一个整数！");
                scanner.next();
            }
            int guess = scanner.nextInt();
            String result = game.makeGuess(guess);
            System.out.println(result);
        }
        scanner.close();
    }
}
