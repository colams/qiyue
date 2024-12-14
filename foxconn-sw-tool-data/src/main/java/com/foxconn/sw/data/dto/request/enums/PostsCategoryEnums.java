package com.foxconn.sw.data.dto.request.enums;

public enum PostsCategoryEnums {


    Default(0, "default", "默认所有"),
    MyPosts(1, "MyPosts", "我的帖子"),
    CollectPosts(2, "CollectPosts", "我的收藏"),
    ;

    PostsCategoryEnums(int code, String enCode, String name) {
        this.code = code;
        this.enCode = enCode;
        this.name = name;
    }

    private int code;
    private String enCode;
    private String name;

    public int getCode() {
        return code;
    }

    public String getEnCode() {
        return enCode;
    }

    public String getName() {
        return name;
    }


}
