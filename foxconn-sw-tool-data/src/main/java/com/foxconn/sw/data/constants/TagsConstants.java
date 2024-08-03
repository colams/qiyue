package com.foxconn.sw.data.constants;

import com.google.common.collect.Lists;

import java.util.List;

public interface TagsConstants {

    String TOOL = "tool";
    String ACCOUNT = "account";
    String ROUTE = "route";
    String MENU = "menu";
    String OA = "oa";
    String UNIVERSAL = "universal";
    String ANNOUNCEMENT = "announcement";
    String SYSTEM = "system";

    List<String> tagList = Lists.newArrayList(TOOL, ACCOUNT, ROUTE, MENU, OA, UNIVERSAL, ANNOUNCEMENT, SYSTEM);

}
