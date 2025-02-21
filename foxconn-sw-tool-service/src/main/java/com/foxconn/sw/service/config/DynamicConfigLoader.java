package com.foxconn.sw.service.config;

import com.foxconn.sw.common.utils.ConfigReader;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

@Component
@Configuration
@EnableConfigurationProperties
//@org.springframework.cloud.context.config.annotation.RefreshScope // 动态刷新
public class DynamicConfigLoader {

    private static final Logger logger = LoggerFactory.getLogger(DynamicConfigLoader.class);

    private static final String PropertyFileName = "app.property.config";

    private static Properties properties;
    @Autowired
    ConfigReader configReader;

    @PostConstruct
    public void loadConfig() {
        try {
            String filename = configReader.readPropertyValue(PropertyFileName);
            FileSystemResource resource = new FileSystemResource(filename);
            this.properties = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }

    public String getStringProperty(String property) {
        if (properties.containsKey(property)) {
            return properties.getProperty(property);
        }
        return null;
    }

    public String getStringProperty(String property, String defaultValue) {
        if (properties.containsKey(property)) {
            return properties.getProperty(property, defaultValue);
        }
        return defaultValue;
    }

    public Integer getIntegerProperty(String property) {
        return Integer.parseInt(getStringProperty(property));
    }
}