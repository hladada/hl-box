package com.example.numbergaming;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class NumberGuessingGame {
    private final int secretNumber;
    private final int maxAttempts;
    private int attempts;
    private final List<Integer> guessedNumbers;
    private boolean gameOver;
    public NumberGuessingGame() {
        this(new Random().nextInt(11), 3);
    }
    public NumberGuessingGame(int secretNumber, int maxAttempts) {
        this.secretNumber = secretNumber;
        this.maxAttempts = maxAttempts;
        this.attempts = 0;
        this.guessedNumbers = new ArrayList<>();
        this.gameOver = false;
    }
    public String makeGuess(int guess) {
        if (gameOver) {
            return "游戏已结束，请重新开始！";
        }
        if (guess < 0 || guess > 10) {
            return "输入超出范围，请输入0-10之间的整数！";
        }
        if (guessedNumbers.contains(guess)) {
            return "这个数字已经猜过了！";
        }
        attempts++;
        guessedNumbers.add(guess);
        if (guess == secretNumber) {
            gameOver = true;
            return String.format("猜对了！你用了%d次猜中答案。", attempts);
        } else if (attempts >= maxAttempts) {
            gameOver = true;
            return String.format("%s已达到最大猜测次数，游戏结束。正确答案是：%d",
                    guess > secretNumber ? "太大了！" : "太小了！",
                    secretNumber);
        } else if (guess > secretNumber) {
            return String.format("太大了！再猜一次吧！还剩%d次机会。", maxAttempts - attempts);
        } else {
            return String.format("太小了！再猜一次吧！还剩%d次机会。", maxAttempts - attempts);
        }
    }
    public boolean isGameOver() {
        return gameOver;
    }
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
