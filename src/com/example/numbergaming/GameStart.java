package com.example.numbergaming;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

/**
 * 游戏启动器类
 * 负责加载配置文件、初始化游戏并处理用户交互
 */
public class GameStart{
    // 默认最大猜测次数，如果配置文件加载失败则使用此值
    private static final int DEFAULT_MAX_ATTEMPTS = 3;

    public static void main(String[] args) {
        // 加载配置文件获取最大猜测次数
        int maxAttempts = loadMaxAttemptsFromConfig();

        // 初始化游戏，使用从配置文件加载的最大猜测次数
        NumberGuessingGame game = new NumberGuessingGame(maxAttempts);
        Scanner scanner = new Scanner(System.in);

        System.out.println("欢迎来到数字猜谜游戏！");
        System.out.println("请猜一个0-10之间的整数，你有" + maxAttempts + "次机会");

        // 游戏主循环，直到游戏结束
        while (!game.isGameOver()) {
            // 验证用户输入是否为整数
            while (!scanner.hasNextInt()) {
                System.out.println("输入无效，请输入一个整数！");
                scanner.next(); // 清除无效输入
            }

            // 获取用户猜测的数字并处理
            int guess = scanner.nextInt();
            String result = game.makeGuess(guess);
            System.out.println(result);
        }

        // 关闭输入流
        scanner.close();
    }

    /**
     * 从配置文件加载最大猜测次数
     * @return 配置文件中的最大猜测次数，加载失败则返回默认值
     */
    private static int loadMaxAttemptsFromConfig() {
        Properties properties = new Properties();
        try {
            // 读取配置文件
            File configFile = new File("game.configD:\\Project\\hl-box\\src\\game.config");
            // 检查配置文件是否存在，如果不存在则创建并写入默认值
            if (!configFile.exists()) {
                System.out.println("配置文件不存在，创建默认配置文件...");
                // 实际应用中可以使用FileOutputStream写入默认配置
                return DEFAULT_MAX_ATTEMPTS;
            }

            // 加载配置文件内容
            properties.load(new FileInputStream(configFile));

            // 从配置中获取maxAttempts属性
            String maxAttemptsStr = properties.getProperty("maxAttempts");

            // 验证配置值是否有效
            if (maxAttemptsStr != null && !maxAttemptsStr.isEmpty()) {
                int maxAttempts = Integer.parseInt(maxAttemptsStr);
                // 确保配置值是正数
                if (maxAttempts > 0) {
                    return maxAttempts;
                }
            }

            // 配置值无效时使用默认值
            System.out.println("配置文件中的maxAttempts值无效，使用默认值: " + DEFAULT_MAX_ATTEMPTS);
            return DEFAULT_MAX_ATTEMPTS;

        } catch (IOException e) {
            System.out.println("加载配置文件失败: " + e.getMessage() + "，使用默认值: " + DEFAULT_MAX_ATTEMPTS);
            return DEFAULT_MAX_ATTEMPTS;
        } catch (NumberFormatException e) {
            System.out.println("配置文件中的maxAttempts不是有效的数字，使用默认值: " + DEFAULT_MAX_ATTEMPTS);
            return DEFAULT_MAX_ATTEMPTS;
        }
    }
}
