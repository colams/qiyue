package com.foxconn.sw.common.utils;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipUtils {


    public static String unZipFile(String toolPath, String params) {
        String zipFilePath = "encrypted.zip";
        String destFolderPath = "output";
        String password = "mypassword";

        try {
            FileInputStream fileInputStream = new FileInputStream(zipFilePath);
            ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);
            byte[] buffer = new byte[1024];

            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
            DESKeySpec desKeySpec = new DESKeySpec(password.getBytes());
            SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);

            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                String entryName = zipEntry.getName();
                String decryptedFileName = destFolderPath + "/" + entryName;
                FileOutputStream fileOutputStream = new FileOutputStream(decryptedFileName);
                CipherInputStream cipherInputStream = new CipherInputStream(zipInputStream, cipher);

                int bytesRead;
                while ((bytesRead = cipherInputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }

                cipherInputStream.close();
                fileOutputStream.close();
                zipInputStream.closeEntry();
            }

            zipInputStream.close();

            System.out.println("ZIP解压加密文件成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
