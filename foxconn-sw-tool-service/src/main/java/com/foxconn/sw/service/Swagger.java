package com.foxconn.sw.service;

import com.foxconn.sw.data.constants.TagsConstants;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger {

    @Bean
    public OpenAPI createRestApi() {
        Info info = new Info().title("三赢java系统文档").version("1.0.0").license(new License());
        return new OpenAPI().info(info);
    }

//    @Bean
//    public List<GroupedOpenApi> groupedOpenApis() {
//        List<GroupedOpenApi> groupedOpenApis = new ArrayList<>();
//
//        groupedOpenApis.add(createGroupedOpenApi(TagsConstants.OA));
//        groupedOpenApis.add(createGroupedOpenApi(TagsConstants.MENU));
//        groupedOpenApis.add(createGroupedOpenApi(TagsConstants.ROUTE));
//        groupedOpenApis.add(createGroupedOpenApi(TagsConstants.ACCOUNT));
//        groupedOpenApis.add(createGroupedOpenApi(TagsConstants.TOOL));
//        groupedOpenApis.add(createGroupedOpenApi(TagsConstants.UNIVERSAL));
//        groupedOpenApis.add(createGroupedOpenApi(TagsConstants.ANNOUNCEMENT));
//        groupedOpenApis.add(createGroupedOpenApi(TagsConstants.SYSTEM));
//
//        return groupedOpenApis;
//    }

    @Bean
    public GroupedOpenApi oaApi() {
        return createGroupedOpenApi(TagsConstants.OA);
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
        return createGroupedOpenApi(TagsConstants.ACCOUNT);
    }

    @Bean
    public GroupedOpenApi toolApi() {
        return createGroupedOpenApi(TagsConstants.TOOL);
    }

    @Bean
    public GroupedOpenApi universalApi() {
        return createGroupedOpenApi(TagsConstants.UNIVERSAL, "/api/" + TagsConstants.UNIVERSAL + "/**", "/api/property/**");
    }

    @Bean
    public GroupedOpenApi announcementApi() {
        return createGroupedOpenApi(TagsConstants.ANNOUNCEMENT);
    }

    @Bean
    public GroupedOpenApi systemApi() {
        return createGroupedOpenApi(TagsConstants.SYSTEM);
    }

    private GroupedOpenApi createGroupedOpenApi(String group) {
        String pathPattern = "/api/" + group + "/**";

        return GroupedOpenApi.builder()
                .group(group)
                .pathsToMatch(pathPattern)
                .build();
    }

    private GroupedOpenApi createGroupedOpenApi(String group, String... paths) {
        return GroupedOpenApi.builder()
                .group(group)
                .pathsToMatch(paths)
                .build();
    }
}