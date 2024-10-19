package com.foxconn.sw.common.utils;

import com.google.common.collect.Lists;
import org.python.util.PythonInterpreter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExecToolUtils {

    private static final Logger logger = LoggerFactory.getLogger(ExecToolUtils.class);


    /**
     * @param toolPath
     * @param params
     * @return
     */
    public String execTool(String toolPath, String params) throws ScriptException, IOException {
        if (toolPath.endsWith("py")) {
            return execPythonFile1(toolPath);
        } else {
            return execExeFile(toolPath, params);
        }
    }

    /**
     * @param toolPath
     * @return
     */
    private String execPythonFile1(String toolPath) throws IOException {
        ConfigReader configReader = SpringUtils.getBean(ConfigReader.class);
        String fileDir = configReader.readPropertyValue(ConfigReader.ConfigKey.TOOL_RESULT);
        String fileName = DateTimeUtils.getFilePrefix() + ".txt";

        String filePath = fileDir + fileName;
        File tempFile = new File(filePath);
        File parentDir = tempFile.getParentFile();

        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
        tempFile.deleteOnExit();

        // 重定向 Python 的标准输出到临时文件
        PrintStream originalOut = System.out;
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            PrintStream tempOut = new PrintStream(fos);
            System.setOut(tempOut);
            PythonInterpreter interpreter = new PythonInterpreter();
            interpreter.execfile(toolPath);
            interpreter.cleanup();
            interpreter.close();
        } finally {
            // 恢复标准输出
            System.setOut(originalOut);
        }

        // 读取临时文件的内容
        try (java.util.Scanner scanner = new java.util.Scanner(tempFile)) {
            if (scanner.hasNextLine()) {
                String output = scanner.nextLine();
                System.out.println("Output from Python script: " + output);
            }
        }
        return fileName;
    }


    /**
     * @param toolPath
     * @return
     */
    public String execPythonFile(String toolPath) throws ScriptException, FileNotFoundException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("python");
        String fileName = "";
        if (engine != null) {
            // 执行 Python 脚本文件
            engine.eval(new java.io.FileReader(toolPath));
            // 调用 Python 函数
            fileName = outputResult(Lists.newArrayList("Result from Python function: " + "value"));
        } else {
            System.out.println("Python engine not found.");
        }

        return fileName;
    }

    private String readFile(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath);
             FileOutputStream fos = new FileOutputStream(filePath)) {

            List<String> list = new ArrayList<>();
            int content;
            while ((content = fis.read()) != -1) {
                fos.write(content);
                System.out.print((char) content);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @param toolPath
     * @param params
     * @return
     */
    public String execExeFile(String toolPath, String params) {
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
    public String outputResult(List<String> results) throws FileNotFoundException {

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
