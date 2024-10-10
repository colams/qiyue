package com.foxconn.sw.common.utils;


import com.foxconn.sw.common.utils.ConfigReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

@Component
public class FilePathUtils {

    @Autowired
    ConfigReader configReader;

    public String getFilePath(String fileAttr) throws FileNotFoundException {
        String resourceLocation = ResourceUtils.FILE_URL_PREFIX + configReader.getBaseUploadPath() + fileAttr.toLowerCase() + "/";
        return ResourceUtils.getURL(resourceLocation).getPath();
    }
}
