package com.foxconn.sw.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ConfigReader {

    @Autowired
    Environment environment;

    /**
     * 读取配置文件
     *
     * @return
     */
    public String readPropertyValue(String propertyKey) {
        return environment.getProperty(propertyKey);
    }

    /**
     * 读取配置文件
     *
     * @return
     */
    public String getBaseUploadPath() {
        return environment.getProperty(ConfigKey.BASE_UPLOAD_PATH);
    }

    public interface ConfigKey {
        String TOOL_FILE = "tool.file.base.path";
        String TOOL_ICON = "tool.icon.base.path";
        String TOOL_GUIDE = "tool.guide.base.path";
        String TOOL_RESULT = "tool.result.base.path";
        String OA_RESOURCE = "oa.base.path";
        String BASE_UPLOAD_PATH = "upload.base.path";
    }
}
