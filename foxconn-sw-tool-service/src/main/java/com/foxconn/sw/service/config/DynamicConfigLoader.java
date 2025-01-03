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
    @Autowired
    ConfigReader configReader;

    private int port;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @PostConstruct
    public void loadConfig() {
        int tempPort = 0;
        try {
            String filename = configReader.readPropertyValue("app.property.config");
            FileSystemResource resource = new FileSystemResource(filename);
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            if (properties.containsKey("server.port")) {
                tempPort = Integer.parseInt(properties.getProperty("server.port"));
            }
        } catch (IOException e) {
            logger.error(e.toString());

        }
        setPort(tempPort == 0 ? 8080 : tempPort);
    }
}