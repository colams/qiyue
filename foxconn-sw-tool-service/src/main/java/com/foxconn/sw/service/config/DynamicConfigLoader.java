package com.foxconn.sw.service.config;

import com.foxconn.sw.common.utils.ConfigReader;
import jakarta.annotation.PostConstruct;
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
        try {
            String filename = configReader.readPropertyValue("app.property.config");
            System.out.println(filename);
            FileSystemResource resource = new FileSystemResource(filename);
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            if (properties.containsKey("server.port")) {
                this.port = Integer.parseInt(properties.getProperty("server.port"));
            } else {
                this.port = 8080;
            }
        } catch (IOException e) {
            System.out.println(e);
            this.port = 8080;
        }
    }
}