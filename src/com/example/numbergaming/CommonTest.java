package com.example.numbergaming;

import java.io.*;

/**
 * 简易文件IO测试类
 * 验证最基础的文件创建、读取、删除操作
 */
public class CommonTest {
    // 测试文件名称
    private static final String TEST_FILE = "test.txt";

    public static void main(String[] args) {
        System.out.println("=== 简易文件IO测试 ===");

        // 测试创建文件并写入内容
        testCreateFile();

        // 测试读取文件内容
        testReadFile();

        // 测试删除文件
        testDeleteFile();
    }

    /**
     * 测试文件创建和写入
     */
    private static void testCreateFile() {
        System.out.println("\n[创建文件]");
        try (FileWriter writer = new FileWriter(TEST_FILE)) {
            // 写入测试内容
            writer.write("Hello, File IO!");
            System.out.println("文件创建并写入成功: " + new File(TEST_FILE).getAbsolutePath());
        } catch (IOException e) {
            System.out.println("创建失败: " + e.getMessage());
        }
    }

    /**
     * 测试文件读取
     */
    private static void testReadFile() {
        System.out.println("\n[读取文件]");
        File file = new File(TEST_FILE);
        if (!file.exists()) {
            System.out.println("文件不存在，无法读取");
            return;
        }

        try (FileReader reader = new FileReader(file);
             BufferedReader br = new BufferedReader(reader)) {

            String content = br.readLine();
            System.out.println("读取内容: " + content);
        } catch (IOException e) {
            System.out.println("读取失败: " + e.getMessage());
        }
    }

    /**
     * 测试文件删除
     */
    private static void testDeleteFile() {
        System.out.println("\n[删除文件]");
        File file = new File(TEST_FILE);
        if (file.delete()) {
            System.out.println("文件删除成功");
        } else {
            System.out.println("文件删除失败或不存在");
        }
    }
}
