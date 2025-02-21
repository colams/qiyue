package com.foxconn.sw.common.utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.FileInputStream;
import java.io.IOException;

public class FtpUploadUtils {

    private static final String server = "ftp.example.com"; // ftp服务器
    private static final Integer port = 21; // ftp 端口号
    private static final String user = "username";  // ftp 账号
    private static final String pass = "password";  // ftp 密码
    private static final String uploadFile = "/path/to/local/file.txt"; // 要上传的文件
    private static final String uploadFileServer = "/path/to/remote/file.txt";  // 保存的文件

    public static void main(String[] args) {
        FTPClient ftp = new FTPClient();
        try {
            ftp.connect(server, port);
            ftp.login(user, pass);
            ftp.setFileType(FTP.BINARY_FILE_TYPE); // 设置传输模式为二进制模式
            FileInputStream input = new FileInputStream(uploadFile);
            boolean done = ftp.storeFile(uploadFileServer, input);
            input.close();
            if (done) {
                System.out.println("文件上传成功");
            } else {
                System.out.println("文件上传失败");
            }
            ftp.logout();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ftp.isConnected()) {
                    ftp.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
