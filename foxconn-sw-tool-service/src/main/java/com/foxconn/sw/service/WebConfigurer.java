package com.foxconn.sw.service;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.foxconn.sw.common.utils.ConfigReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@EnableWebMvc
@Configuration
public class WebConfigurer implements WebMvcConfigurer, WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    @Autowired
    ConfigReader configReader;

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        factory.setPort(8000);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String ICON_PATH = configReader.readPropertyValue(ConfigReader.ConfigKey.TOOL_ICON);
        String RESULT_PATH = configReader.readPropertyValue(ConfigReader.ConfigKey.TOOL_RESULT);
        String GUIDE_PATH = configReader.readPropertyValue(ConfigReader.ConfigKey.TOOL_GUIDE);

        registry.addResourceHandler("/favicon.ico")
                .addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
        registry.addResourceHandler("/static/**")
                .addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");

        registry.addResourceHandler("/upload/**")
                .addResourceLocations(ResourceUtils.FILE_URL_PREFIX + configReader.getBaseUploadPath());

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

    /**
     * 配置跨域请求的映射规则
     *
     * @param registry 用于注册 CORS 配置的注册表
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        CorsRegistration registration = registry.addMapping("/**")  // 允许所有路径的跨域请求
                .allowedMethods("GET", "POST")  // 允许的请求方法
                .allowedHeaders("*")  // 允许所有请求头
                .exposedHeaders("Content-Disposition")
                .allowCredentials(true)  // 允许携带凭证（如 Cookies）
                .maxAge(3600);

        // 添加跨域映射规则
        String allowedOrigin = "*";
        if (allowedOrigin.equalsIgnoreCase("*")) {
            registration.allowedOriginPatterns("*"); // 允许来自 http://example.com 的跨域请求
        } else {
            String[] origins = allowedOrigin.split(",");
            registration.allowedOrigins(origins);  // 允许来自 http://example.com 的跨域请求
        }
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOriginPattern("*");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.setAllowCredentials(true);
        config.addExposedHeader("Content-Disposition");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

//    @Bean
//    public FilterRegistrationBean corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOriginPattern("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        config.addExposedHeader("Content-Disposition");
//        source.registerCorsConfiguration("/**", config);
//        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//        bean.setOrder(0);
//        return bean;
//    }

}
