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
                "/api/file/**",
                "/api/universal/**",
                "/api/document/**",
                "/api/pj/**",
        };
        return createGroupedOpenApi(TagsConstants.OA, paths);
    }

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