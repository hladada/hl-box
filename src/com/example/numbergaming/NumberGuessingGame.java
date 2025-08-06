package com.example.numbergaming;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 数字猜谜游戏核心逻辑类
 * 负责生成秘密数字、处理猜测逻辑和跟踪游戏状态
 */
public class NumberGuessingGame {
    // 秘密数字（游戏目标，用户需要猜中的数字）
    private final int secretNumber;
    // 最大猜测次数（从配置文件加载）
    private final int maxAttempts;
    // 当前已猜测次数
    private int attempts;
    // 存储用户已经猜过的数字，避免重复猜测
    private final List<Integer> guessedNumbers;
    // 游戏是否结束的标志
    private boolean gameOver;

    /**
     * 构造方法，使用指定的最大猜测次数初始化游戏
     * 秘密数字：0-10之间的随机整数
     * @param maxAttempts 最大允许猜测次数
     */
    public NumberGuessingGame(int maxAttempts) {
        this.secretNumber = new Random().nextInt(11);
        this.maxAttempts = maxAttempts;
        this.attempts = 0; // 初始化为0次猜测
        this.guessedNumbers = new ArrayList<>(); // 初始化已猜数字列表
        this.gameOver = false; // 游戏初始状态为未结束
    }

    /**
     * 处理用户的猜测逻辑
     * @param guess 用户输入的猜测数字
     * @return 猜测结果的反馈信息
     */
    public String makeGuess(int guess) {
        // 如果游戏已结束，返回提示信息
        if (gameOver) {
            return "游戏已结束，请重新开始！";
        }

        // 检查猜测数字是否超出0-10的范围
        if (guess < 0 || guess > 10) {
            return "输入超出范围，请输入0-10之间的整数！";
        }

        // 检查是否重复猜测同一个数字
        if (guessedNumbers.contains(guess)) {
            return "这个数字已经猜过了！";
        }

        // 猜测次数加1
        attempts++;
        // 记录本次猜测的数字
        guessedNumbers.add(guess);

        // 判断是否猜中秘密数字
        if (guess == secretNumber) {
            gameOver = true; // 标记游戏结束
            return String.format("猜对了！你用了%d次猜中答案。", attempts);
        }
        // 判断是否达到最大猜测次数
        else if (attempts >= maxAttempts) {
            gameOver = true; // 标记游戏结束
            // 根据猜测结果给出"太大"或"太小"的提示，并显示正确答案
            return String.format("%s已达到最大猜测次数，游戏结束。正确答案是：%d",
                    guess > secretNumber ? "太大了！" : "太小了！",
                    secretNumber);
        }
        // 猜测数字太大的情况
        else if (guess > secretNumber) {
            return String.format("太大了！再猜一次吧！还剩%d次机会。", maxAttempts - attempts);
        }
        // 猜测数字太小的情况
        else {
            return String.format("太小了！再猜一次吧！还剩%d次机会。", maxAttempts - attempts);
        }
    }

    /**
     * 判断游戏是否结束
     * @return 游戏结束返回true，否则返回false
     */
    public boolean isGameOver() {
        return gameOver;
    }
}
