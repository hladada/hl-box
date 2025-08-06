package com.example.numbergaming;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * 通用功能测试类，主要用于验证文件IO等功能的实现
 */
public class CommonTest {

    // 配置文件路径（与项目中实际使用的路径保持一致）
    private static final String CONFIG_FILE_PATH = "game.config";

    public static void main(String[] args) {
        System.out.println("=== 开始文件IO功能验证 ===");

        // 测试配置文件读取功能
        testConfigFileReading();

        // 测试配置文件创建功能
        testConfigFileCreation();

        // 测试文件是否存在判断功能
        testFileExistenceCheck();

        System.out.println("=== 文件IO功能验证结束 ===");
    }

    /**
     * 测试配置文件读取功能
     */
    public static void testConfigFileReading() {
        System.out.println("\n[测试] 配置文件读取...");
        Properties properties = new Properties();

        try (InputStream input = CommonTest.class.getClassLoader().getResourceAsStream(CONFIG_FILE_PATH)) {
            if (input == null) {
                System.out.println("配置文件不存在，无法读取");
                return;
            }

            // 加载配置文件
            properties.load(input);

            // 读取并打印配置项
            String maxAttempts = properties.getProperty("maxAttempts");
            System.out.println("读取到的maxAttempts值: " + maxAttempts);

            if (maxAttempts == null || maxAttempts.trim().isEmpty()) {
                System.out.println("警告：配置文件中未找到有效的maxAttempts值");
            } else {
                try {
                    int value = Integer.parseInt(maxAttempts);
                    System.out.println("配置值有效，转换为整数: " + value);
                } catch (NumberFormatException e) {
                    System.out.println("错误：配置值不是有效的整数");
                }
            }

        } catch (IOException e) {
            System.out.println("读取配置文件失败: " + e.getMessage());
        }
    }

    /**
     * 测试配置文件创建功能
     */
    public static void testConfigFileCreation() {
        System.out.println("\n[测试] 配置文件创建...");

        // 检查文件是否已存在
        if (Files.exists(Paths.get(CONFIG_FILE_PATH))) {
            System.out.println("配置文件已存在，无需创建");
            return;
        }

        // 创建并写入默认配置
        try (OutputStream output = new FileOutputStream(CONFIG_FILE_PATH)) {
            Properties properties = new Properties();
            properties.setProperty("maxAttempts", "5"); // 默认值

            // 写入配置文件（带注释）
            properties.store(output, "Number Guessing Game Configuration");
            System.out.println("默认配置文件创建成功，路径: " + new File(CONFIG_FILE_PATH).getAbsolutePath());

        } catch (IOException e) {
            System.out.println("创建配置文件失败: " + e.getMessage());
        }
    }

    /**
     * 测试文件是否存在的判断功能
     */
    public static void testFileExistenceCheck() {
        System.out.println("\n[测试] 文件存在性检查...");

        File configFile = new File(CONFIG_FILE_PATH);
        if (configFile.exists()) {
            System.out.println("文件存在");
            System.out.println("文件路径: " + configFile.getAbsolutePath());
            System.out.println("文件大小: " + configFile.length() + " 字节");
        } else {
            System.out.println("文件不存在，路径: " + configFile.getAbsolutePath());
        }
    }
}
