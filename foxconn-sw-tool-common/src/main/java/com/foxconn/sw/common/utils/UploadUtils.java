package com.foxconn.sw.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class UploadUtils {

    private static final Logger logger = LoggerFactory.getLogger(UploadUtils.class);


    public static String upload(String fileBasepath, MultipartFile multipartFile) {
        return upload(fileBasepath, multipartFile, 0);
    }

    /**
     * 上傳文件
     *
     * @param fileBasepath
     * @param multipartFile
     * @param index
     * @return
     */
    public static String upload(String fileBasepath, MultipartFile multipartFile, int index) {
        try {
            // 构建保存文件的完整路径
            String fileName = renameFile(multipartFile.getOriginalFilename(), index);
            String filePath = fileBasepath + fileName;
            // 保存文件
            File file = new File(filePath);

            File parentDir = file.getParentFile();

            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }

            multipartFile.transferTo(new File(filePath));
            logger.info("文件上传成功：" + fileName, fileName);
            return fileName;
        } catch (Exception e) {
            logger.error("文件上传失败：", e);
        }
        return null;
    }

    /**
     * 规范上传文件命名
     *
     * @param originalName
     * @param index
     * @return
     */
    private static String renameFile(String originalName, int index) {
        String timeStamp = DateTimeUtils.getFilePrefix();
        return String.format("%s_%s.%s", timeStamp, index, getFileExtension(originalName));
    }

    private static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1) {
            return ""; // 没有后缀名
        }
        return fileName.substring(dotIndex + 1);
    }

}
