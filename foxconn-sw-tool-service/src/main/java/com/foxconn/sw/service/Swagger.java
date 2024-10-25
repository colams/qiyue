package com.foxconn.sw.service;

import com.foxconn.sw.data.constants.TagsConstants;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class Swagger {

    private static final String API_PREFIX = "/api/%s/**";

    @Bean
    public OpenAPI createRestApi() {
        Info info = new Info().title("三赢java系统文档").version("1.0.0").license(new License());
        return new OpenAPI().info(info);
    }

    @Bean
    public GroupedOpenApi oaApi() {
        String[] paths = new String[]{
                "/api/" + TagsConstants.OA + "/**",
                "/api/meet/**",
                "/api/collaboration/**",
                "/api/document/**",
                "/api/project/**",
        };
        return createGroupedOpenApi(TagsConstants.OA, paths);
    }

    @Bean
    public GroupedOpenApi routeApi() {
        return createGroupedOpenApi(TagsConstants.MENU);
    }

    @Bean
    public GroupedOpenApi menuApi() {
        return createGroupedOpenApi(TagsConstants.ROUTE);
    }

    @Bean
    public GroupedOpenApi accountApi() {
        String[] paths = new String[]{
                "/api/" + TagsConstants.ACCOUNT + "/**",
                "/api/profile/**"
        };
        return createGroupedOpenApi(TagsConstants.ACCOUNT, paths);
    }

    @Bean
    public GroupedOpenApi toolApi() {
        return createGroupedOpenApi(TagsConstants.TOOL);
    }

    @Bean
    public GroupedOpenApi universalApi() {
        String[] paths = new String[]{
                "/api/" + TagsConstants.UNIVERSAL + "/**",
                "/api/property/**",
                "/api/feedback/**"
        };
        return createGroupedOpenApi(TagsConstants.UNIVERSAL, paths);
    }

    @Bean
    public GroupedOpenApi announcementApi() {
        return createGroupedOpenApi(TagsConstants.ANNOUNCEMENT);
    }

    @Bean
    public GroupedOpenApi systemApi() {
        String[] paths = new String[]{
                String.format(API_PREFIX, TagsConstants.SYSTEM),
                String.format(API_PREFIX, "department"),
                String.format(API_PREFIX, "property"),
                String.format(API_PREFIX, "basic"),

        };
        return createGroupedOpenApi(TagsConstants.SYSTEM, paths);
    }

//    @Bean
//    public GroupedOpenApi systemApi() {
//        String[] paths = new String[]{
//                String.format(API_PREFIX, TagsConstants.SYSTEM),
//                String.format(API_PREFIX, "depart"),
//        };
//        return createGroupedOpenApi(TagsConstants.SYSTEM);
//    }

    private GroupedOpenApi createGroupedOpenApi(String group, String... paths) {
        if (Objects.isNull(paths) || paths.length <= 0) {
            paths = new String[]{String.format(API_PREFIX, group)};
        }

        return GroupedOpenApi.builder()
                .group(group)
                .pathsToMatch(paths)
                .build();
    }
}