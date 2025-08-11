package com.example.numbergaming;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class GameStartTest {
    private static final int DEFAULT = 3;
    private static final String CONFIG = "game.config";

    public static void main(String[] args) {
        System.out.println("=== 测试配置加载方法 ===");

        // 测试场景：文件不存在、有效配置、无效格式、无效范围
        test(null, DEFAULT);  // 无文件
        test("maxAttempts=5", 5);  // 有效配置
        test("maxAttempts=abc", DEFAULT);  // 格式错误
        test("maxAttempts=-1", DEFAULT);  // 范围错误

        System.out.println("=== 测试结束 ===");
    }

    /**
     * 通用测试方法
     *
     * @param content  配置文件内容（null表示无文件）
     * @param expected 预期返回值
     */
    private static void test(String content, int expected) {
        System.out.println("\n[测试: " + (content == null ? "无配置文件" : content) + "]");

        // 准备测试环境（创建/删除文件）
        if (content == null) {
            new File(CONFIG).delete(); // 删除文件
        } else {
            try (FileWriter w = new FileWriter(CONFIG)) {
                w.write(content); // 写入配置
            } catch (IOException e) {
                System.out.println("准备测试文件失败: " + e.getMessage());
                return;
            }
        }

        // 执行方法并验证结果
        int result = loadMaxAttemptsFromConfig();
        System.out.println(result == expected ? "通过" : "失败" +
                " (实际: " + result + ", 预期: " + expected + ")");
    }

    // 待测试的配置加载方法
    private static int loadMaxAttemptsFromConfig() {
        Properties prop = new Properties();
        File file = new File(CONFIG);

        if (!file.exists()) {
            System.out.println("无配置文件，用默认值");
            return DEFAULT;
        }

        try (java.io.FileInputStream fis = new java.io.FileInputStream(file)) {
            prop.load(fis);
            String val = prop.getProperty("maxAttempts");

            if (val != null && !val.isEmpty()) {
                int num = Integer.parseInt(val);
                if (num > 0) return num;
            }
            System.out.println("配置无效，用默认值");
        } catch (Exception e) {
            System.out.println("加载失败，用默认值: " + e.getMessage());
        }

        return DEFAULT;
    }
}