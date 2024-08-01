package com.foxconn.sw.service;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.foxconn.sw.common.utils.ConfigReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@EnableWebMvc
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Autowired
    ConfigReader configReader;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String ICON_PATH = configReader.readPropertyValue(ConfigReader.ConfigKey.TOOL_ICON);
        String RESULT_PATH = configReader.readPropertyValue(ConfigReader.ConfigKey.TOOL_RESULT);
        String GUIDE_PATH = configReader.readPropertyValue(ConfigReader.ConfigKey.TOOL_GUIDE);

        registry.addResourceHandler("/static/**")
                .addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");

        registry.addResourceHandler("/upload/icon/**")
                .addResourceLocations(ResourceUtils.FILE_URL_PREFIX + ICON_PATH);

        registry.addResourceHandler("/upload/guide/**")
                .addResourceLocations(ResourceUtils.FILE_URL_PREFIX + GUIDE_PATH);

        registry.addResourceHandler("/result/**")
                .addResourceLocations(ResourceUtils.FILE_URL_PREFIX + RESULT_PATH);
    }

    /**
     * 自定义格式化 LocalDateTime 和 LocalDate
     *
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // Create a Jackson2ObjectMapperBuilder to customize ObjectMapper
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.modules(new JavaTimeModule());

        // Customize LocalDateTime serialization
        builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        builder.serializerByType(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        // Configure converters with customized ObjectMapper
        converters.add(6, new MappingJackson2HttpMessageConverter(builder.build()));
    }
}
