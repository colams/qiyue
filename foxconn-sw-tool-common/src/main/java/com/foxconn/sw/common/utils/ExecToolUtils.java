package com.foxconn.sw.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExecToolUtils {

    private static final Logger logger = LoggerFactory.getLogger(ExecToolUtils.class);

    /**
     * @param toolPath
     * @param params
     * @return
     */
    public static String execTool(String toolPath, String params) {
        String charset = "Big5";
        try {
            if (toolPath.startsWith("/")) {
                toolPath = toolPath.substring(1);
            }

            Process process = Runtime.getRuntime().exec("cmd.exe");
            process.getOutputStream().write((toolPath + "  " + params + "\r\n").getBytes());
            List<String> results = new ArrayList<>();

            // 创建一个线程来读取新进程的输出
            Thread outputThread = new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), charset))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        results.add("exec:" + line);
                    }
                    results.add("BufferedReader  end ");
                } catch (IOException e) {
                    System.out.println("BufferedReader  excetpinot");
                    e.printStackTrace();
                }
            });

            // 启动读取输出的线程
            outputThread.start();

            // 向新进程发送输入数据
            try (OutputStreamWriter writer = new OutputStreamWriter(process.getOutputStream(), charset)) {
                writer.write("您要輸入的數據\r\n");
                writer.flush();
                writer.write("s");
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 等待读取输出的线程结束
                outputThread.join();
            // 等待命令执行完成
            int res = process.waitFor();
            results.add("waitFor: " + res);
            // 关闭进程
            process.destroy();
            String fileName = outputResult(results);
            return fileName;
        } catch (IOException | InterruptedException e) {
            logger.error("execTool", e);
            return null;
        }
    }

    /**
     * 写入工具运行结果到文本中
     *
     * @param results
     * @return
     * @throws FileNotFoundException
     */
    public static String outputResult(List<String> results) throws FileNotFoundException {

        ConfigReader configReader = SpringUtils.getBean(ConfigReader.class);
        String fileDir = configReader.readPropertyValue(ConfigReader.ConfigKey.TOOL_RESULT);
        String fileName = DateTimeUtils.getFilePrefix() + ".txt";

        String filePath = fileDir + fileName;
        File file = new File(filePath);
        File parentDir = file.getParentFile();

        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String result : results) {
                writer.write(result);
                writer.write("\r\n");
            }
        } catch (IOException e) {
            logger.error("outputResult", e);
        }
        return fileName;
    }

}
