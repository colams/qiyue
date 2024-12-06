package com.foxconn.sw.data.constants;

import com.google.common.collect.Lists;

import java.util.List;

public interface TagsConstants {

    String TOOL = "tool";
    String ACCOUNT = "account";
    String ROUTE = "route";
    String MENU = "menu";
    String OA = "oa";
    String TASK = "task";
    String REPORT = "report";
    String DOCUMENT = "document";
    String MEET = "meet";
    String PROJECT = "project";
    String COLLABORATION = "collaboration";
    String UNIVERSAL = "universal";
    String ANNOUNCEMENT = "announcement";
    String FORUMS = "forums";
    String COMMENTS = "Comment";
    String SYSTEM = "system";
    String GROUP = "group";



    List<String> tagList = Lists.newArrayList(TOOL, ACCOUNT, ROUTE, MENU, OA, UNIVERSAL, ANNOUNCEMENT, SYSTEM, FORUMS,COMMENTS);

}
