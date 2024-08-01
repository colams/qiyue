package com.foxconn.sw.service.utils;


import com.foxconn.sw.common.utils.ConfigReader;
import com.foxconn.sw.data.constants.enums.FileAttrTypeEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

@Component
public class FilePathUtils {

    @Autowired
    ConfigReader configReader;

    public String getFilePath(String fileAttr) throws FileNotFoundException {
        String path = "";
        if (FileAttrTypeEnums.TOOL.getSymbol().equalsIgnoreCase(fileAttr)) {
            path = configReader.readPropertyValue(ConfigReader.ConfigKey.TOOL_FILE);
        } else if (FileAttrTypeEnums.ICON.getSymbol().equalsIgnoreCase(fileAttr)) {
            path = configReader.readPropertyValue(ConfigReader.ConfigKey.TOOL_ICON);
        } else if (FileAttrTypeEnums.GUIDE.getSymbol().equalsIgnoreCase(fileAttr)) {
            path = configReader.readPropertyValue(ConfigReader.ConfigKey.TOOL_GUIDE);
        } else if (FileAttrTypeEnums.RESULT.getSymbol().equalsIgnoreCase(fileAttr)) {
            path = configReader.readPropertyValue(ConfigReader.ConfigKey.TOOL_RESULT);
        }

        return ResourceUtils.getURL(ResourceUtils.FILE_URL_PREFIX + path).getPath();
    }

}
